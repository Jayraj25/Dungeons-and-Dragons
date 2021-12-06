package view;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import controller.Features;

public class SpecsForm extends JFrame implements DungeonView {

  private final JRadioButton wrappingType;
  private final JTextField rowsTextField;
  private final JTextField colsTextField;
  private final JTextField interconnectivityTextField;
  private final JTextField amountTextField;
  private final JTextField numOtyughsTextField;
  private final JButton sub;


  public SpecsForm(String WindowTitle) {
    super(WindowTitle);
    setBounds(300, 90, 800, 600);
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
    amount.setSize(200,20);
    amount.setLocation(100,300);
    c.add(amount);

    amountTextField = new JTextField();
    amountTextField.setSize(290, 20);
    amountTextField.setLocation(300, 300);
    c.add(amountTextField);

    JLabel numOtyughs = new JLabel("Number of Otyughs :");
    numOtyughs.setSize(200,20);
    numOtyughs.setLocation(100,350);
    c.add(numOtyughs);

    numOtyughsTextField = new JTextField();
    numOtyughsTextField.setSize(290, 20);
    numOtyughsTextField.setLocation(300, 350);
    c.add(numOtyughsTextField);

    sub = new JButton("Create Dungeon");
    sub.setSize(200, 20);
    sub.setLocation(300, 450);
    c.add(sub);

//    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public List<Integer> getSpecs() {
    List<Integer> specs = new ArrayList<>();
//    specs.add(Integer.parseInt(rowsTextField.getText()));
//    specs.add(Integer.parseInt(colsTextField.getText()));
//    specs.add(Integer.parseInt(interconnectivityTextField.getText()));
//    if (wrappingType.isSelected()) {
//      specs.add(1);
//    }
//    else {
//      specs.add(0);
//    }
//    specs.add(Integer.parseInt(amountTextField.getText()));
//    specs.add(Integer.parseInt(numOtyughsTextField.getText()));
    return specs;
  }

  @Override
  public void setFeatures(Features f) {
    sub.addActionListener(e -> f.createModel());
  }
}