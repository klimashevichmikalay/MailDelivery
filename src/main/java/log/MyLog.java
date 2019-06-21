package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static model.Constants.LOG_DIR_PATH;
import static model.Constants.LOG_FILE_PATH;

/**
 * Класс-реализация логирования.
 *
 * @author Mikalay
 * @version 1.0
 */
public class MyLog {

    /**
     * Запись в лог события, связанного с работой с таблицей.
     *
     * @param msg сообщение, записываемое в лог файл
     * @throws java.io.IOException - исключение при неудаче записи в лог нового
     * сообщения.
     */
    public static void logMsg(String msg) throws IOException {

        new File(LOG_DIR_PATH).mkdirs();
        writeMsg(LOG_FILE_PATH, msg);
    }

    /**
     * Непосредственно записть сообщения в файл по определенному пути.
     *
     * @param msg сообщение, записываемое в лог файл
     * @param path путь лог-файла
     * @throws java.io.IOException исключение при неудаче записи в лог
     * сообщения.
     */
    private static void writeMsg(String path, String msg) throws IOException {

        try {
            try (Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path, true),
                    StandardCharsets.UTF_8))) {
                out.append(new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(Calendar.getInstance().getTime())
                        + " --- " + msg + "\n");
                out.flush();
            }
        } catch (Exception e) {
        }
    }
}
