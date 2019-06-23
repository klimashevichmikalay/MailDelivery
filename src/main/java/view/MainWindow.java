package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import controller.Controller;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Mikalay
 */
public class MainWindow {

    /**
     * Главное окно интерфейса.
     */
    private JFrame frame;

    /**
     * Поле для ввода письма.
     */
    private WorkingArea messageSet;

    /**
     * Поле вывода сообщений по ходу отправки.
     */
    volatile private WorkingArea memo;

    /**
     * Панель настройки соединения: шифрования, хост, порт, логин и пароль и т.
     * д.
     */
    private ToolsPanel toolspanel;

    /**
     * Кнопка начала рассылки.
     */
    private JButton send;

    /**
     * Выбор файла и адресата.
     */
    private ToolsBar toolsbar;

    /**
     * Контроллер занимается отправкой, используя остальные объекты классов.
     */
    private Controller cntrlr;

    /**
     * Конструктор класса - инициализирует интерфейс.
     *
     */
    public MainWindow() {
        super();
        initUI();
    }

    /**
     * Инициализация интерфейса.
     */
    private void initUI() {

        ////
        Font font = new Font("Verdana", Font.PLAIN, 10);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);

        ///
        cntrlr = new Controller();

        frame = new JFrame("Рассылка почты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageSet = new WorkingArea("Поле ввода письма", true);
        memo = new WorkingArea("События", false);

        send = new JButton("Разослать");
        send.addActionListener((ActionEvent e) -> {
            try {
                cntrlr.sendMessages(
                        toolsbar,
                        memo,
                        messageSet,
                        toolspanel
                );
            } catch (Exception ex) {
            }
        });

        toolspanel = new ToolsPanel();
        toolsbar = new ToolsBar();

        //content.add(tabbedPane, BorderLayout.CENTER);
        JPanel mailPanel = new JPanel();
        mailPanel.setLayout(new GridLayout(1, 2, 0, 0));
        mailPanel.add(messageSet.getPanel());
        mailPanel.add(memo.getPanel());
        tabbedPane.addTab("Ввод письма", mailPanel);

        JPanel tools = new JPanel();
        mailPanel.setLayout(new GridLayout(1, 2, 0, 0));
        tabbedPane.addTab("Настройки", toolspanel.getPanel());

        Container contentPane = frame.getContentPane();
        contentPane.add(toolsbar.getToolbar(), BorderLayout.NORTH);
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        contentPane.add(send, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * Установка контроллера.
     *
     * @param controller - контроллер
     */
    public void setController(Controller controller) {

        this.cntrlr = controller;
    }
}
