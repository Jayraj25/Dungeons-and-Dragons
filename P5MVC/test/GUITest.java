import org.junit.Before;
import org.junit.Test;

import controller.DungeonControllerImpl;
import controller.Features;
import model.dungeon.Directions;
import model.dungeon.MockModel;
import view.DungeonView;
import view.MockFormView;
import view.MockView;

import static org.junit.Assert.assertEquals;

/**
 * GUI tests for the MVC game.
 */
public class GUITest {

  StringBuilder log;

  MockModel model;
  MockFormView formView;
  DungeonView view;
  Features controller;

  @Before
  public void setUp() {
    log = new StringBuilder();
    model = new MockModel(5,5,1,1,1,1,1,1,log);
    formView = new MockFormView(log);
    view = new MockView(log);
    controller = new DungeonControllerImpl(formView);
    controller.setModel(model);
    controller.setView(view);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    Features tempControl = new DungeonControllerImpl(formView);
    tempControl.setModel(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullView() {
    Features tempControl = new DungeonControllerImpl(formView);
    tempControl.setView(null);
  }

  @Test
  public void testFeatures() {
    assertEquals("setFeatures method was called in view\n",log.toString());
  }

  @Test
  public void testMove1() {
    controller.move(Directions.WEST);
    String temp =
        "setFeatures method was called in view\n"
            + "isReachedDest method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "displayLoseInfo method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testMove2() {
    controller.move(Directions.WEST);
    controller.move(Directions.SOUTH);
    String temp =
        "setFeatures method was called in view\n"
            + "isReachedDest method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "displayLoseInfo method was called in view\n"
            + "isReachedDest method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "displayLoseInfo method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testPickTreasure() {
    controller.pickTreasure();
    String temp =
        "setFeatures method was called in view\n"
            + "pickTreasure method called.\n"
            + "addLabel method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testPickArrow() {
    controller.pickArrow();
    String temp =
        "setFeatures method was called in view\n"
            + "pickArrow method called.\n"
            + "addLabel method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testExitProgram() {
    controller.exitProgram();
    String temp =
        "setFeatures method was called in view\n" + "exitGame method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testGetInformation() {
    controller.getInformation();
    String temp =
        "setFeatures method was called in view\n"
            + "getDungeonLabel method called.\n"
            + "getRows method called.\n"
            + "getCols method called.\n"
            + "getInterconnectivity method called.\n"
            + "getAmount method called.\n"
            + "getNumOtyughs method called.\n"
            + "displayInfo method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testShoot() {
    controller.shoot(Directions.WEST,1);
    String temp =
        "setFeatures method was called in view\n"
            + "shoot method called.\n"
            + "shootInfo method was called in view\n"
            + "getCurrentLocation method called.\n"
            + "getArrowAtCurrentLoc method called.\n"
            + "detectSmell method called.\n"
            + "display method was called in view\n"
            + "refresh method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testDescription() {
    controller.getDescription();
    String temp =
        "setFeatures method was called in view\n"
            + "getDescription method called.\n"
            + "getDescription method was called in view\n";
    assertEquals(temp,log.toString());
  }

  @Test
  public void testMoveClickListener() {
    controller.moveClickListener(10,10);
    String temp =
        "setFeatures method was called in view\n"
            + "isReachedDest method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isReachedDest method called.\n"
            + "isPlayerAlive method called.\n"
            + "isKilledByPit method called.\n"
            + "displayLoseInfo method was called in view\n";
    assertEquals(temp,log.toString());
  }
}