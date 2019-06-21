package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Mikalay
 */
public class WorkingArea {

    JPanel panel;
    JTextArea area;
    JButton clearArea;

    WorkingArea(String title, boolean isEditable) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        area = new JTextArea();
        area.setEditable(isEditable);

        JScrollPane logScrollPane = new JScrollPane(area);
        panel.add(logScrollPane);
        clearArea = new JButton("Очистить все");
        clearArea.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText("");
            }

        });
        clearArea.setAlignmentX(0.5f);

        TitledBorder border = new TitledBorder(title);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        panel.setBorder(border);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(clearArea);
    }

    /**
     * Запись сообщения в представление.
     *
     * @param message - записываемая строка в рабочую область или мемо-поле
     *
     */
    public void setMesage(String message) {
        area.append("\n" + message);
    }

    public String getText() {
        return area.getText();
    }

    JPanel getPanel() {
        return this.panel;
    }
}