package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.*;

import controller.Features;

/**
 * Create the view once the parameters to create dungeon are submitted.
 */
public class DungeonWindow extends JFrame implements DungeonView {

  private final JMenuItem quit;
  private final JMenuItem restart;
  private final JMenuItem restartNewGame;
  private final JMenuItem getInfo;

  /**
   * Construct the actual game window with the parameters from previous window.
   * @param title The title of the window
   * @throws HeadlessException exception if no title given
   */
  public DungeonWindow(String title) throws HeadlessException {
    super(title);
    setBounds(300, 90, 800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar jMenuBar = new JMenuBar();
    JMenu preferences = new JMenu("Preferences");
    JMenu gameSettings = new JMenu("Settings");
    quit = new JMenuItem("Exit Game");
    restart = new JMenuItem("Restart Game");
    restartNewGame = new JMenuItem("New Game");
    getInfo = new JMenuItem("Game Information");
    preferences.add(quit);
    preferences.add(restart);
    preferences.add(restartNewGame);
    gameSettings.add(getInfo);
    jMenuBar.add(preferences);
    jMenuBar.add(gameSettings);
    setJMenuBar(jMenuBar);
    setVisible(true);
  }

  @Override
  public List<Integer> getSpecs() {
    return null;
  }

  @Override
  public void setFeatures(Features f) {
    quit.addActionListener(e -> f.exitProgram());
    restart.addActionListener(e -> f.restartSameGame());
    restartNewGame.addActionListener(e -> f.restartNewGame());
    getInfo.addActionListener(e -> f.getInformation());
  }
}
