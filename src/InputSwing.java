import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class InputSwing {
  private JButton showInSwingBttn;
  private JButton showInBrowserBttn;
  private JTextArea outputTxtArea;
  private JTextField x1txtFld;
  private JTextField y1txtFld;
  private JTextField x2txtFld;
  private JTextField y2txtFld;
  private JButton addLineBttn;
  private JButton computeBttn;
  private JTextArea inputTxtArea;
  private JComboBox<Entry> presetPicker;
  private JButton showInputBrowserBttn;
  private JPanel panel;

  private Line2D[] lines;

  private Triangle[] result;

  private boolean inputLegal = false;

  private InputSwing() {
    computeBttn.addActionListener(this::handleComputeBttnClicked);
    addLineBttn.addActionListener(this::handleAddLineBttnClicked);
    showInSwingBttn.addActionListener(this::handleShowInSwingBttnClicked);
    showInBrowserBttn.addActionListener(this::handleShowInBrowserBttnClicked);
    showInputBrowserBttn.addActionListener(this::handleShowInputBrowserClicked);
    inputTxtArea.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        this.changedUpdate(e);
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        this.changedUpdate(e);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        updateInput();
      }
    });

    presetPicker.addItem(entryFromResource("dreiecke1.txt"));
    presetPicker.addItem(entryFromResource("dreiecke2.txt"));
    presetPicker.addItem(entryFromResource("dreiecke3.txt"));
    presetPicker.addItem(entryFromResource("dreiecke4.txt"));
    presetPicker.addItem(entryFromResource("dreiecke5.txt"));
    presetPicker.addItem(entryFromResource("dreiecke6.txt"));
    presetPicker.setSelectedIndex(-1);
    presetPicker.addItemListener(this::handlePresetPickerItemAction);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("triangleFinder");
    frame.setContentPane(new InputSwing().panel);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  private Entry entryFromResource(String path) {
    return new Entry(path, streamFromResource(path));
  }

  private InputStream streamFromResource(String path) {
    return this.getClass().getClassLoader().getResourceAsStream(path);
  }

  private void handleComputeBttnClicked(ActionEvent e) {
    if (!inputLegal) {
      alert("Illegal Input!");
      return;
    }

    compute();
  }

  private void handleAddLineBttnClicked(ActionEvent e) {
    String builder = x1txtFld.getText() +
      ' ' +
      y1txtFld.getText() +
      ' ' +
      x2txtFld.getText() +
      ' ' +
      y2txtFld.getText() +
      ' ' +
      '\n';

    inputTxtArea.append(builder);
  }

  private void handleShowInSwingBttnClicked(ActionEvent e) {
    if (result == null) compute();

    new OutputSwing(result);
  }

  private void handleShowInBrowserBttnClicked(ActionEvent e) {
    if (result == null) compute();

    new OutputWeb().show(result);
  }

  private void handleShowInputBrowserClicked(ActionEvent e) {
    new OutputWeb().show(lines);
  }

  private void handlePresetPickerItemAction(ItemEvent e) {
    Entry entry = (Entry) e.getItem();

    inputTxtArea.setText(entry.value);
  }

  private void compute() {
    String input = inputTxtArea.getText();

    result = Main.compute(input);

    updateResult();
  }

  private void updateResult() {
    if (result.length < 1) {
      outputTxtArea.setText("No triangles found");
      return;
    }

    Arrays.sort(result, Triangle::compareTo);

    StringBuilder builder = new StringBuilder();
    for (Triangle t : result) {
      builder.append(t.toString());
      builder.append('\n');
    }

    outputTxtArea.setText(builder.toString());
  }

  private void updateInput() {
    try {
      lines = InputReader.readFromString(inputTxtArea.getText()).toArray(new Line2D[0]);

      inputLegal = true;
    } catch (Exception e) {
      inputLegal = false;
    }

    updateLegal();
  }

  private void updateLegal() {
    if (inputLegal) inputTxtArea.setBackground(Color.WHITE);
    else inputTxtArea.setBackground(Color.RED);
  }

  private void alert(String s) {
    JOptionPane.showMessageDialog(panel, s);
  }

  private class Entry {
    String label;
    String value;

    Entry(String label, InputStream stream) {
      this.label = label;

      Scanner scanner = new Scanner(stream, "UTF-8");
      StringBuilder builder = new StringBuilder();

      while(scanner.hasNextLine()) {
        builder.append(scanner.nextLine());
        builder.append('\n');
      }

      this.value = builder.toString();
    }

    @Override
    public String toString() {
      return this.label;
    }
  }
}
