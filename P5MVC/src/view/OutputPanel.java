package view;

import model.dungeon.Directions;
import model.dungeon.TreasuresTypes;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This panel displays the current location details which contains treasures, list of directions
 * for movement and details of the event performed by player.
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
    if (directionsList.size() == 2) {
      JLabel locInfo = new JLabel("You are in a tunnel");
      locInfo.setAlignmentX(CENTER_ALIGNMENT);
      add(locInfo);
    }
    else {
      JLabel locInfo = new JLabel("You are in a cave");
      locInfo.setAlignmentX(CENTER_ALIGNMENT);
      add(locInfo);
    }
    displayTreasure(treasureList);
    if (arrowAtCurrentLoc != 0) {
      addArrow(arrowAtCurrentLoc);
    }
    InputStream movementStream = getClass().getResourceAsStream("/movement.png");
    try {
      BufferedImage buffMovement = ImageIO.read(movementStream);
      Image movement = buffMovement.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(movement);
      JLabel directions = new JLabel(directionsList.toString(), tIcon, SwingConstants.CENTER);
      directions.setAlignmentX(CENTER_ALIGNMENT);
      detectSmell(smell);
      add(directions);
    } catch (IOException e) {
      e.printStackTrace();
    }
    revalidate();
    repaint();
  }

  private void addArrow(int arrowAtCurrentLoc) {
    InputStream arrowStream = getClass().getResourceAsStream("/shooting-arrow.png");
    try {
      BufferedImage buffArrow = ImageIO.read(arrowStream);
      Image movement = buffArrow.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(movement);
      JLabel arrowLabel = new JLabel(String.valueOf(arrowAtCurrentLoc),
              tIcon, SwingConstants.CENTER);
      arrowLabel.setAlignmentX(CENTER_ALIGNMENT);
      add(arrowLabel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void detectSmell(int smell) {
    if (smell != 0) {
      InputStream dangerStream = getClass().getResourceAsStream("/danger.png");
      ImageIcon tIcon = null;
      try {
        BufferedImage buffDanger = ImageIO.read(dangerStream);
        Image movement = buffDanger.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        tIcon = new ImageIcon(movement);
      } catch (IOException e) {
        e.printStackTrace();
      }
      JLabel smellLabel;
      if (smell == 1) {
        smellLabel = new JLabel("Very pungent smell nearby!", tIcon, SwingConstants.CENTER);
        smellLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(smellLabel);
      } else if (smell == 2) {
        smellLabel = new JLabel("Pungent smell!", tIcon, SwingConstants.CENTER);
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
    try {
      if (countDiamond != 0) {
        InputStream treasureStream = getClass().getResourceAsStream("/diamond.png");
        treasureIconHelper(countDiamond,treasureStream);
      }
      if (countRuby != 0) {
        InputStream treasureStream = getClass().getResourceAsStream("/ruby.png");
        treasureIconHelper(countRuby,treasureStream);
      }
      if (countSapphire != 0) {
        InputStream treasureStream = getClass().getResourceAsStream("/sapphire.png");
        treasureIconHelper(countSapphire,treasureStream);
      }
      if (countTopaz != 0) {
        InputStream treasureStream = getClass().getResourceAsStream("/emerald.png");
        treasureIconHelper(countTopaz,treasureStream);
      }
    }
    catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private void treasureIconHelper(Integer value,
                                  InputStream treasureStream) throws IOException {
    BufferedImage buffTreasure = ImageIO.read(treasureStream);
    Image treasure = buffTreasure.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon tIcon = new ImageIcon(treasure);
    JLabel label = new JLabel(String.valueOf(value), tIcon, SwingConstants.CENTER);
    label.setAlignmentX(CENTER_ALIGNMENT);
    add(label);
  }

  public void addLabel(String s) {
    JLabel pick = new JLabel(s);
    pick.setAlignmentX(CENTER_ALIGNMENT);
    add(pick);
    revalidate();
    repaint();
  }
}
