package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import log.MyLog;
import model.Algorithms;
import model.MessageInfo;
import model.XLSXParser;

/**
 *
 * @author Mikalay
 */
public class MainWindow {
    
    private JFrame frame;
    private JButton fileSelect;
    private JButton send;
    private JLabel fileLabel;
    private String file = "";
    private JTextField addressee;
    private WorkingArea messageSet;
    private WorkingArea memo;
    private final Algorithms alg;
    
    public MainWindow() {
        super();
        initUI();
        alg = new Algorithms();
    }
    
    private void initUI() {
        
        try {
            
            try {
                MyLog.logMsg("Начало инициализации представления.");
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            frame = new JFrame("Рассылка почты");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JToolBar toolbar = new JToolBar();
            toolbar.setRollover(true);
            
            fileLabel = new JLabel("<Файл для рассылки>");
            
            fileSelect = new JButton("Выбрать файл");
            fileSelect.addActionListener((ActionEvent e) -> {
                setFile();
                fileLabel.setText(file);
                fileLabel.repaint();
            });
            
            JLabel mailLabel = new JLabel("Адресат:");
            
            addressee = new JTextField(8);
            addressee.setText("");
            
            toolbar.add(fileSelect);
            toolbar.addSeparator();
            toolbar.add(fileLabel);
            toolbar.addSeparator();
            toolbar.add(mailLabel);
            toolbar.addSeparator();
            toolbar.add(addressee);
            
            messageSet = new WorkingArea("Ввод сообщения для рассылки", true);
            memo = new WorkingArea("События", false);
            
            send = new JButton("Разослать все сообщения");
            send.addActionListener((ActionEvent e) -> {
                sendMessages();
            });
            
            Container contentPane = frame.getContentPane();
            contentPane.add(toolbar, BorderLayout.NORTH);
            contentPane.add(messageSet.getPanel(), BorderLayout.CENTER);
            contentPane.add(memo.getPanel(), BorderLayout.EAST);
            contentPane.add(send, BorderLayout.SOUTH);
            
            frame.setSize(600, 400);
            frame.setVisible(true);
            
            MyLog.logMsg("Представление инициализировано.");
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setFile() {
        
        try {
            MyLog.logMsg("Выбор файла для рассылки.");
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Выберите файл *.xlsx");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file", "xlsx");
        jfc.addChoosableFileFilter(filter);
        
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.file = jfc.getSelectedFile().getPath();
        }
        
        try {
            MyLog.logMsg("Выбран файл: " + file + ".");
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendMessages() {
        
        try {
            MyLog.logMsg("Начало обработки сообщения для рассылки.");
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(file)) {
            memo.setMesage("|>Не выбран файл с \nданными для рассылки.\nРассылка не удалась.\n\n");
            return;
        }
        
        file = file.replace("\\", "\\\\");
        
        XLSXParser pars = new XLSXParser();
        Vector<MessageInfo> messages = null;
        try {
            messages = pars.getMessages(file);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MessageInfo tableHeader = pars.getHeader();
        
        try {
            if (tableHeader.isHasDublicate()) {
                memo.setMesage("|>В заголовках \nтаблицы есть дубликаты.\nРассылка не удалась.\n\n");
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ("".equals(addressee.getText())) {
            memo.setMesage("|>Необходимо ввести\nадресата.\n\n");
            return;
        }
        
        if ("".equals(messageSet.getText())) {
            memo.setMesage("|>Необходимо ввести\nписьмо.\n\n");
            return;
        }
        
        Vector<String> msgsTempl = alg.setTemplates(tableHeader, messages, messageSet.getText());

        ////
        messageSet.setMesage("\n\n------------------------");
        
        for (int i = 0; i < msgsTempl.size(); i++) {
            messageSet.setMesage(msgsTempl.get(i));
            messageSet.setMesage("\n\n------------------------");
        }

        ///////
    }
}
