package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import static model.Constants.TEMPLATES_DIR;

public class MailsTemplatesCombobox {

    private final JPanel panel = new JPanel();
    @SuppressWarnings("unchecked")
    private final JComboBox<String> comboBox = new JComboBox();
    private final WorkingArea workingArea;

    public MailsTemplatesCombobox(WorkingArea _workingArea) {
        createGui();
        this.workingArea = _workingArea;
    }

    private void createGui() {

        setItemsTemplates();

        JButton buttonSet = new JButton("Установить шаблон письма");
        buttonSet.addActionListener((ActionEvent e) -> {
            System.out.println((String) comboBox.getSelectedItem());
            try {
                setTemplate(readFile(TEMPLATES_DIR + "\\" + (String) comboBox.getSelectedItem(), StandardCharsets.UTF_8));
                //

                //
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MailsTemplatesCombobox.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MailsTemplatesCombobox.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridLayout(3, 1, 0, 0));
        TitledBorder border = new TitledBorder("Установить шаблон");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(border);

        panel.setLayout(new GridLayout(4, 1));
        panel.add(comboBox);
        panel.add(buttonSet);
        panel.setVisible(true);
    }

    private void setItemsTemplates() {
        File folder = new File(TEMPLATES_DIR);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                comboBox.addItem(listOfFile.getName());
            }
        }
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void appendCbItem(String item) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            comboBox.addItem(item);
            requestCbFocus();
        });
    }

    public Vector<String> getStrings() {

        Vector<String> result = new Vector<>();
        int size = comboBox.getItemCount();
        for (int i = 0; i < size; i++) {
            result.add(comboBox.getItemAt(i));
        }
        return result;
    }

    public void requestCbFocus() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            comboBox.requestFocus();
            comboBox.requestFocusInWindow();
        });
    }

    private void setTemplate(String text) throws FileNotFoundException {
        workingArea.setText(text);
    }

    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
