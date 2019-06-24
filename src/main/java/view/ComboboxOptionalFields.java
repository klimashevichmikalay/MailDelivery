package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ComboboxOptionalFields {

    private final JPanel panel = new JPanel();
    @SuppressWarnings("unchecked")
    private final JComboBox<String> comboBox = new JComboBox();
    private final JTextField textfField = new JTextField(5);

    public ComboboxOptionalFields() {
        createGui();
    }

    private void createGui() {
        JButton buttonClean = new JButton("Очистить список");
        buttonClean.addActionListener((ActionEvent e) -> {
            javax.swing.SwingUtilities.invokeLater(() -> {
                comboBox.removeAllItems();
            });
        });
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.addActionListener((ActionEvent e) -> {
            appendCbItem(textfField.getText());
            textfField.setText("");
        });

        panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridLayout(4, 1, 0, 0));
        TitledBorder border = new TitledBorder("Необязательные для отправления столбцы таблицы");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(border);

        panel.setLayout(new GridLayout(4, 1));
        panel.add(comboBox);
        panel.add(textfField);
        panel.add(buttonAdd);
        panel.add(buttonClean);
        panel.setVisible(true);
    }

    JPanel getPanel() {
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
}
