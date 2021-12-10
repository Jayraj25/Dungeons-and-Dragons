package view;

import model.dungeon.Directions;
import model.dungeon.ReadOnlyModel;
import model.dungeon.TreasuresTypes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This view is created to display the dungeon as the player explores the game.
 */
public class DungeonPanel extends JPanel {

  private final ReadOnlyModel model;
  private final Map<List<Integer>, String> pathMap = new HashMap<>();

  /**
   * Constructs the dungeon panel.
   * @param m the model
   */
  public DungeonPanel(ReadOnlyModel m) {
    this.model = m;
    setPreferredSize(new Dimension(2000,2000));
    this.setLayout(null);
  }

  private String getPath(List<Directions> direction) {
    String srcPath = "/color-cells/";
    if (direction.contains(Directions.NORTH)) {
      srcPath += "N";
    }
    if (direction.contains(Directions.SOUTH)) {
      srcPath += "S";
    }
    if (direction.contains(Directions.EAST)) {
      srcPath += "E";
    }
    if (direction.contains(Directions.WEST)) {
      srcPath += "W";
    }
    return srcPath + ".png";
  }

  // The paintComponent method is called every time
  // that the panel needs to be displayed or refreshed.
  // Anything you want drawn on the panel should be drawn
  // in this method.
  @Override
  public void paintComponent(Graphics page) {
    Graphics2D g2d = (Graphics2D) page;
    super.paintComponent(g2d);
    String path = "/color-cells/NS.png";
    Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> map = model.getCurrentLocation();
    for (Map.Entry<Integer, Map<List<Directions>, List<TreasuresTypes>>> m1 : map.entrySet()) {
      Map<List<Directions>, List<TreasuresTypes>> val = m1.getValue();
      for (Map.Entry<List<Directions>, List<TreasuresTypes>> m2 : val.entrySet()) {
        path = getPath(m2.getKey());
      }
    }
    BufferedImage img = null;
    pathMap.put(model.getPlayerLocRowColIndex(),path);
    try {
      for (Map.Entry<List<Integer>, String> m : pathMap.entrySet()) {
        List<Integer> indexes = m.getKey();
        InputStream imageStream = getClass().getResourceAsStream(m.getValue());
        img = ImageIO.read(imageStream);
        g2d.drawImage(img,indexes.get(1) * img.getWidth(this),
                indexes.get(0) * img.getHeight(this), null);
        g2d.drawLine(model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * img.getHeight(this),
                (model.getPlayerLocRowColIndex().get(1) * img.getWidth(this))
                        + img.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * img.getHeight(this));
        g2d.drawLine((model.getPlayerLocRowColIndex().get(1) * img.getWidth(this))
                        + img.getWidth(this),
                (model.getPlayerLocRowColIndex().get(0) * img.getHeight(this)),
                (model.getPlayerLocRowColIndex().get(1) * img.getWidth(this))
                        + img.getWidth(this),
                (model.getPlayerLocRowColIndex().get(0) * img.getHeight(this))
                        + img.getHeight(this));
        g2d.drawLine((model.getPlayerLocRowColIndex().get(1) * img.getWidth(this))
                        + img.getWidth(this),
                (model.getPlayerLocRowColIndex().get(0) * img.getHeight(this))
                        + img.getHeight(this),
                model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                (model.getPlayerLocRowColIndex().get(0) * img.getHeight(this))
                        + img.getHeight(this));
        g2d.drawLine(model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                (model.getPlayerLocRowColIndex().get(0) * img.getHeight(this))
                        + img.getHeight(this),
                model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * img.getHeight(this));
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(2f));
      }
      int smell = model.detectSmell();
      if (smell == 1) {
        InputStream stenchStream = getClass().getResourceAsStream("/stench02.png");
        final BufferedImage imgSmell = ImageIO.read(stenchStream);
        g2d.drawImage(imgSmell,
                model.getPlayerLocRowColIndex().get(1) * imgSmell.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * imgSmell.getHeight(this),
                null);
      }
      else if (smell == 2) {
        InputStream stenchStream = getClass().getResourceAsStream("/stench01.png");
        final BufferedImage imgSmell = ImageIO.read(stenchStream);
        g2d.drawImage(imgSmell,
                model.getPlayerLocRowColIndex().get(1) * imgSmell.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * imgSmell.getHeight(this),
                null);
      }
      if (!model.isPlayerAlive() && !model.isKilledByPit()) {
        InputStream otyughStream = getClass().getResourceAsStream("/otyugh.png");
        final BufferedImage imgOtyugh = ImageIO.read(otyughStream);
        g2d.drawImage(imgOtyugh,
                model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * img.getHeight(this),
                50,50,null);
      }
      if (!model.isPlayerAlive() && model.isKilledByPit()) {
        InputStream pitStream = getClass().getResourceAsStream("/pit.png");
        final BufferedImage imgPit = ImageIO.read(pitStream);
        g2d.drawImage(imgPit,
                model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * img.getHeight(this),
                50,50,null);
      }

      else if (model.isTreasureStolen()) {
        InputStream robberStream = getClass().getResourceAsStream("/robber.png");
        final BufferedImage imgRobber = ImageIO.read(robberStream);
        g2d.drawImage(imgRobber,
                model.getPlayerLocRowColIndex().get(1) * img.getWidth(this),
                model.getPlayerLocRowColIndex().get(0) * img.getHeight(this),
                50,50,null);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    setBackground(Color.BLACK);
  }
}
