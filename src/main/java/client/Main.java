package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;
import log.MyLog;
import model.Constants;
import model.MessageInfo;
import model.XLSXParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Класс с точкой входа в программy.
 *
 * @author Mikalay
 */
public class Main {

//проверка почты через регулярку
//добавление  в мемо - интерфейс
//show objcts
// в XLSXParser добавить ошибку в лог - "не тип строки"
//добавить возможность не только для одного листа    
//проверка - если есть хоть 1 null, то имя неполное, если все null - то игнорим
//при ошибке выводим строку номер и текст тот, что есть
//лог добавить    
    static void testFormMessages() throws IOException {
        ClientView cv = new ClientView();
        XLSXParser pars = new XLSXParser();
        Vector<MessageInfo> vect = pars.getMessages("C:\\Users\\Mikalay\\Downloads\\file5.xlsx");

        cv.setMesage("Заголовок:\n");
        cv.setMesage(pars.getHeader().getString());
        cv.setMesage("Всего адресатов: " + vect.size());
        for (int i = 0; i < vect.size(); i++) {
            cv.setMesage("\n\nItem #" + (i + 1) + "\n" + vect.get(i).getString());
        }
    }

    public static void main(String[] args) throws IOException {        
        MyLog.logMsg("Начало работы программы.");
        testFormMessages();
    }
}
