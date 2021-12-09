package controller;

import java.util.List;

import javax.swing.*;

import model.dungeon.Directions;
import model.dungeon.DungeonModel;
import model.dungeon.Model;
import view.DungeonForm;
import view.DungeonView;
import view.DungeonWindow;
import view.SpecsForm;

public class DungeonControllerImpl implements Features {

  private Model model;
  private DungeonForm formView;
  private DungeonView view;
  List<Integer> specs;

  public DungeonControllerImpl(DungeonForm v) {
    formView = v;
    formView.setFeatures(this);
  }

  @Override
  public void playGame(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
  }

  public void createModel() {
    specs = formView.getSpecs();
//    model = new DungeonModel(new DungeonModel(4,5,2,
//            1,true,50,2));
    try {
      if (specs.get(3) == 1) {
        model = new DungeonModel(new DungeonModel(specs.get(0), specs.get(1),
                specs.get(2), 1, true, specs.get(4), specs.get(5), specs.get(6), specs.get(7)));
      }
      else {
        model = new DungeonModel(new DungeonModel(specs.get(0), specs.get(1),
                specs.get(2), 1, false, specs.get(4), specs.get(5), specs.get(6), specs.get(7)));
      }
      this.formView.hideWindow();
      this.view = new DungeonWindow("Dungeon Game", model);
      view.display(
              model.getCurrentLocation(), model.getArrowAtCurrentLoc(), model.detectSmell(), true);
      view.setFeatures(this);
      view.resetFocus();
    }
    catch (IndexOutOfBoundsException ies) {
      // Do nothing
    }
  }

  @Override
  public void exitProgram() {
    Object[] options = {"Exit","No"};
    int res = JOptionPane.showOptionDialog(null,
            "Are you sure to quit the game?",
            "Exit Game",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
            null,options,null);
    if (res == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  @Override
  public void restartSameGame() {
    this.view.hideWindow();
    createModel();
  }

  @Override
  public void restartNewGame() {
    this.view.hideWindow();
    new DungeonControllerImpl(new SpecsForm("MVC Game"));
  }

  @Override
  public void getInformation() {
    String type = model.getDungeonLabel() ? "Wrapping" : "Non Wrapping";
    view.displayInfo(model.getRows(),model.getCols(),model.interConnectivity(),
            type, model.getAmount(),model.getNumOtyughs());
  }

  @Override
  public void shoot(Directions direction, int distance) {
    try {
      int res = model.shoot(direction,distance);
      view.shootInfo(res);
      view.display(model.getCurrentLocation(),
              model.getArrowAtCurrentLoc(),model.detectSmell(),true);
      view.refresh();
    }
    catch (IllegalStateException ies) {
      view.displayLoseInfo();
    }
  }

  @Override
  public void move(Directions direction) {
    if (model.isReachedDest() && !model.isPlayerAlive() && !model.isKilledByPit()) {
      view.displayLoseInfo();
    }
    else if (!model.isReachedDest() && !model.isPlayerAlive() && model.isKilledByPit()) {
      view.displayLoseInfoByPit();
    }
    else if (model.isReachedDest() && model.isPlayerAlive()){
      view.displayWinInfo();
    }
    else {
      moveHelper(direction);
    }
  }

  private void moveHelper(Directions direction) {
    boolean status = model.makeMove(direction);
    view.display(model.getCurrentLocation(),model.getArrowAtCurrentLoc(),
            model.detectSmell(),status);
    view.refresh();
    if (model.detectPit()) {
      view.displayPitInfo();
    }
    if (model.isReachedDest() && model.isPlayerAlive()) {
      view.displayWinInfo();
    }
    else if (model.isReachedDest() && !model.isPlayerAlive() && model.isKilledByPit()) {
      view.displayLoseInfoByPit();
    }
    else if (model.isReachedDest() && !model.isPlayerAlive() && !model.isKilledByPit()) {
      view.displayLoseInfo();
    }
    else if (!model.isReachedDest() && !model.isPlayerAlive() && !model.isKilledByPit()) {
      view.displayLoseInfo();
    }
    else if (!model.isReachedDest() && !model.isPlayerAlive() && model.isKilledByPit()) {
      view.displayLoseInfoByPit();
    }
  }

  @Override
  public void moveClickListener(int x, int y) {
    if (model.isReachedDest() && !model.isPlayerAlive()) {
      view.displayLoseInfo();
    }
    else if (!model.isReachedDest() && model.isPlayerAlive()) {
      List<Integer> locCoordinates = model.getPlayerLocRowColIndex();
      Directions direction = null;
      if (specs.get(3) == 1) {
        if (locCoordinates.get(1) == model.getCols() - 1) {
          if (((locCoordinates.get(0) * 64) + 64) > y && y > (locCoordinates.get(0) * 64)
                  && 0 < x && x < 64) {
            direction = Directions.EAST;
          }
        }
        if (locCoordinates.get(1) == 0) {
          if (((locCoordinates.get(0) * 64) + 64) > y && y > (locCoordinates.get(0) * 64)
                  && (model.getCols() - 1) * 64 < x && x < (((model.getCols() - 1) * 64) + 64)) {
            direction = Directions.WEST;
          }
        }
        if (locCoordinates.get(0) == model.getRows() - 1) {
          if (((locCoordinates.get(1) * 64) + 64) > x && x > (locCoordinates.get(1) * 64)
                  && 0 < y && y < 64) {
            direction = Directions.SOUTH;
          }
        }
        if (locCoordinates.get(0) == 0) {
          if ((model.getRows()) * 64  > y && y > (model.getRows() - 1) * 64
                  && ((locCoordinates.get(1) * 64) + 64) > x && x > (locCoordinates.get(1) * 64)) {
            direction = Directions.NORTH;
          }
        }
        direction = getDirections(x, y, locCoordinates, direction);
      }
      else {
        direction = getDirections(x, y, locCoordinates, null);
      }
      moveHelper(direction);
    }
    else if (!model.isReachedDest() && !model.isPlayerAlive() && !model.isKilledByPit()) {
      view.displayLoseInfo();
    }
    else if (!model.isReachedDest() && !model.isPlayerAlive() && model.isKilledByPit()) {
      view.displayLoseInfoByPit();
    }
    else if (model.isReachedDest() && !model.isPlayerAlive() && model.isKilledByPit()) {
      view.displayLoseInfoByPit();
    }
    else if (model.isReachedDest() && !model.isPlayerAlive() && !model.isKilledByPit()) {
      view.displayLoseInfo();
    }
    else if (model.isReachedDest() && model.isPlayerAlive()) {
      view.displayWinInfo();
    }
  }

  private Directions getDirections(int x, int y, List<Integer> locCoordinates, Directions direction) {
    if (((locCoordinates.get(1) * 64) - 64) < x && x < (locCoordinates.get(1) * 64)
            && (locCoordinates.get(0) * 64) < y && y < (locCoordinates.get(0) * 100) + 64) {
      direction = Directions.WEST;
    } else if (((locCoordinates.get(1) * 64) + 128) > x && x > (locCoordinates.get(1) * 64) + 64
            && (locCoordinates.get(0) * 64) < y && y < (locCoordinates.get(0) * 64) + 64) {
      direction = Directions.EAST;
    } else if ((locCoordinates.get(1) * 64) < x && x < (locCoordinates.get(1) * 64) + 64
            && ((locCoordinates.get(0) * 64) - 64) < y && y < (locCoordinates.get(0) * 64)) {
      direction = Directions.NORTH;
    } else if ((locCoordinates.get(1) * 64) < x && x < (locCoordinates.get(1) * 64) + 64
            && ((locCoordinates.get(0)* 100) + 128) > y && y > (locCoordinates.get(0) * 64)) {
      direction = Directions.SOUTH;
    }
    return direction;
  }

  @Override
  public void pickTreasure() {
    try {
      boolean flag = model.pickTreasure();
      if (flag) {
        view.addLabel("Treasure picked!");
      }
      else {
        view.addLabel("No Treasure to pick!");
      }
    }
    catch (IllegalStateException ies) {
      view.displayLoseInfo();
    }
  }

  @Override
  public void pickArrow() {
    try{
      boolean flag = model.pickArrow();
      if (flag) {
        view.addLabel("Arrow picked!");
      }
      else {
        view.addLabel("No arrows to pick!");
      }
    }
    catch (IllegalStateException ies) {
      view.displayLoseInfo();
    }
  }

  @Override
  public void getDescription() {
    view.getDescription(model.getDescription());
  }
}
