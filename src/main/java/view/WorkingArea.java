package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Класс для представления текстового поля для ввода письма или вывода сообщений
 * от приложений.
 *
 * @author Mikalay
 */
public class WorkingArea {

    /**
     * Панель, на которой располагаются компонены: текстовое поле и кнопка
     * очистки этого поля.
     */
    private final JPanel panel;

    /**
     * Текстовое поле для ввода письма или вывода сообщений приложения.
     */
    private JTextArea area;

    /**
     * Кнопка очистки текстового поля.
     */
    private final JButton clearArea;

    /**
     * Конструктор для инициализации и размещения компонентов поля.
     *
     * @param title - заголовок компонента - панели
     * @param isEditable - булева переменная, при истинности текстовое поле
     * панели можно редактировать, при ложности - нельзя
     */
    WorkingArea(String title, boolean isEditable) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        area = new JTextArea();
        area.setEditable(isEditable);

        JScrollPane logScrollPane = new JScrollPane(area);
        panel.add(logScrollPane);
        clearArea = new JButton("Очистить все");
        clearArea.addActionListener((ActionEvent e) -> {
            area.setText("");
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

    /**
     * Возвращает текст из текстового поля.
     *
     * @return текст JTextArea area
     */
    public String getText() {
        return area.getText();
    }

    public void setText(String text) {
        area.setText(text);
        area.repaint();
    }

    /**
     * Возвращает панель с компонентами.
     *
     * @return панель с компонентами
     */
    JPanel getPanel() {
        return this.panel;
    }
}
