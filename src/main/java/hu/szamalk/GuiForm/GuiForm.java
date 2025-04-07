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



































package hu.szamalk.guiForm;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class GuiForm extends JFrame{
    private JComboBox comboBox1;
    private JButton btnFelvesz;
    private JList list1;
    private JCheckBox checkBox1;
    private JTextField textField1;
    private JPanel pnlMain;


    public GuiForm() {
        setTitle("Combo + List");
        setContentPane(pnlMain);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520, 240);
        setVisible(true);
        setLocationRelativeTo(null); // középre

        // JList
        DefaultListModel<String> dlm = new DefaultListModel<>();
        list1.setModel(dlm);

        // Menü
        JMenuItem menuMentes = new JMenuItem("Mentése");
        JMenuItem menuBetolt = new JMenuItem("Betölt");
        JMenu menu = new JMenu("Program");
        menu.add(menuMentes);
        menu.add(menuBetolt);
        menu.add(new JSeparator());
        menu.add(new JMenuItem("Kilépés"));
        JMenuBar menuBar = new JMenuBar();


        menuBar.add(menu);
        setJMenuBar(menuBar);








        // Minden felvett elemnél a combox-ra hozzáadunk egy "elem + 1-100ig számot"
        String elem = "elem " + (int)(Math.random()*100);
        btnFelvesz.addActionListener(e -> comboBox1.addItem((textField1.getText()) + elem));
        comboBox1.addItem("Felvett elemek");


        //Mindgi a kiválaszott combobox elemet kiiírjuk konzolra
        /*comboBox1.addActionListener(e -> {
            String kivalasztott = comboBox1.getSelectedItem().toString();
            System.out.println(kivalasztott);

            DefaultListModel<String> ls = (DefaultListModel<String>) list1.getModel();
            ls.addElement(kivalasztott);
        });*/



        // checkboxba pipa akkkor átkerül az elem és kitörlödik
        checkBox1.addActionListener(e -> {
            if(checkBox1.isSelected()) {
                String kivalasztott = comboBox1.getSelectedItem().toString();
                System.out.println(kivalasztott);

                DefaultListModel<String> ls = (DefaultListModel<String>) list1.getModel();
                ls.addElement(kivalasztott);

                comboBox1.removeItem(comboBox1.getSelectedItem());
            }
        });


        // kettot kattintunk a listára és egyet kiveszünk azzal belőle
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println(list1.getSelectedValue());

                    String elme = (String) list1.getSelectedValue();
                    DefaultListModel dlm = (DefaultListModel) list1.getModel();
                    dlm.removeElement(elem);
                }
            }
        });



        //
        mitMent.addActionListener(e -> {
            JFileChooser jfl = new JFileChooser(new File(System.getProperty("user.dir")));
            if(jfl.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("konf.conf"))){

                } catch (FileNotFoundException ex){
                    System.out.println("Nem találjuk a fájlt");
                    ex.printStackTrace();
                } catch (IOException ex){
                    System.out.println("IO hiba");
                    ex.printStackTrace();
                }
            };


        });



        // nem tudom, elvesztem
        mnuKiir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    File fajl = jfc.getSelectedFile();
                    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fajl))) {
                        // kiirando modell
                        GuiConfigModell modell = new GuiConfigModell();
                        // checkbox állapota modellbe
                        modell.setChbMozgat(chbMozgat.isSelected());
                        // combo szövege listába
                        List<String> cl = new ArrayList<>();
                        for(int i = 0; i < cmbElemek.getItemCount(); i++) {
                            cl.add((String) cmbElemek.getItemAt(i));
                        }
                        modell.setComboSzovegek(cl);


                        List<String> ll = new ArrayList<>();
                        ListModel lm = felveszList.getModel();
                        for(int i = 0; i < felveszList.getModel().getSize(); i++) {
                            ll.add((String) lm.getElementAt(i));
                        }
                        modell.setComboSzovegek(ll);
                        oos.writeObject(modell);
                    } catch(FileNotFoundException ex) {
                        System.err.println("Nincs meg a fájl" + ex.getMessage());
                        ex.printStackTrace();
                    } catch(IOException ex) {
                        System.err.println("I/O hiba" + ex.getMessage());
                        ex.printStackTrace();
                    }
                }

            }
        });



        menuBetolt.addActionListener(e -> {

        });

    }


    public static void main(String[] args) {
        new GuiForm();
    }
}
