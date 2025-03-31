package hu.szamalk.GuiForm;

import javax.swing.*;

public class GuiForm extends JFrame {
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button2;
    private JPanel pnlMain;

    public GuiForm() {
        setTitle("Form használata");
        setContentPane(pnlMain);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 240);
        setVisible(true);
        button1.addActionListener(e -> comboBox1.addItem(textField1.getText()));
        comboBox1.addItem("Felvett elemek");
        button2.addActionListener(e -> {
            int i = comboBox1.getSelectedIndex();
            if (i > 0) {
                comboBox1.removeItem(comboBox1.getItemAt(comboBox1.getSelectedIndex()));
            }

        });
    }


    public static void main(String[] args) {
        new GuiForm();
    }

}
