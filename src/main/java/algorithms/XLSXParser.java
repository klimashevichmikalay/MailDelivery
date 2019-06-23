package algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import log.MyLog;
import model.MessageInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Класс для чтения XLSX-файла, формироания будущих сообщений и проверки их на
 * синтаксис.
 *
 * @author Mikalay
 */
public class XLSXParser {

    /**
     * Булева переменная, равна истине, если заголовок был уже считан, ложь,
     * если еще нет.
     */
    private boolean flagHeader = false;

    /**
     * Заголовок таблицы.
     */
    public static MessageInfo header;

    /**
     * Возвращает итератор для просмотра строк таблицы.
     *
     * @param path - путь файла для рассылки
     * @return итератор на первую строку
     */
    private Iterator<Row> getRowIterator(String path) {

        File myFile = new File(path);
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(myFile);
        } catch (FileNotFoundException ex) {
        }

        XSSFWorkbook myWorkBook = null;
        try {

            myWorkBook = new XSSFWorkbook(fis);
        } catch (IOException ex) {

        }

        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = mySheet.iterator();

        return rowIterator;
    }

    /**
     * Формирует объект MessageInfo из строки таблицы, объект содержит данные
     * строки.
     *
     * @param row - строка
     * @return объект со значениями ячейки строки
     */
    private MessageInfo getMessageInfo(Row row) {

        Iterator<Cell> cellIterator = row.cellIterator();
        int count = 0;

        MessageInfo mi = new MessageInfo();

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {

                case Cell.CELL_TYPE_BLANK:
                    mi.setCellValue(count, "");
                    break;

                case Cell.CELL_TYPE_STRING:
                    mi.setCellValue(count, cell.getStringCellValue());
                    break;
                default:
            }

            count++;
        }
        return mi;
    }

    /**
     * Формирует массив объектов MessageInfo из строк таблицы, объекты содержат
     * данные строк. Так же тут сохраняется отдельно заголовок.
     *
     * @param path - путь к XLSX - файлу
     * @return массив объектов со значениями ячейки строки
     * @throws java.io.IOException исключение при работе с файлом по пути path
     */
    public Vector<MessageInfo> getMessages(String path) throws IOException {

        Vector<MessageInfo> messages = new Vector<>(0);
        Iterator<Row> rowIterator = getRowIterator(path);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            MessageInfo mi = getMessageInfo(row);
            if (!flagHeader) {
                header = mi;
                flagHeader = true;
                continue;
            }
            messages.add(mi);
            MyLog.logMsg("Добавлена строка таблицы:\n" + mi.getString());
        }
        flagHeader = false;
        MyLog.logMsg("Таблица сформирована.");
        return messages;
    }
}
