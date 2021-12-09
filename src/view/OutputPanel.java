package view;

import model.dungeon.Directions;
import model.dungeon.TreasuresTypes;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This panel displays the current location details and details of the event performed by player.
 */
class OutputPanel extends JPanel {

  public OutputPanel() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setVisible(true);
    setBackground(Color.white);
    JLabel entry = new JLabel("Game Log");
    entry.setAlignmentX(CENTER_ALIGNMENT);
    add(entry);
  }

  public void displayCurrentLocInfo(
      Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> currentLocation,
      int arrowAtCurrentLoc,
      int smell,
      boolean status) {
    JLabel move;
    JLabel line = new JLabel("----------");
    line.setSize(100, 100);
    line.setAlignmentX(CENTER_ALIGNMENT);
    add(line);
    JLabel desc = new JLabel("Current Location Details");
    desc.setAlignmentX(CENTER_ALIGNMENT);
    add(desc);
    JLabel line1 = new JLabel("----------------------");
    line1.setAlignmentX(CENTER_ALIGNMENT);
    add(line1);
    if (!status) {
      move = new JLabel("Cannot move here", SwingConstants.CENTER);
      move.setAlignmentX(CENTER_ALIGNMENT);
      add(move);
    }
    List<TreasuresTypes> treasureList = new ArrayList<>();
    List<Directions> directionsList = new ArrayList<>();
    for (Map.Entry<Integer, Map<List<Directions>, List<TreasuresTypes>>> m1 :
        currentLocation.entrySet()) {
      Map<List<Directions>, List<TreasuresTypes>> val = m1.getValue();
      for (Map.Entry<List<Directions>, List<TreasuresTypes>> m2 : val.entrySet()) {
        directionsList = m2.getKey();
        treasureList = m2.getValue();
      }
    }
    displayTreasure(treasureList);
    if (arrowAtCurrentLoc != 0) {
      addArrow(arrowAtCurrentLoc);
    }
    ImageIcon movement =
        new ImageIcon(
            new ImageIcon("res/dungeon-images/movement.png")
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    JLabel directions = new JLabel(directionsList.toString(), movement, SwingConstants.CENTER);
    directions.setAlignmentX(CENTER_ALIGNMENT);
    detectSmell(smell);
    add(directions);
    revalidate();
    repaint();
  }

  private void addArrow(int arrowAtCurrentLoc) {
    ImageIcon arrow =
        new ImageIcon(
            new ImageIcon("res/dungeon-images/shooting-arrow.png")
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    JLabel arrowLabel = new JLabel(String.valueOf(arrowAtCurrentLoc), arrow, SwingConstants.CENTER);
    arrowLabel.setAlignmentX(CENTER_ALIGNMENT);
    add(arrowLabel);
  }

  private void detectSmell(int smell) {
    if (smell != 0) {
      JLabel smellLabel;
      ImageIcon smellIcon =
          new ImageIcon(
              new ImageIcon("res/dungeon-images/danger.png")
                  .getImage()
                  .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
      if (smell == 1) {
        smellLabel = new JLabel("Very pungent smell nearby!", smellIcon, SwingConstants.CENTER);
        smellLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(smellLabel);
      } else if (smell == 2) {
        smellLabel = new JLabel("Pungent smell!", smellIcon, SwingConstants.CENTER);
        smellLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(smellLabel);
      }
    }
  }

  private void displayTreasure(List<TreasuresTypes> treasureList) {
    int countDiamond = 0;
    int countRuby = 0;
    int countTopaz = 0;
    int countSapphire = 0;
    for (TreasuresTypes t : treasureList) {
      if (t.equals(TreasuresTypes.RUBY)) {
        countRuby++;
      }
      if (t.equals(TreasuresTypes.TOPAZ)) {
        countTopaz++;
      }
      if (t.equals(TreasuresTypes.SAPPHIRE)) {
        countSapphire++;
      }
      if (t.equals(TreasuresTypes.DIAMOND)) {
        countDiamond++;
      }
    }
    if (countDiamond != 0) {
      ImageIcon diamond =
          new ImageIcon(
              new ImageIcon("res/dungeon-images/diamond.png")
                  .getImage()
                  .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
      JLabel diamondLabel =
          new JLabel(String.valueOf(countDiamond), diamond, SwingConstants.CENTER);
      diamondLabel.setAlignmentX(CENTER_ALIGNMENT);
      add(diamondLabel);
    }
    if (countRuby != 0) {
      ImageIcon ruby =
          new ImageIcon(
              new ImageIcon("res/dungeon-images/ruby.png")
                  .getImage()
                  .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
      JLabel rubyLabel = new JLabel(String.valueOf(countRuby), ruby, SwingConstants.CENTER);
      rubyLabel.setAlignmentX(CENTER_ALIGNMENT);
      add(rubyLabel);
    }
    if (countSapphire != 0) {
      ImageIcon sapphire =
          new ImageIcon(
              new ImageIcon("res/dungeon-images/sapphire.png")
                  .getImage()
                  .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
      JLabel sapphireLabel =
          new JLabel(String.valueOf(countSapphire), sapphire, SwingConstants.CENTER);
      sapphireLabel.setAlignmentX(CENTER_ALIGNMENT);
      add(sapphireLabel);
    }
    if (countTopaz != 0) {
      ImageIcon topaz =
          new ImageIcon(
              new ImageIcon("res/dungeon-images/emerald.png")
                  .getImage()
                  .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
      JLabel topazLabel = new JLabel(String.valueOf(countTopaz), topaz, SwingConstants.CENTER);
      topazLabel.setAlignmentX(CENTER_ALIGNMENT);
      add(topazLabel);
    }
  }

  public void addLabel(String s) {
    JLabel pick = new JLabel(s);
    pick.setAlignmentX(CENTER_ALIGNMENT);
    add(pick);
    revalidate();
    repaint();
  }
}
