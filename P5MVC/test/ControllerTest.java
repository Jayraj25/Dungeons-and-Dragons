import controller.DungeonConsoleController;
import controller.DungeonController;
import model.dungeon.DungeonModel;
import model.dungeon.Model;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

/** Tests to demonstrate the working of model through controller. */
public class ControllerTest {

  Model m1;
  Model m2;

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    m1 = new DungeonModel(new DungeonModel(5, 6, 1,
            1, true, 50, 8,1,1));
    m2 = new DungeonModel(new DungeonModel(5, 6, 2,
            0, false, 50, 8,1,1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    Readable input = new StringReader("w q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullReadable() {
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(null, gameLog);
    thrown.expectMessage("Readable and Appendable can't be null");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullAppendable() {
    Readable input = new StringReader("1 east q");
    DungeonController control = new DungeonConsoleController(input, null);
    thrown.expectMessage("Readable and Appendable can't be null");
  }

  @Test
  public void invalidCommands() {
    StringReader input = new StringReader("w q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(m1);
    assertTrue(gameLog.toString().contains("Unknown command: w"));
  }

  @Test
  public void testPickArrows() {
    Readable input = new StringReader("3 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(m1);
    assertTrue(gameLog.toString().contains("Player picked up the arrows"));
    assertTrue(gameLog.toString().contains("Arrows: 4"));
  }

  @Test
  public void testPickArrowsWhenNotAvailableArrow() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2,1,1));
    Readable input = new StringReader("3 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("No Arrows to pick"));
  }

  @Test
  public void testPickTreasure() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("2 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Picked the treasure!"));
    assertTrue(gameLog.toString().contains("Diamond: 1"));
    assertTrue(gameLog.toString().contains("Topaz: 1"));
  }

  @Test
  public void testPickTreasureWhenNotAvailable() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("1 east 2 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("No treasure to pick!"));
  }

  @Test
  public void testInvalidMoveInvalidDirection() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("1 ew q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Invalid direction provided"));
  }

  @Test
  public void testInvalidMoveDirectionNotAvailableToMove() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("1 west q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Cannot move in the given direction"));
  }

  @Test
  public void testValidMove() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("1 east q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Move made successfully"));
  }

  @Test
  public void testInvalidShootInvalidDirection() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("4 wer 1 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Invalid direction provided"));
  }

  @Test
  public void testInvalidShootInvalidDistance() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("4 east 0 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Invalid distance provided"));
  }

  @Test
  public void testInvalidDistanceInShootNumberFormatExp() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("4 east w q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Invalid input: For input string: \"w\""));
  }

  @Test
  public void testInvalidDistanceInShoot() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 2, 1, 1));
    Readable input = new StringReader("4 east 8 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Invalid distance provided"));
  }

  @Test
  public void testValidShootMonsterHit() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 0, 1));
    Readable input = new StringReader("1 east 1 south 1 east 1 east 4 east 1 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Monster hit successfully!"));
  }

  @Test
  public void testValidShootMonsterSlayed() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 0, 1));
    Readable input = new StringReader("1 east 1 south 1 east 1 east 4 east 1 4 east 1 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Monster slayed!"));
  }

  @Test
  public void testValidShootArrowInDark() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 1, 1));
    Readable input = new StringReader("1 east 1 south 1 east 4 east 1 5 4 east 1 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Arrow went in dark!"));
  }

  @Test
  public void testArrowsAfterShoot() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 1, 1));
    Readable input = new StringReader("5 1 east 1 south 1 east 4 east 1 5 4 east 1 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Arrows: 3"));
    assertTrue(gameLog.toString().contains("Arrows: 2"));
    assertTrue(gameLog.toString().contains("Arrows: 1"));
  }

  @Test
  public void testShootWhenNoArrowsLeft() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 1, 1));
    Readable input = new StringReader("1 east 4 east 1 4 east 1 4 east 1 4 east 1 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Out of Arrows!!"));
  }

  @Test
  public void testPlayerWinsReachEndKillMonster() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 1, 0, 1));
    Readable input =
        new StringReader(
            "1 east 1 south 1 east 1 south 1 east 1 south" + " 4 west 1 4 west 1 1 west q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Monster slayed!"));
    assertTrue(gameLog.toString().contains("Reached the end!"));
    assertTrue(gameLog.toString().contains("You won the game"));
    assertTrue(gameLog.toString().contains("Game over!"));
  }

  @Test
  public void testPlayerKilled() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 1, 0, 1));
    Readable input = new StringReader("1 east 1 south 1 east 1 south 1 east 1 south" + " 1 west");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Chomp, chomp, chomp, you are eaten by an Otyugh!"));
    assertTrue(gameLog.toString().contains("Game over!"));
  }

  @Test
  public void testPlayerKilledWithInjuredMonster() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 1, 1));
    // Here after shooting in south direction for 1 cave the monster is hit
    // And then the player is moved in that south direction and luckily player survives.
    // Then again a player tries to pick treasure here and after picking up he gets killed
    // by the monster based on the 50% survival chances
    Readable input = new StringReader("1 east 1 south 1 east 1 south 1 east 4 south 1 1 south 2");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("Monster hit successfully!"));
    assertTrue(gameLog.toString().contains("Chomp, chomp, chomp, you are eaten by an Otyugh!"));
    assertTrue(gameLog.toString().contains("Game over!"));
  }

  @Test
  public void testPlayerSurvivedWithInjuredMonster() {
    Model model = new DungeonModel(new DungeonModel(3, 4, 1,
            1, true, 20, 3, 1, 1));
    // Here after shooting in south direction for 1 cave the monster is hit
    // And then the player is moved in that south direction and luckily player survives
    // based on 50% survival chances.
    Readable input =
        new StringReader("1 east 1 south 1 east 1 south 1 east 4 south 1 " + "1 south q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(model);
    assertTrue(gameLog.toString().contains("You smell something terrible nearby!!"));
    assertTrue(gameLog.toString().contains("Monster hit successfully!"));
  }

  @Test
  public void testDetectPit() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,1, 1, 1));
    Readable input =
            new StringReader("1 east q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(m);
    assertTrue(gameLog.toString().contains("Beware!! A pit is nearby"));
  }

  @Test
  public void testKilledByPit() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,50,1, 1, 1));
    Readable input =
            new StringReader("1 east 1 south q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(m);
    assertTrue(gameLog.toString().contains("You fell into the pit!\n" + "Game over!"));
  }

  @Test
  public void testTreasureStolen() {
    Model m = new DungeonModel(new DungeonModel(3,4,1,0,
            true,80,1, 0, 3));
    Readable input =
            new StringReader("2 5 1 east 1 south 5 q");
    Appendable gameLog = new StringBuilder();
    DungeonController control = new DungeonConsoleController(input, gameLog);
    control.playGame(m);
    assertTrue(
        gameLog
            .toString()
            .contains(
                "The following are the treasures and arrows the player currently have:\n"
                    + "Arrows: 3\n"
                    + "Topaz: 1"));
    assertTrue(gameLog.toString().contains("Thief encountered and your treasure was stolen!"));
    assertTrue(
        gameLog
            .toString()
            .contains(
                "The following are the treasures and arrows the player currently have:\n"
                    + "Arrows: 3"));
  }
}
