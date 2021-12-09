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
import java.util.List;
import java.util.Map;
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
    ImageIcon shoot = new ImageIcon(new ImageIcon("res/dungeon-images/letter-s.png")
            .getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    JLabel shootLabel = new JLabel("To Shoot", shoot, SwingConstants.LEFT);
    windowWithinSide.add(shootLabel);
    ImageIcon pickTreasure = new ImageIcon(new ImageIcon("res/dungeon-images/letter-p.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel pickTreasureLabel = new JLabel("To Pick Treasure", pickTreasure, SwingConstants.LEFT);
    windowWithinSide.add(pickTreasureLabel);
    ImageIcon pickArrow = new ImageIcon(new ImageIcon("res/dungeon-images/letter-a.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel pickArrowLabel = new JLabel("To Pick Arrow", pickArrow, SwingConstants.LEFT);
    windowWithinSide.add(pickArrowLabel);
    ImageIcon description = new ImageIcon(new ImageIcon("res/dungeon-images/letter-d.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel descriptionLabel = new JLabel("Get Description", description, SwingConstants.LEFT);
    windowWithinSide.add(descriptionLabel);
    ImageIcon left = new ImageIcon(new ImageIcon("res/dungeon-images/leftArrow.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel leftArrow = new JLabel("Move West", left, SwingConstants.LEFT);
    windowWithinSide.add(leftArrow);
    ImageIcon right = new ImageIcon(new ImageIcon("res/dungeon-images/right-arrow.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel rightArrow = new JLabel("Move East", right, SwingConstants.LEFT);
    windowWithinSide.add(rightArrow);
    ImageIcon up = new ImageIcon(new ImageIcon("res/dungeon-images/up-arrow.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel upArrow = new JLabel("Move North", up, SwingConstants.LEFT);
    windowWithinSide.add(upArrow);
    ImageIcon down = new ImageIcon(new ImageIcon("res/dungeon-images/down-arrow.png")
            .getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
    JLabel downArrow = new JLabel("Move West", down, SwingConstants.LEFT);
    windowWithinSide.add(downArrow);
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
            (key,value) -> {
              if (value != 0) {
                if (key.equals("Sapphire")) {
                  ImageIcon sapphire = new ImageIcon(
                          new ImageIcon("res/dungeon-images/sapphire.png")
                                  .getImage().getScaledInstance(
                                          20, 20, Image.SCALE_SMOOTH));
                  JLabel sapphireLabel = new JLabel(String.valueOf(value),
                          sapphire, SwingConstants.CENTER);
                  sapphireLabel.setAlignmentX(CENTER_ALIGNMENT);
                  popUp.add(sapphireLabel);
                }
                if (key.equals("Topaz")) {
                  ImageIcon topaz = new ImageIcon(
                          new ImageIcon("res/dungeon-images/emerald.png")
                                  .getImage().getScaledInstance(
                                          20, 20, Image.SCALE_SMOOTH));
                  JLabel topazLabel = new JLabel(String.valueOf(value),
                          topaz, SwingConstants.CENTER);
                  topazLabel.setAlignmentX(CENTER_ALIGNMENT);
                  popUp.add(topazLabel);
                }
                if (key.equals("Diamond")) {
                  ImageIcon diamond = new ImageIcon(
                          new ImageIcon("res/dungeon-images/diamond.png")
                          .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                  JLabel diamondLabel = new JLabel(String.valueOf(value),
                          diamond, SwingConstants.CENTER);
                  diamondLabel.setAlignmentX(CENTER_ALIGNMENT);
                  popUp.add(diamondLabel);
                }
                if (key.equals("Ruby")) {
                  ImageIcon ruby = new ImageIcon(
                          new ImageIcon("res/dungeon-images/ruby.png")
                          .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                  JLabel rubyLabel = new JLabel(String.valueOf(value),
                          ruby, SwingConstants.CENTER);
                  rubyLabel.setAlignmentX(CENTER_ALIGNMENT);
                  popUp.add(rubyLabel);
                }
                if (key.equals("Arrows")) {
                  ImageIcon arrow = new ImageIcon(
                          new ImageIcon("res/dungeon-images/shooting-arrow.png")
                          .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                  JLabel arrowLabel = new JLabel(String.valueOf(value),
                          arrow, SwingConstants.CENTER);
                  arrowLabel.setAlignmentX(CENTER_ALIGNMENT);
                  popUp.add(arrowLabel);
                }
              }
            }
    );
    JOptionPane.showMessageDialog(this,popUp,"Player Details",
            JOptionPane.PLAIN_MESSAGE);
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
    ImageIcon winner = new ImageIcon(
            new ImageIcon("res/dungeon-images/winner.png")
                    .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    JOptionPane.showMessageDialog(null,info,"You won",
            JOptionPane.PLAIN_MESSAGE,winner);
  }

  @Override
  public void displayLoseInfo() {
    String info1 = "Game Over!! you were killed by an otyugh";
    ImageIcon otyugh = new ImageIcon(
            new ImageIcon("res/dungeon-images/otyugh.png")
                    .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    JOptionPane.showMessageDialog(null,info1,"Killed",
            JOptionPane.PLAIN_MESSAGE,otyugh);
    String info = "Navigate to preferences in menu bar to\n"
            + "start new game or quit the game";
    ImageIcon loser = new ImageIcon(
            new ImageIcon("res/dungeon-images/loser.png")
                    .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    JOptionPane.showMessageDialog(null,info,"You lost",
            JOptionPane.PLAIN_MESSAGE,loser);
  }

  @Override
  public void displayPitInfo() {
    String info1 = "Be careful!! A pit is within 1 units";
    ImageIcon pit = new ImageIcon(
            new ImageIcon("res/dungeon-images/pit.png")
                    .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    JOptionPane.showMessageDialog(null,info1,"Pit",
            JOptionPane.PLAIN_MESSAGE,pit);
  }

  @Override
  public void displayLoseInfoByPit() {
    String info1 = "Game Over!! you jumped into a pit";
    ImageIcon pit = new ImageIcon(
            new ImageIcon("res/dungeon-images/pit.png")
                    .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    JOptionPane.showMessageDialog(null,info1,"Pit",
            JOptionPane.PLAIN_MESSAGE,pit);
    String info = "Navigate to preferences in menu bar to\n"
            + "start new game or quit the game";
    ImageIcon loser = new ImageIcon(
            new ImageIcon("res/dungeon-images/loser.png")
                    .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
    JOptionPane.showMessageDialog(null,info,"You lost",
            JOptionPane.PLAIN_MESSAGE,loser);
  }

  @Override
  public void ExitGame() {
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
