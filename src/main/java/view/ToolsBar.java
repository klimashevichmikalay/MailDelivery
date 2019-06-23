package view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import log.MyLog;

/**
 * Класс с тулбаром с кнопкой выбора файла, файлчузером.
 *
 * @author Mikalay
 */
public class ToolsBar {

    /**
     * Отображение выбранного файла.
     */
    private JLabel fileLabel;

    /**
     * Строковое представление выбранного пути файла.
     */
    private String file = "";

    /**
     * Поле ввода адресата.
     */
    private final JTextField addressee;

    /**
     * Кнопка для вызова файлчузера и выбора файла XLSX.
     */
    private final JButton fileSelect;

    /**
     * Булева переменная, равна истине, если заголовок был уже считан, ложь,
     * если еще нет.
     */
    private final JToolBar toolbar;

    /**
     * Конструктор для формирования верхней панели управления в графическом
     * интерфейсе.
     *
     */
    public ToolsBar() {

        toolbar = new JToolBar();
        toolbar.setRollover(true);

        fileLabel = new JLabel("<Файл для рассылки>");

        fileSelect = new JButton("Выбрать файл");
        fileSelect.addActionListener((ActionEvent e) -> {
            setFile();
            fileLabel.setText(file);
            fileLabel.repaint();
        });

        JLabel mailLabel = new JLabel("Имя столбца адресатов:");

        addressee = new JTextField(8);
        addressee.setText("");

        toolbar.add(fileSelect);
        toolbar.addSeparator();
        toolbar.add(fileLabel);
        toolbar.addSeparator();
        toolbar.add(mailLabel);
        toolbar.addSeparator();
        toolbar.add(addressee);
    }

    /**
     * Выбор файла через файлчузер.
     */
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

    /**
     * Формирует массив объектов MessageInfo из строк таблицы, объекты содержат
     * данные строк. Так же тут сохраняется отдельно заголовок.
     *
     * @return панель с компонентами интерфейса
     */
    public JToolBar getToolbar() {

        return toolbar;
    }

    /**
     * Возврат введенного пользователем адресата.
     *
     * @return введенное пользователем имя столбца, где находятся постовые
     * адреса
     */
    public String getAddressee() {

        return addressee.getText();
    }

    /**
     * Вернуть выбранный пользователей файл XLSX для рассылки данных.
     *
     * @return выбранный пользователем файл
     */
    public String getFile() {

        return this.file;
    }
}