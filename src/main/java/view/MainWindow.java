package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import controller.Controller;

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
    private WorkingArea memo;

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

        Container contentPane = frame.getContentPane();
        contentPane.add(toolsbar.getToolbar(), BorderLayout.NORTH);
        contentPane.add(messageSet.getPanel(), BorderLayout.CENTER);
        contentPane.add(memo.getPanel(), BorderLayout.EAST);
        contentPane.add(send, BorderLayout.SOUTH);
        contentPane.add(toolspanel.getPanel(), BorderLayout.WEST);

        frame.setSize(800, 720);
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
