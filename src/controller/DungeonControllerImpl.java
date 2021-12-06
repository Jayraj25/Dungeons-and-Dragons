package controller;

import java.util.List;

import javax.swing.*;

import model.dungeon.DungeonModel;
import model.dungeon.Model;
import view.DungeonView;
import view.DungeonWindow;
import view.SpecsForm;

public class DungeonControllerImpl implements Features {

  private Model model;
  private DungeonView view;

  public DungeonControllerImpl(DungeonView v) {
    view = v;
    view.setFeatures(this);
    initView();
  }

  @Override
  public void playGame(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
  }

  public void initView() {
//    view.getSub().addActionListener(e -> createModel());
  }

  public void createModel() {
    List<Integer> specs = view.getSpecs();
    model = new DungeonModel(new DungeonModel(4,5,2,
            1,true,20,3));
//    if (specs.get(3) == 1) {
//      model = new DungeonModel(new DungeonModel(specs.get(0), specs.get(1),
//              specs.get(2), 1, true, specs.get(4), specs.get(5)));
//    }
//    else {
//      model = new DungeonModel(new DungeonModel(specs.get(0), specs.get(1),
//              specs.get(2), 1, false, specs.get(4), specs.get(5)));
//    }
    this.view = new DungeonWindow("Dungeon Game");
    view.setFeatures(this);
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
    createModel();
  }

  @Override
  public void restartNewGame() {
    new DungeonControllerImpl(new SpecsForm("MVC Game"));
//    this.view = new SpecsForm("MVC Game");
  }

  @Override
  public void getInformation() {
    String info =    "Number of Rows:          " + model.getRows()
            + "\n" + "Number of Columns:     " + model.getCols()
            + "\n" + "Interconnectivity:          " + model.interConnectivity();
    String type = model.getDungeonLabel() ? "Wrapping" : "Non Wrapping";
    String display = info
            + "\n" + "Dungeon Type:           " + type
            + "\n" + "Amount distributed:     " + model.getAmount()
            + "\n" + "Number of Monsters:    " + model.getNumOtyughs();
    JOptionPane.showMessageDialog(null,display);
  }
}
