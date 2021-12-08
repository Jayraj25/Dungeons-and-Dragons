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
    List<Integer> specs = formView.getSpecs();
    model = new DungeonModel(new DungeonModel(4,5,2,
            1,true,50,2));
//    if (specs.get(3) == 1) {
//      model = new DungeonModel(new DungeonModel(specs.get(0), specs.get(1),
//              specs.get(2), 1, true, specs.get(4), specs.get(5)));
//    }
//    else {
//      model = new DungeonModel(new DungeonModel(specs.get(0), specs.get(1),
//              specs.get(2), 1, false, specs.get(4), specs.get(5)));
//    }
    this.formView.hideWindow();
    this.view = new DungeonWindow("Dungeon Game", model);
    view.display(
        model.getCurrentLocation(), model.getArrowAtCurrentLoc(), model.detectSmell(), true);
    view.setFeatures(this);
    view.resetFocus();
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
    int res = model.shoot(direction,distance);
    view.shootInfo(res);
    System.out.println();
  }

  @Override
  public void move(Directions direction) {
    boolean status = model.makeMove(direction);
    view.display(model.getCurrentLocation(),model.getArrowAtCurrentLoc(),model.detectSmell(),status);
    view.refresh();
  }

  @Override
  public void pickTreasure() {
    boolean flag = model.pickTreasure();
    if (flag) {
      view.addLabel("Treasure picked!");
    }
    else {
      view.addLabel("No Treasure to pick!");
    }
  }

  @Override
  public void pickArrow() {
    boolean flag = model.pickArrow();
    if (flag) {
      view.addLabel("Arrow picked!");
    }
    else {
      view.addLabel("No arrows to pick!");
    }
  }

  @Override
  public void getDescription() {
    view.getDescription(model.getDescription());
    System.out.println(model.getDescription());
  }
}
