import model.dungeon.Directions;
import model.dungeon.DungeonModel;
import model.dungeon.Location;
import model.dungeon.Model;
import model.dungeon.TreasuresTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test and validate all the methods of dungeon.
 */
public class DungeonModelTest {

  private Model dungeonNonWrapping;
  private Model dungeonWrapping;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    dungeonNonWrapping = new DungeonModel(new DungeonModel(4,5,0,
            0,false,40, 7, 1, 1));
    dungeonWrapping = new DungeonModel(new DungeonModel(5,6,0,
            0,true,40, 8, 1, 1));
  }

  @Test
  public void testGetRows() {
    assertEquals(4, dungeonNonWrapping.getRows());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidRows() {
    new DungeonModel(0,4,0,0,false,
            20, 6, 1, 1);
    thrown.expectMessage("Rows and columns cannot be less than 1");
  }

  @Test
  public void getCols() {
    assertEquals(5, dungeonNonWrapping.getCols());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCol() {
    new DungeonModel(4,0,0,0,false,
            20, 2, 1, 1);
    thrown.expectMessage("Rows and columns cannot be less than 1");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumPits() {
    new DungeonModel(new DungeonModel(4,5,0,0,
            false, 20, 2, -1, 1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumThieves() {
    new DungeonModel(new DungeonModel(4,5,0,0,
            false, 20, 2, 0, -1));
  }

  @Test
  public void testNumPits() {
    Model m = new DungeonModel(new DungeonModel(4,5,0,0,
            false, 20, 2, 2, 0));
    List<Location> locationList = m.getLocationList();
    int count = 0;
    for (Location l : locationList) {
      if (l.isPitPresent()) {
        count++;
      }
    }
    assertEquals(count,2);
  }

  @Test
  public void testZeroPits() {
    Model m = new DungeonModel(new DungeonModel(4,5,0,0,
            false, 20, 2, 0, 1));
    List<Location> locationList = m.getLocationList();
    int count = 0;
    for (Location l : locationList) {
      if (l.isPitPresent()) {
        count++;
      }
    }
    assertEquals(count,0);
  }


  @Test
  public void testZeroThieves() {
    Model m = new DungeonModel(new DungeonModel(4,5,0,0,
            false, 20, 2, 1, 0));
    List<Location> locationList = m.getLocationList();
    int count = 0;
    for (Location l : locationList) {
      if (l.isThiefPresent()) {
        count++;
      }
    }
    assertEquals(count,0);
  }

  @Test
  public void testNumThieves() {
    Model m = new DungeonModel(new DungeonModel(4,5,0,0,
            false, 20, 2, 0, 2));
    List<Location> locationList = m.getLocationList();
    int count = 0;
    for (Location l : locationList) {
      if (l.isThiefPresent()) {
        count++;
      }
    }
    assertEquals(count,2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidDungeonCreation() {
    new DungeonModel(2,2,0,0,false,
            20, 5, 1, 1);
    thrown.expectMessage("The product of rows and columns should be at least 5 "
            + "for a valid dungeon");
  }

  @Test
  public void testInterconnectivity() {
    assertEquals(0, dungeonNonWrapping.interConnectivity());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidInterconnectivity() {
    new DungeonModel(4,6,-1,0,false,
            20, 7, 1, 1);
    thrown.expectMessage("Interconnectivity cannot be less than 0");
  }

  @Test
  public void testDungeonBeforeInterconnectivityNonWrapping() {
    Model d = new DungeonModel(new DungeonModel(4,5,0,
            0,false,20,4, 1, 1));
    int count = 0;
    for (Location l : d.getLocationList()) {
      count += l.getDirectionsList().size();
    }
    assertEquals(38, count);
  }

  @Test
  public void testDungeonAfterInterconnectivityNonWrapping() {
    Model d = new DungeonModel(new DungeonModel(4,5,4,
            0,false,20,4, 1, 1));
    int count = 0;
    for (Location l : d.getLocationList()) {
      count += l.getDirectionsList().size();
    }
    assertEquals(46, count);
  }

  @Test
  public void testDungeonBeforeInterconnectivityWrapping() {
    Model d = new DungeonModel(new DungeonModel(5,5,0,
            0,true,20, 4, 1, 1));
    int count = 0;
    for (Location l : d.getLocationList()) {
      count += l.getDirectionsList().size();
    }
    assertEquals(48, count);
  }

  @Test
  public void testDungeonAfterInterconnectivityWrapping() {
    Model d = new DungeonModel(new DungeonModel(5,5,4,
            0,true,20, 4, 1, 1));
    int count = 0;
    for (Location l : d.getLocationList()) {
      count += l.getDirectionsList().size();
    }
    assertEquals(56, count);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidAmount() {
    new DungeonModel(4,5, 1,0,
            true, -1, 5, 1, 1);
    thrown.expectMessage("Amount to be distributed cannot be zero");
  }

  @Test
  public void testIsCave() {
    Location l = dungeonNonWrapping.getLocationList().get(1);
    assertTrue(l.isCave());
    assertTrue(dungeonWrapping.getLocationList().get(2).isCave());
  }

  @Test
  public void testIsTunnel() {
    Location l = dungeonNonWrapping.getLocationList().get(3);
    assertFalse(l.isCave());
  }

  @Test
  public void testIsCaveOneWayEntryExit() {
    List<Directions> dir1 = dungeonNonWrapping.getLocationList().get(0).getDirectionsList();
    assertEquals("[South]",dir1.toString());
    assertTrue(dungeonNonWrapping.getLocationList().get(0).isCave());
  }

  @Test
  public void testIsTunnelTwoWayEntryExit() {
    List<Directions> dir1 = dungeonNonWrapping.getLocationList().get(3).getDirectionsList();
    List<Directions> dir2 = dungeonWrapping.getLocationList().get(0).getDirectionsList();
    assertEquals("[East, South]",dir1.toString());
    assertEquals("[East, North]",dir2.toString());
    assertFalse(dungeonNonWrapping.getLocationList().get(3).isCave());
    assertFalse(dungeonWrapping.getLocationList().get(0).isCave());
  }

  @Test
  public void testIsCaveThreeWayEntryExit() {
    List<Directions> dir1 = dungeonNonWrapping.getLocationList().get(11).getDirectionsList();
    assertEquals("[East, West, South]",dir1.toString());
    assertTrue(dungeonNonWrapping.getLocationList().get(11).isCave());
  }

  @Test
  public void testIsCaveFourWayEntryExit() {
    List<Directions> dir1 = dungeonNonWrapping.getLocationList().get(8).getDirectionsList();
    assertEquals("[South, West, North, East]",dir1.toString());
    assertTrue(dungeonNonWrapping.getLocationList().get(8).isCave());
  }

  @Test
  public void testGetStart() {
    assertEquals(0, dungeonNonWrapping.getStart());
    assertEquals("{0={[South]=[]}}",dungeonNonWrapping.getCurrentLocation().toString());
  }

  @Test
  public void testStartIsCave() {
    Location l = dungeonNonWrapping.getLocationList().get(dungeonNonWrapping.getStart());
    assertTrue(l.isCave());
  }

  @Test
  public void testGetEnd() {
    assertEquals(8, dungeonNonWrapping.getEnd());
  }

  @Test
  public void testEndIsCave() {
    Location l = dungeonNonWrapping.getLocationList().get(dungeonNonWrapping.getEnd());
    assertTrue(l.isCave());
  }

  @Test
  public void testZeroAmountDistributed() {
    Model d = new DungeonModel(new DungeonModel(3,5,1,
            0,false,0, 4, 1, 1));
    List<Location> temp = d.getLocationList();
    int countCavesExpected = 0;
    int countCavesActual = 0;
    for (Location l : temp) {
      if (l.isCave()) {
        countCavesActual++;
        if (l.getTreasuresList().isEmpty()) {
          countCavesExpected++;
        }
      }
    }
    assertEquals(countCavesExpected,countCavesActual);
  }

  @Test
  public void testTreasureAdded() {
    List<Location> temp = dungeonNonWrapping.getLocationList();
    List<TreasuresTypes> tr = temp.get(1).getTreasuresList();
    assertEquals("[Ruby]",tr.toString());
  }

  @Test
  public void testTreasureInTunnel() {
    List<Location> temp = dungeonNonWrapping.getLocationList();
    List<TreasuresTypes> tr = temp.get(3).getTreasuresList();
    assertFalse(temp.get(3).isCave());
    assertEquals("[]",tr.toString());
  }

  @Test
  public void testCavesWithMultipleTreasure() {
    List<Location> temp = dungeonNonWrapping.getLocationList();
    List<TreasuresTypes> tr = temp.get(15).getTreasuresList();
    assertEquals("[Sapphire, Ruby, Topaz]",tr.toString());
  }

  @Test
  public void testNoTreasureInCave() {
    List<Location> temp = dungeonNonWrapping.getLocationList();
    List<TreasuresTypes> tr = temp.get(0).getTreasuresList();
    assertEquals("[]",tr.toString());
  }

  @Test
  public void testMinNumOfCavesWithTreasure() {
    List<Location> temp = dungeonNonWrapping.getLocationList();
    int countCavesWithTreasure = 0;
    double countCavesActual = 0;
    for (Location l : temp) {
      if (l.isCave()) {
        countCavesActual++;
        if (!l.getTreasuresList().isEmpty()) {
          countCavesWithTreasure++;
        }
      }
    }
    double countCavesWithTreasureActual = Math.round(
            ((double) dungeonNonWrapping.getAmount() / (double) 100) * countCavesActual);
    assertTrue(countCavesWithTreasure >= countCavesWithTreasureActual);
  }

  @Test
  public void testCountCavesWithTreasure() {
    List<Location> temp = dungeonNonWrapping.getLocationList();
    int countCavesWithTreasure = 0;
    for (Location l : temp) {
      if (l.isCave()) {
        if (!l.getTreasuresList().isEmpty()) {
          countCavesWithTreasure++;
        }
      }
    }
    assertEquals(6,countCavesWithTreasure);
  }

  @Test
  public void testPlayerMoveNonWrapping() {
    assertEquals("{0={[South]=[]}}", dungeonNonWrapping.getCurrentLocation().toString());
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    assertEquals("{5={[North, South]=[]}}", dungeonNonWrapping.getCurrentLocation().toString());
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    assertEquals("{10={[East, North]=[]}}", dungeonNonWrapping.getCurrentLocation().toString());
  }

  @Test
  public void testPlayerMoveWrapping() {
    assertEquals("{19={[South]=[]}}", dungeonWrapping.getCurrentLocation().toString());
    dungeonWrapping.makeMove(Directions.SOUTH);
    assertEquals("{25={[North, East]=[]}}",
            dungeonWrapping.getCurrentLocation().toString());
    dungeonWrapping.makeMove(Directions.EAST);
    assertEquals("{26={[South, West, East, North]=[]}}",
            dungeonWrapping.getCurrentLocation().toString());
  }

  @Test
  public void testInvalidMoveNonWrapping() {
    assertFalse(dungeonNonWrapping.makeMove(Directions.NORTH));
  }

  @Test
  public void testInvalidMoveWrapping() {
    assertFalse(dungeonWrapping.makeMove(Directions.EAST));
  }

  @Test
  public void testMoveWrappingSide() {
    dungeonWrapping.makeMove(Directions.WEST);
    assertEquals("{19={[South]=[]}}", dungeonWrapping.getCurrentLocation().toString());
    dungeonWrapping.makeMove(Directions.SOUTH);
    assertEquals("{25={[North, East]=[]}}", dungeonWrapping.getCurrentLocation().toString());
  }

  @Test
  public void testTreasurePicked() {
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    assertFalse(dungeonNonWrapping.pickTreasure());
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.pickTreasure();
    dungeonNonWrapping.makeMove(Directions.EAST);
    dungeonNonWrapping.pickTreasure();
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.pickTreasure();
    dungeonNonWrapping.makeMove(Directions.WEST);
    assertEquals("{Sapphire=1, Diamond=0, Arrows=3, Topaz=0, Ruby=0}",
            dungeonNonWrapping.getDescription().toString());
  }

  @Test
  public void testNoTreasurePicked() {
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.EAST);
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.WEST);
    dungeonNonWrapping.makeMove(Directions.WEST);
    dungeonNonWrapping.makeMove(Directions.NORTH);
    dungeonNonWrapping.makeMove(Directions.NORTH);
    assertEquals("{Sapphire=0, Diamond=0, Arrows=3, Topaz=0, Ruby=0}",
            dungeonNonWrapping.getDescription().toString());
  }

  @Test
  public void testNumMonsters() {
    List<Location> nonWrappingLocationList = dungeonNonWrapping.getLocationList();
    int countMonstersNonWrapping = (int) nonWrappingLocationList.stream()
            .filter(Location::isMonsterAssigned).count();
    assertEquals(7,countMonstersNonWrapping);
    List<Location> wrappingLocationList = dungeonWrapping.getLocationList();
    int countMonstersWrapping = (int) wrappingLocationList.stream()
            .filter(Location::isMonsterAssigned).count();
    assertEquals(8,countMonstersWrapping);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumMonsters() {
    Model m1 = new DungeonModel(new DungeonModel(5,6,1,1,
            true, 40,0, 1, 1));
    thrown.expectMessage("Invalid number of monsters provided");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumMonstersMoreThanCave() {
    Model m1 = new DungeonModel(new DungeonModel(5,6,1,1,
            true, 40,23, 1, 1));
    thrown.expectMessage("Invalid number of monsters provided");
  }

  @Test
  public void testMonsterNotAtStartCave() {
    assertFalse(dungeonNonWrapping.getLocationList()
            .get(dungeonNonWrapping.getStart()).isMonsterAssigned());
    assertFalse(dungeonWrapping.getLocationList()
            .get(dungeonWrapping.getStart()).isMonsterAssigned());
  }

  @Test
  public void testMonsterAlwaysAtEndCave() {
    assertTrue(dungeonNonWrapping.getLocationList()
            .get(dungeonNonWrapping.getEnd()).isMonsterAssigned());
    assertTrue(dungeonWrapping.getLocationList()
            .get(dungeonWrapping.getEnd()).isMonsterAssigned());
  }

  @Test
  public void testNumArrows() {
    List<Location> loc = dungeonNonWrapping.getLocationList();
    int countLocWithArrows = 0;
    for (Location l : loc) {
      if (l.getArrowsCount() != 0) {
        countLocWithArrows++;
      }
    }
    int countTotalLocations = dungeonNonWrapping.getCols() * dungeonNonWrapping.getRows();
    int locsWithArrowsExpected = countTotalLocations * dungeonNonWrapping.getAmount() / 100;
    assertEquals(locsWithArrowsExpected,countLocWithArrows);
  }

  @Test
  public void testArrowsInitially() {
    assertEquals(3, dungeonWrapping.getNumArrows());
  }

  @Test
  public void testArrowsAfterPicking() {
    dungeonNonWrapping.pickArrow();
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    assertEquals(4,dungeonNonWrapping.getNumArrows());
  }

  @Test
  public void testArrowsAfterShooting() {
    dungeonWrapping.shoot(Directions.WEST, 1);
    dungeonWrapping.shoot(Directions.EAST,2);
    assertEquals(1,dungeonWrapping.getNumArrows());
  }

  @Test
  public void testShooting() {
    // shooting returns 1 if monster slayed
    // shooting returns 2 if monster is injured
    // shooting returns 3 if arrow went in dark
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.EAST);
    int temp1 = dungeonNonWrapping.shoot(Directions.EAST, 1);
    int temp2 = dungeonNonWrapping.shoot(Directions.EAST, 1);
    int temp3 = dungeonNonWrapping.shoot(Directions.EAST, 1);
    assertEquals(2,temp1); // Monster hit
    assertEquals(1,temp2); // Monster slayed
    assertEquals(3,temp3); // Arrow went in dark
  }

  @Test (expected = IllegalStateException.class)
  public void playerDies() {
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.SOUTH);
    dungeonNonWrapping.makeMove(Directions.EAST);
    dungeonNonWrapping.makeMove(Directions.EAST);
    dungeonNonWrapping.makeMove(Directions.EAST);
    thrown.expectMessage("Player is dead");
  }

  @Test
  public void testPlayerWins() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,1,
            true,50,1, 0, 1));
    m.makeMove(Directions.EAST);
    m.makeMove(Directions.SOUTH);
    m.makeMove(Directions.EAST);
    m.makeMove(Directions.SOUTH);
    m.makeMove(Directions.EAST);
    m.makeMove(Directions.SOUTH);
    m.shoot(Directions.WEST,1);
    int temp = m.shoot(Directions.WEST,1);
    assertEquals(1,temp); // end monster killed
    m.makeMove(Directions.WEST);
    assertTrue(m.isReachedDest());
  }

  @Test
  public void testPlayerSurvivedWithInjuredMonster() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,1,
            true,50,3, 0, 1));
    m.makeMove(Directions.EAST);
    m.makeMove(Directions.SOUTH);
    int temp = m.shoot(Directions.EAST, 1);
    assertEquals(2,temp); //Monster hit
    // After hitting monster the player moves in that direction and survives
    // with the injured monster.
    m.makeMove(Directions.EAST);
    assertTrue(m.isPlayerAlive());
  }

  @Test (expected = IllegalStateException.class)
  public void testPlayerKilledWithInjuredMonster() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,3, 1, 1));
    m.makeMove(Directions.EAST);
    int temp = m.shoot(Directions.SOUTH,1);
    assertEquals(2,temp); // The monster is shot
    // The player first survives the injured monster
    // Then the player tries to pick arrow and after picking up arrow from that location
    // with injured monster the player again tries to pick treasure and is killed by the monster.
    m.makeMove(Directions.SOUTH);
    m.pickArrow();
    m.pickTreasure();
    thrown.expectMessage("Player is dead");
  }

  @Test
  public void noMonsterInTunnel() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,3, 1, 1));
    m.makeMove(Directions.EAST);
    Map<Integer, Map<List<Directions>,List<TreasuresTypes>>> det = m.getCurrentLocation();
    int loc = 0;
    List<Directions> dir = new ArrayList<>();
    for (Map.Entry<Integer,Map<List<Directions>,List<TreasuresTypes>>> map : det.entrySet()) {
      Map<List<Directions>,List<TreasuresTypes>> map2 = map.getValue();
      loc = map.getKey();
      for (Map.Entry<List<Directions>,List<TreasuresTypes>> m2 : map2.entrySet()) {
        dir = m2.getKey();
      }
    }
    // verify that the current location is a tunnel
    assertEquals(2,dir.size());
    assertFalse(m.getLocationList().get(loc).isMonsterAssigned());
  }

  @Test
  public void testOutOfArrows() {
    // The shoot function returns 4 when the player is out of arrows.
    dungeonWrapping.shoot(Directions.NORTH,1);
    dungeonWrapping.shoot(Directions.SOUTH,2);
    dungeonWrapping.shoot(Directions.NORTH,1);
    int temp = dungeonWrapping.shoot(Directions.NORTH,1);
    assertEquals(4, temp);
  }

  @Test
  public void testArrowThroughTunnel() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,3, 0, 1));
    // Here the arrow first goes to tunnel in east and from that tunnel it goes to south and
    // hits the monster
    m.shoot(Directions.EAST,1);
    int temp = m.shoot(Directions.EAST,1);
    assertEquals(1,temp); // monster slayed
    // now the player is in tunnel which is verified by getting current location.
    m.makeMove(Directions.EAST);
    Map<Integer, Map<List<Directions>,List<TreasuresTypes>>> det = m.getCurrentLocation();
    List<Directions> dir = new ArrayList<>();
    for (Map.Entry<Integer,Map<List<Directions>,List<TreasuresTypes>>> map : det.entrySet()) {
      Map<List<Directions>,List<TreasuresTypes>> map2 = map.getValue();
      for (Map.Entry<List<Directions>,List<TreasuresTypes>> m2 : map2.entrySet()) {
        dir = m2.getKey();
      }
    }
    // The current location has 2 directions and so it is a tunnel
    assertEquals(2,dir.size());
    // Player is moved to south
    m.makeMove(Directions.SOUTH);
    Map<Integer, Map<List<Directions>,List<TreasuresTypes>>> det1 = m.getCurrentLocation();
    List<Directions> dir1 = new ArrayList<>();
    for (Map.Entry<Integer,Map<List<Directions>,List<TreasuresTypes>>> map : det1.entrySet()) {
      Map<List<Directions>,List<TreasuresTypes>> map2 = map.getValue();
      for (Map.Entry<List<Directions>,List<TreasuresTypes>> m2 : map2.entrySet()) {
        dir1 = m2.getKey();
      }
    }
    // Now after the movement the current location has 3 exits and so it is a cave.
    assertEquals(3,dir1.size());
    // This justifies that the arrow travels correctly from tunnel to cave and can slay the monster.

    // Verify that arrow travels correctly through two caves by shooting monster in end cave.
    m.makeMove(Directions.EAST);
    m.shoot(Directions.NORTH,2);
    int t = m.shoot(Directions.NORTH,2);
    assertEquals(4,t); // Monster slayed two units away
    // now the player is moved two caves and checked if the monster there is destroyed and
    // the player wins
    m.makeMove(Directions.NORTH);
    m.makeMove(Directions.NORTH);
    assertTrue(m.isReachedDest());
  }

  @Test
  public void testSmell() {
    // For strong smell the function returns 1 and for less pungent it returns 2.
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,3, 1, 1));
    int temp = m.detectSmell();
    // here it returns 2 because the monster is 2 units away as shown in previous test function.
    assertEquals(2,temp);
  }

  @Test
  public void testDetectPit() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,1, 1, 1));
    m.makeMove(Directions.EAST);
    assertTrue(m.detectPit());
  }

  @Test
  public void testTreasureStolen() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,80,1, 0, 3));
    m.pickTreasure();
    m.makeMove(Directions.EAST);
    assertEquals("{Sapphire=0, Diamond=0, Arrows=3, Topaz=1, Ruby=0}",
            m.getDescription().toString());
    m.makeMove(Directions.SOUTH);
    assertTrue(m.isTreasureStolen());
    assertEquals("{Arrows=3}",
            m.getDescription().toString());
  }

  @Test (expected = IllegalStateException.class)
  public void testKilledByPit() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,1, 1, 1));
    m.makeMove(Directions.EAST);
    assertTrue(m.detectPit());
    m.makeMove(Directions.SOUTH);
    m.makeMove(Directions.WEST);
  }
}