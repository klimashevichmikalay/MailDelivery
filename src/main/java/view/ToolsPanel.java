/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import static view.ToolsPanel.Encryption.NOTHING;
import static view.ToolsPanel.Encryption.SSL;
import static view.ToolsPanel.Encryption.TSL;

/**
 *
 * @author Mikalay
 */
public class ToolsPanel {

    public enum Encryption {
        NOTHING, SSL, TSL
    };

    Encryption encr;
    JPanel panel;

    JLabel llogin;
    JTextField tlogin;
    JLabel lpass;
    JTextField tpass;

    JLabel lport;
    JLabel lfrom;
    JLabel ltopic;
    JLabel lhost;
    JTextField thost;
    JTextField tport;
    JTextField tfrom;
    JTextField ttopic;
    ButtonGroup group;
    JPanel mainpanel;

    public ToolsPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridLayout(16, 1, 0, 0));
        TitledBorder border = new TitledBorder("Настройки");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(border);

        mainpanel = new JPanel();
        mainpanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        mainpanel.setLayout(new GridLayout(2, 1, 0, 0));

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

        none.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                encr = NOTHING;
            }
        }
        );

        ssl.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                encr = SSL;
            }
        }
        );

        tsl.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                encr = TSL;
            }
        }
        );

        panel.add(
                new JLabel("Шифрование:"));
        group.add(none);

        group.add(ssl);

        group.add(tsl);

        panel.add(none);

        panel.add(ssl);

        panel.add(tsl);

        mainpanel.add(panel);
    }

    public Encryption getEncryption() {
        return this.encr;
    }

    public String getFrom() {
        return tfrom.getText();
    }

    public String getTopic() {
        return ttopic.getText();
    }

    public int getPort() {
        return Integer.parseInt(tport.getText());
    }

    public String getHost() {
        return thost.getText();
    }

    JPanel getPanel() {
        return this.mainpanel;
    }

    public String getPass() {
        return this.tpass.getText();
    }

    public String getLogin() {
        return this.tlogin.getText();
    }

}
