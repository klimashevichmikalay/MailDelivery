package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import log.MyLog;
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

    boolean flagHeader = false;
    MessageInfo header;

    private Iterator<Row> getRowIterator(String path) {
        File myFile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XLSXParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        XSSFWorkbook myWorkBook = null;
        try {
            myWorkBook = new XSSFWorkbook(fis);
        } catch (IOException ex) {
            Logger.getLogger(XLSXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = mySheet.iterator();

        return rowIterator;
    }

    private MessageInfo getMessageInfo(Row row) {

        Iterator<Cell> cellIterator = row.cellIterator();
        int count = 0;
        MessageInfo mi = new MessageInfo();
        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {

                case Cell.CELL_TYPE_STRING:
                    mi.setCellValue(count, cell.getStringCellValue());
                    break;
                default:
            }
            count++;
        }
        return mi;
    }

    public Vector<MessageInfo> getMessages(String path) throws IOException {
        MyLog.logMsg("Начинаем формировать таблицу.");
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

    public MessageInfo getHeader() {
        return this.header;
    }
}
