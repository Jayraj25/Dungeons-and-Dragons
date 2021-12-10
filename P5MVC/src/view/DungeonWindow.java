package view;

import controller.Features;
import model.dungeon.Directions;
import model.dungeon.Model;
import model.dungeon.ReadOnlyModel;
import model.dungeon.TreasuresTypes;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * Create the view once the parameters to create dungeon are submitted.
 */
public class DungeonWindow extends JFrame implements DungeonView {

  private final JMenuItem quit;
  private final JMenuItem restart;
  private final JMenuItem restartNewGame;
  private final JMenuItem getInfo;
  private final DungeonPanel mainWindow;
  private final OutputPanel sideWindow;
  private boolean shootFlag;

  /**
   * Construct the actual game window with the parameters from previous window.
   * @param title The title of the window
   * @throws HeadlessException exception if no title given
   */
  public DungeonWindow(String title, ReadOnlyModel model) throws HeadlessException {
    super(title);
    setBounds(300, 90, 1000, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.shootFlag = false;
    quit = new JMenuItem("Exit Game");
    restart = new JMenuItem("Restart Game");
    restartNewGame = new JMenuItem("New Game");
    getInfo = new JMenuItem("Game Information");
    JMenu preferences = new JMenu("Preferences");
    preferences.add(quit);
    preferences.add(restart);
    preferences.add(restartNewGame);
    JMenu gameSettings = new JMenu("Settings");
    gameSettings.add(getInfo);
    JMenuBar jMenuBar = new JMenuBar();
    jMenuBar.add(preferences);
    jMenuBar.add(gameSettings);
    setJMenuBar(jMenuBar);
    mainWindow = new DungeonPanel(model);
    sideWindow = new OutputPanel();
    JPanel windowWithinSide = new JPanel();
    addItemsToWindowWithin(windowWithinSide);
    JScrollPane scrollPane = new JScrollPane(mainWindow);
    JScrollPane scrollPaneSide = new JScrollPane(sideWindow,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JSplitPane s2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,scrollPaneSide, windowWithinSide);
    s2.setDividerLocation(400);
    JSplitPane s1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,s2);
    s1.setDividerLocation(750);
    scrollPaneSide.getVerticalScrollBar().addAdjustmentListener(e ->
            e.getAdjustable().setValue(e.getAdjustable().getMaximum()));
    this.add(s1);
    setVisible(true);
  }

  private void addItemsToWindowWithin(JPanel windowWithinSide) {
    windowWithinSide.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
    windowWithinSide.setBackground(Color.white);
    JLabel headLabel = new JLabel("Keyboard Game Functions",JLabel.CENTER);
    windowWithinSide.add(headLabel);
    windowWithinSide.setLayout(new GridLayout(9,2));
    InputStream shootStream = getClass().getResourceAsStream("/letter-s.png");
    InputStream treasureStream = getClass().getResourceAsStream("/letter-p.png");
    InputStream arrowStream = getClass().getResourceAsStream("/letter-a.png");
    InputStream descriptionStream = getClass().getResourceAsStream("/letter-d.png");
    InputStream leftStream = getClass().getResourceAsStream("/leftArrow.png");
    InputStream rightStream = getClass().getResourceAsStream("/right-arrow.png");
    InputStream upStream = getClass().getResourceAsStream("/up-arrow.png");
    InputStream downStream = getClass().getResourceAsStream("/down-arrow.png");
    BufferedImage buffShoot = null;
    BufferedImage buffTreasure = null;
    BufferedImage buffArrow = null;
    BufferedImage buffDescription = null;
    BufferedImage buffLeft = null;
    BufferedImage buffRight = null;
    BufferedImage buffUp = null;
    BufferedImage buffDown = null;
    try {
      buffShoot = ImageIO.read(shootStream);
      Image shoot = buffShoot.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon sIcon = new ImageIcon(shoot);
      JLabel shootLabel = new JLabel("To Shoot", sIcon, SwingConstants.LEFT);
      windowWithinSide.add(shootLabel);

      buffTreasure = ImageIO.read(treasureStream);
      Image treasure = buffTreasure.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(treasure);
      JLabel pickTreasureLabel = new JLabel("To Pick Treasure", tIcon, SwingConstants.LEFT);
      windowWithinSide.add(pickTreasureLabel);

      buffArrow = ImageIO.read(arrowStream);
      Image arrow = buffArrow.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon aIcon = new ImageIcon(arrow);
      JLabel pickArrowLabel = new JLabel("To Pick Arrow", aIcon, SwingConstants.LEFT);
      windowWithinSide.add(pickArrowLabel);

      buffDescription = ImageIO.read(descriptionStream);
      Image description = buffDescription.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon dIcon = new ImageIcon(description);
      JLabel descriptionLabel = new JLabel("Get Description", dIcon, SwingConstants.LEFT);
      windowWithinSide.add(descriptionLabel);

      buffLeft = ImageIO.read(leftStream);
      Image left = buffLeft.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon leftIcon = new ImageIcon(left);
      JLabel leftArrow = new JLabel("Move West", leftIcon, SwingConstants.LEFT);
      windowWithinSide.add(leftArrow);

      buffRight = ImageIO.read(rightStream);
      Image right = buffRight.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon rIcon = new ImageIcon(right);
      JLabel rightArrow = new JLabel("Move East", rIcon, SwingConstants.LEFT);
      windowWithinSide.add(rightArrow);

      buffUp = ImageIO.read(upStream);
      Image up = buffUp.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon upIcon = new ImageIcon(up);
      JLabel upArrow = new JLabel("Move North", upIcon, SwingConstants.LEFT);
      windowWithinSide.add(upArrow);

      buffDown = ImageIO.read(downStream);
      Image down = buffDown.getScaledInstance(20,20,Image.SCALE_SMOOTH);
      ImageIcon downIcon = new ImageIcon(down);
      JLabel downArrow = new JLabel("Move West", downIcon, SwingConstants.LEFT);
      windowWithinSide.add(downArrow);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Integer> getSpecs() {
    return null;
  }

  private Directions getDirection(KeyEvent e) {
    Directions direction = null;
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      direction = Directions.WEST;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      direction = Directions.EAST;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      direction = Directions.NORTH;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      direction = Directions.SOUTH;
    }
    return direction;
  }

  @Override
  public void setFeatures(Features f) {
    quit.addActionListener(e -> f.exitProgram());
    restart.addActionListener(e -> f.restartSameGame());
    restartNewGame.addActionListener(e -> f.restartNewGame());
    getInfo.addActionListener(e -> f.getInformation());

    mainWindow.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        f.moveClickListener(e.getX(),e.getY());
      }
    });

    this.addKeyListener(
        new KeyListener() {
          @Override
          public void keyTyped(KeyEvent e) {
            // Do nothing
          }

          @Override
          public void keyPressed(KeyEvent e) {
            if (shootFlag) {
              if (e.getKeyCode() == KeyEvent.VK_LEFT
                      || e.getKeyCode() == KeyEvent.VK_RIGHT
                      || e.getKeyCode() == KeyEvent.VK_UP
                      || e.getKeyCode() == KeyEvent.VK_DOWN) {
                Object[] options = {"1", "2", "3", "4", "5"};
                String s =
                        (String)
                                JOptionPane.showInputDialog(
                                        null,
                                        "Distance to shoot?",
                                        "Distance",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options,
                                        options[0]);
                f.shoot(getDirection(e), Integer.parseInt(s));
                shootFlag = false;
              }
              else {
                shootFlag = false;
              }
            } else {
              if (e.getKeyCode() == KeyEvent.VK_LEFT
                  || e.getKeyCode() == KeyEvent.VK_RIGHT
                  || e.getKeyCode() == KeyEvent.VK_UP
                  || e.getKeyCode() == KeyEvent.VK_DOWN) {
                f.move(getDirection(e));
              }
              if (e.getKeyCode() == KeyEvent.VK_S) {
                shootFlag = true;
              }
              if (e.getKeyCode() == KeyEvent.VK_P) {
                f.pickTreasure();
              }
              if (e.getKeyCode() == KeyEvent.VK_A) {
                f.pickArrow();
              }
              if (e.getKeyCode() == KeyEvent.VK_D) {
                f.getDescription();
              }
            }
          }

          @Override
          public void keyReleased(KeyEvent e) {
            // Do nothing
          }
        });
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void createDungeon(Model model) {
    // Do nothing
  }

  @Override
  public void display(Map<Integer, Map<List<Directions>, List<TreasuresTypes>>> currentLocation,
                      int arrowAtCurrentLoc,int smell, boolean status) {
    sideWindow.displayCurrentLocInfo(currentLocation, arrowAtCurrentLoc, smell, status);
  }

  @Override
  public void getDescription(Map<String, Integer> description) {
    JPanel popUp = new JPanel();
    JLabel text = new JLabel("The player has:");
    popUp.add(text);
    description.forEach(
        (key, value) -> {
          if (value != 0) {
            try {
              if (key.equals("Sapphire")) {
                InputStream treasureStream = getClass().getResourceAsStream("/sapphire.png");
                treasureIconHelper(popUp, value, treasureStream);
              }
              if (key.equals("Topaz")) {
                InputStream treasureStream = getClass().getResourceAsStream("/emerald.png");
                treasureIconHelper(popUp, value, treasureStream);
              }
              if (key.equals("Diamond")) {
                InputStream treasureStream = getClass().getResourceAsStream("/diamond.png");
                treasureIconHelper(popUp, value, treasureStream);
              }
              if (key.equals("Ruby")) {
                InputStream treasureStream = getClass().getResourceAsStream("/ruby.png");
                treasureIconHelper(popUp, value, treasureStream);
              }
              if (key.equals("Arrows")) {
                InputStream treasureStream = getClass().getResourceAsStream("/shooting-arrow.png");
                treasureIconHelper(popUp, value, treasureStream);
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
    JOptionPane.showMessageDialog(this,popUp,"Player Details",
            JOptionPane.PLAIN_MESSAGE);
  }

  private void treasureIconHelper(JPanel popUp, Integer value,
                                  InputStream treasureStream) throws IOException {
    BufferedImage buffTreasure = ImageIO.read(treasureStream);
    Image treasure = buffTreasure.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon tIcon = new ImageIcon(treasure);
    JLabel topazLabel = new JLabel(String.valueOf(value), tIcon, SwingConstants.CENTER);
    topazLabel.setAlignmentX(CENTER_ALIGNMENT);
    popUp.add(topazLabel);
  }

  @Override
  public void addLabel(String s) {
    sideWindow.addLabel(s);
  }

  @Override
  public void displayInfo(int rows, int cols, int interConnectivity, String type,
                          int amount, int numOtyughs) {
    String info =    "Number of Rows:          " + rows
            + "\n" + "Number of Columns:     " + cols
            + "\n" + "Interconnectivity:          " + interConnectivity
            + "\n" + "Dungeon Type:           " + type
            + "\n" + "Amount distributed:     " + amount
            + "\n" + "Number of Monsters:    " + numOtyughs;
    JOptionPane.showMessageDialog(null,info);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void shootInfo(int res) {
    String info;
    if (res == 1) {
      info = "Monster killed!";
    }
    else if (res == 2) {
      info = "You hear a great howl in the distance!";
    }
    else {
      info = "Oops! Arrow went in dark";
    }
    JOptionPane.showMessageDialog(null,info);
  }

  @Override
  public void displayWinInfo() {
    String info = "Congratulations!! You won the game\n\n"
            + "Navigate to preferences in menu bar to\n"
            + "start new game or quit the game";
    InputStream winnerStream = getClass().getResourceAsStream("/winner.png");
    try {
      BufferedImage buffWinner = ImageIO.read(winnerStream);
      Image winner = buffWinner.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(winner);
      JOptionPane.showMessageDialog(null,info,"You won",
              JOptionPane.PLAIN_MESSAGE,tIcon);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void displayLoseInfo() {
    String info1 = "Game Over!! you were killed by an otyugh";
    InputStream otyughStream = getClass().getResourceAsStream("/otyugh.png");
    try {
      BufferedImage buffOtyugh = ImageIO.read(otyughStream);
      Image otyugh = buffOtyugh.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(otyugh);
      JOptionPane.showMessageDialog(null,info1,"Killed",
              JOptionPane.PLAIN_MESSAGE,tIcon);
    } catch (IOException e) {
      e.printStackTrace();
    }

    helperDisplayPane();
  }

  @Override
  public void displayPitInfo() {
    String info1 = "Be careful!! A pit is within 1 units";
    pitInfoDisplay(info1);
  }

  @Override
  public void displayLoseInfoByPit() {
    String info1 = "Game Over!! you jumped into a pit";
    pitInfoDisplay(info1);
    helperDisplayPane();
  }

  private void pitInfoDisplay(String info1) {
    InputStream pitStream = getClass().getResourceAsStream("/pit.png");
    try {
      BufferedImage buffPit = ImageIO.read(pitStream);
      Image pit = buffPit.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(pit);
      JOptionPane.showMessageDialog(null,info1,"Pit",
              JOptionPane.PLAIN_MESSAGE,tIcon);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void helperDisplayPane() {
    String info = "Navigate to preferences in menu bar to\n"
            + "start new game or quit the game";
    InputStream loserStream = getClass().getResourceAsStream("/loser.png");
    try {
      BufferedImage buffWinner = ImageIO.read(loserStream);
      Image winner = buffWinner.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
      ImageIcon tIcon = new ImageIcon(winner);
      JOptionPane.showMessageDialog(null,info,"You lost",
              JOptionPane.PLAIN_MESSAGE,tIcon);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void exitGame() {
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
  public void hideWindow() {
    dispose();
  }
}
