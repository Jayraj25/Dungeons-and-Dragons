package view;

import controller.Features;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The view that shows the form with input elements like rows, cols, interconnectivity, amount,
 * numPits, numMonsters, etc.
 */
public class SpecsForm extends JFrame implements DungeonForm {

  private final JRadioButton wrappingType;
  private final JTextField rowsTextField;
  private final JTextField colsTextField;
  private final JTextField interconnectivityTextField;
  private final JTextField amountTextField;
  private final JTextField numOtyughsTextField;
  private final JTextField numPitsTextField;
  private final JTextField numThievesTextField;
  private final JButton sub;

  /**
   * Creates the specifications form.
   * @param WindowTitle the title of the form.
   */
  public SpecsForm(String WindowTitle) {
    super(WindowTitle);
    setBounds(300, 90, 800, 800);
    // View uses Swing framework to display UI to user
    Container c = getContentPane();
    c.setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create UI elements
    JLabel title = new JLabel("Enter the Specifications for dungeon creation");
    title.setSize(300, 50);
    title.setLocation(300, 30);
    c.add(title);

    JLabel rows = new JLabel("Number of Rows :");
    rows.setSize(200, 20);
    rows.setLocation(100, 100);
    c.add(rows);

    rowsTextField = new JTextField();
    rowsTextField.setSize(290, 20);
    rowsTextField.setLocation(300, 100);
    c.add(rowsTextField);

    JLabel cols = new JLabel("Number of Cols :");
    cols.setSize(200, 20);
    cols.setLocation(100, 150);
    c.add(cols);

    colsTextField = new JTextField();
    colsTextField.setSize(290, 20);
    colsTextField.setLocation(300, 150);
    c.add(colsTextField);

    JLabel dungeonType = new JLabel("Dungeon Type :");
    dungeonType.setSize(200, 20);
    dungeonType.setLocation(100, 200);
    c.add(dungeonType);

    wrappingType = new JRadioButton("Wrapping");
    wrappingType.setSelected(true);
    wrappingType.setSize(100, 20);
    wrappingType.setLocation(300, 200);
    c.add(wrappingType);

    JRadioButton nonWrappingType = new JRadioButton("Non-Wrapping");
    nonWrappingType.setSelected(false);
    nonWrappingType.setSize(200, 20);
    nonWrappingType.setLocation(400, 200);
    c.add(nonWrappingType);

    ButtonGroup grp1 = new ButtonGroup();
    grp1.add(wrappingType);
    grp1.add(nonWrappingType);

    JLabel interconnectivity = new JLabel("Interconnectivity");
    interconnectivity.setSize(200, 20);
    interconnectivity.setLocation(100, 250);
    c.add(interconnectivity);

    interconnectivityTextField = new JTextField();
    interconnectivityTextField.setSize(290, 20);
    interconnectivityTextField.setLocation(300, 250);
    c.add(interconnectivityTextField);

    JLabel amount = new JLabel("Treasure and Arrow Amount :");
    amount.setSize(200, 20);
    amount.setLocation(100, 300);
    c.add(amount);

    amountTextField = new JTextField();
    amountTextField.setSize(290, 20);
    amountTextField.setLocation(300, 300);
    c.add(amountTextField);

    JLabel numOtyughs = new JLabel("Number of Otyughs :");
    numOtyughs.setSize(200, 20);
    numOtyughs.setLocation(100, 350);
    c.add(numOtyughs);

    numOtyughsTextField = new JTextField();
    numOtyughsTextField.setSize(290, 20);
    numOtyughsTextField.setLocation(300, 350);
    c.add(numOtyughsTextField);

    JLabel numPits = new JLabel("Number of Pits :");
    numPits.setSize(200, 20);
    numPits.setLocation(100, 400);
    c.add(numPits);

    numPitsTextField = new JTextField();
    numPitsTextField.setSize(290, 20);
    numPitsTextField.setLocation(300, 400);
    c.add(numPitsTextField);

    JLabel numThieves = new JLabel("Number of Thieves :");
    numThieves.setSize(200, 20);
    numThieves.setLocation(100, 450);
    c.add(numThieves);

    numThievesTextField = new JTextField();
    numThievesTextField.setSize(290, 20);
    numThievesTextField.setLocation(300, 450);
    c.add(numThievesTextField);

    sub = new JButton("Create Dungeon");
    sub.setSize(200, 20);
    sub.setLocation(300, 500);
    c.add(sub);

    setVisible(true);
  }

  @Override
  public List<Integer> getSpecs() {
    List<Integer> specs = new ArrayList<>();
    if (rowsTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid rows", "Invalid rows", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(rowsTextField.getText());
        if (a < 3) {
          JOptionPane.showMessageDialog(
              null, "Rows should be more than 3", "Invalid rows", JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(rowsTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null, "Invalid rows", "Invalid rows", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (colsTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid columns", "Invalid columns", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(colsTextField.getText());
        if (a < 3) {
          JOptionPane.showMessageDialog(
              null, "Columns should be more than 3", "Invalid columns", JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(colsTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null, "Invalid columns", "Invalid columns", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (interconnectivityTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid interconnectivity", "Invalid columns", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(interconnectivityTextField.getText());
        if (a < 0) {
          JOptionPane.showMessageDialog(
              null,
              "Interconnectivity " + "should not be less than 0",
              "Invalid interconnectivity",
              JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(interconnectivityTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null,
            "Invalid interconnectivity",
            "Invalid interconnectivity",
            JOptionPane.ERROR_MESSAGE);
      }
    }
    if (wrappingType.isSelected()) {
      specs.add(1);
    } else {
      specs.add(0);
    }
    if (amountTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid amount", "Invalid amount", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(amountTextField.getText());
        if (a < 0) {
          JOptionPane.showMessageDialog(
              null, "Amount should not be negative", "Invalid amount", JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(amountTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null, "Invalid amount", "Invalid amount", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (numOtyughsTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid number of monsters", "Invalid monsters", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(numOtyughsTextField.getText());
        if (a < 1) {
          JOptionPane.showMessageDialog(
              null,
              "Num of monsters" + " cannot be less than 1",
              "Invalid monsters",
              JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(numOtyughsTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null, "Invalid number of monsters", "Invalid monsters", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (numPitsTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid number of pits", "Invalid monsters", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(numPitsTextField.getText());
        if (a < 0) {
          JOptionPane.showMessageDialog(
              null,
              "Num of pits" + " cannot be less than 0",
              "Invalid pits",
              JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(numPitsTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null, "Invalid number of pits", "Invalid pits", JOptionPane.ERROR_MESSAGE);
      }
    }
    if (numThievesTextField.getText().equals("")) {
      JOptionPane.showMessageDialog(
          null, "Invalid number of thieves", "Invalid thieves", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        int a = Integer.parseInt(numThievesTextField.getText());
        if (a < 0) {
          JOptionPane.showMessageDialog(
              null,
              "Num of thieves" + " cannot be less than 1",
              "Invalid thieves",
              JOptionPane.ERROR_MESSAGE);
        } else {
          specs.add(Integer.parseInt(numThievesTextField.getText()));
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null, "Invalid number of thieves", "Invalid thieves", JOptionPane.ERROR_MESSAGE);
      }
    }
    return specs;
  }

  @Override
  public void setFeatures(Features f) {
    sub.addActionListener(e -> f.createModel());
  }

  @Override
  public void hideWindow() {
    dispose();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
