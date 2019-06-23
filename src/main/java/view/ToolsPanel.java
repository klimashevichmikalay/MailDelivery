package view;

import model.Encryption;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.BorderFactory;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import static model.Encryption.NOTHING;
import static model.Encryption.SSL;
import static model.Encryption.TSL;

/**
 * Класс с настройками соеденения с сервером: хост, порт, шифрование.
 *
 * @author Mikalay
 */
public class ToolsPanel {

    /**
     * Окно для выбора необязательных полей таблицы
     */
    private ComboboxOptionalFields combobox;

    /**
     * Тип шифрования, выбираемый в радиобаттон. По умолчанию - SSL.
     */
    private Encryption encr;

    /**
     * Панель для инструкции.
     */
    private final JPanel panel;

    /**
     * Поле для обозначения, где вводить логин.
     */
    private final JLabel llogin;

    /**
     * Поле для ввода логина.
     */
    private final JTextField tlogin;

    /**
     * Поле для обозначения, где вводить пароль.
     */
    private final JLabel lpass;

    /**
     * Поле для ввода пароля.
     */
    private final JTextField tpass;

    /**
     * Поле для обозначения, где вводить порт.
     */
    private final JLabel lport;

    /**
     * Поле для обозначения, где вводить отправителя.
     */
    private final JLabel lfrom;

    /**
     * Поле для обозначения, где вводить тему письма.
     */
    private final JLabel ltopic;

    /**
     * Поле для обозначения, где вводить хост.
     */
    private final JLabel lhost;

    /**
     * Поле для ввода обозначения, где вводить хост.
     */
    private final JTextField thost;

    /**
     * Поле для ввода обозначения, где вводить порт.
     */
    private final JTextField tport;

    /**
     * Поле для ввода обозначения, где вводить отправителя.
     */
    private final JTextField tfrom;

    /**
     * Поле для ввода темы письма.
     */
    private final JTextField ttopic;

    /**
     * Кнопки для выбора шифрования.
     */
    private final ButtonGroup group;

    /**
     * Главная панель для компонентов.
     */
    private final JPanel mainpanel;

    /**
     * Конструктор - инициализирует все компоненты.
     *
     */
    public ToolsPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridLayout(12, 1, 0, 0));
        TitledBorder border = new TitledBorder("Авторизация");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(border);

        mainpanel = new JPanel();
        mainpanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        mainpanel.setLayout(new GridLayout(2, 1, 0, 0));

        combobox = new ComboboxOptionalFields();

//инструкции       
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText(
                "|>Выберите файл XLSX для рассылки.\n"
                + "по кнопке в верхнем правом углу\n\n"
                + " |>Введите почту адресата\n(заголовок"
                + " столбца с эл. почтой).\n\n"
                + "|>Введите текст письма в поле ввода,\n"
                + "используя замены по названию\n"
                + "заголовком столбца:\n\n\n"
                + "|>вставка ячейки строки:\n"
                + "<var>заголовок таблицы</var>\n\n"
                + "|>вставка ссылки на сайт:\n"
                + "<siteLink>ссылка</siteLink>\n\n"
                + "|>вставка ссылки на почту:\n"
                + "<mailLink>почта</mailLink>\n\n"
                + "|>вставка картинки из ПК:\n"
                + "<image>C:\\Users\\im.jpg</image>"
                + "\n\n\nНажмите кнопку внизу\n"
                + "\"Разослать\"");
        JScrollPane logScrollPane = new JScrollPane(area);

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        panel1.setLayout(new GridLayout(1, 1, 0, 0));
        TitledBorder border1 = new TitledBorder("Инструкции");
        border1.setTitleJustification(TitledBorder.CENTER);
        border1.setTitlePosition(TitledBorder.TOP);
        panel1.setBorder(border1);
        panel1.add(logScrollPane);
        mainpanel.add(panel1);
//mainpanel.add(Box.createRigidArea(new Dimension(0, 5)));
//начало логина пароля
        llogin = new JLabel("LOGIN:");
        tlogin = new JTextField(5);
        lpass = new JLabel("PASSWORD:");
        tpass = new JTextField(5);
        panel.add(llogin);
        panel.add(tlogin);
        panel.add(lpass);
        panel.add(tpass);
//конец логина пароля
//конец инструкции
//хост и порт
        lhost = new JLabel("HOST:");
        thost = new JTextField(5);
        panel.add(lhost);
        panel.add(thost);
        lport = new JLabel("PORT:");
        tport = new JTextField(5);
        panel.add(lport);
        panel.add(tport);
//конец хоста  порта        
//от кого и тема  
        lfrom = new JLabel("Сообщение от:");
        tfrom = new JTextField(5);
        panel.add(lfrom);
        panel.add(tfrom);
        ltopic = new JLabel("Тема:");
        ttopic = new JTextField(5);
        panel.add(ltopic);
        panel.add(ttopic);
//конец от кого и тема
//начало радиокнопки
        group = new ButtonGroup();
        JRadioButton none = new JRadioButton("Нет шифрования", false);
        JRadioButton ssl = new JRadioButton("SSL-шифрование", true);
        encr = SSL;
        JRadioButton tsl = new JRadioButton("TSL-шифрование", false);

        none.addActionListener((ActionEvent e) -> {
            encr = NOTHING;
        });

        ssl.addActionListener((ActionEvent e) -> {
            encr = SSL;
        });

        tsl.addActionListener((ActionEvent e) -> {
            encr = TSL;
        });

        JPanel encrPanel = new JPanel();
        encrPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        encrPanel.setLayout(new GridLayout(4, 1, 0, 0));
        TitledBorder border2 = new TitledBorder("Настройки шифрования");
        border2.setTitleJustification(TitledBorder.CENTER);
        border2.setTitlePosition(TitledBorder.TOP);
        encrPanel.setBorder(border2);
        encrPanel.add(new JLabel("Шифрование:"));
        group.add(none);
        group.add(ssl);
        group.add(tsl);
        encrPanel.add(none);
        encrPanel.add(ssl);
        encrPanel.add(tsl);

        mainpanel.add(panel);
        mainpanel.add(encrPanel);
        mainpanel.add(combobox.getPanel());
    }

    public Vector<String> getOptionalColumnVector() {

        return this.combobox.getStrings();
    }

    /**
     * Получить выбранное шифрование сервера.
     *
     * @return тип штфрования
     */
    public Encryption getEncryption() {
        return this.encr;
    }

    /**
     * Получить имя отправителя(это ваше имя).
     *
     * @return имя отправителя
     */
    public String getFrom() {
        return tfrom.getText();
    }

    /**
     * Получить тему письма.
     *
     * @return тему письма
     */
    public String getTopic() {
        return ttopic.getText();
    }

    /**
     * Получить порт как число.
     *
     * @return тему письма
     */
    public int getPort() {
        return Integer.parseInt((tport.getText()).replaceAll("\\s+", ""));
    }

    /**
     * Получить порт как строку.
     *
     * @return тему письма
     */
    public String getPortString() {
        return tport.getText();
    }

    /**
     * Получить хост.
     *
     * @return хост сервера
     */
    public String getHost() {
        return (thost.getText().replaceAll("\\s+", ""));
    }

    /**
     * Получить панель с компонентами.
     *
     * @return главная панель управления
     */
    JPanel getPanel() {
        return this.mainpanel;
    }

    /**
     * Получить введенный пароль.
     *
     * @return пароль для авторизации
     */
    public String getPass() {
        return (this.tpass.getText().replaceAll("\\s+", ""));
    }

    /**
     * Получить логин.
     *
     * @return логин для авторизации
     */
    public String getLogin() {
        return (this.tlogin.getText().replaceAll("\\s+", ""));
    }
}
