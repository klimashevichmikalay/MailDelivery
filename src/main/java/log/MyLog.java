package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Класс-реализация логирования.
 *
 * @author Mikalay
 * @version 1.0
 */
public class MyLog {

    /**
     * Конструктор, создает, если их нет, папки с лог-файлами в папке, откуда
     * запускается программа.
     */
    public MyLog() {

    }
    /**
     * Путь к файлам с лог-файлами лога каждой сессии с каким-либо клиентом.
     */
    public static final String logTable = "log/log";

    /**
     * Запись в лог события, связанного с работой с таблицей.
     *
     * @param msg - сообщение, записываемое в лог файл
     */
    public static void logMsg(String msg) throws IOException {
        new File("log").mkdirs();
        String path = logTable;
        writeMsg(path, msg);
    }

    /**
     * Непосредственно записть сообщения в файл по определенному пути.
     *
     * @param msg - сообщение, записываемое в лог файл
     * @param path - путь лог-файла
     */
    private static void writeMsg(String path, String msg) throws UnsupportedEncodingException, IOException {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path, true),
                    StandardCharsets.UTF_8));
            out.append(new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(Calendar.getInstance().getTime())
                    + " --- " + msg + "\n");
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }
}
