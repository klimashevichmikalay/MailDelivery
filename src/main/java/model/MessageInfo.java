package model;

import algorithms.XLSXParser;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import log.MyLog;

/**
 * Класс, объекты которого хранят значения ячеек строки.
 *
 * @author Mikalay
 */
public class MessageInfo {

    /**
     * Содержит все значения ячеек данной строки. Ключ - номер столбца, значение
     * - это значение ячейки строки этого стобца.
     */
    private final Map<Integer, String> row;

    /**
     * Парсер файла.
     */
    private XLSXParser parser;

    /**
     * Конструктор класса. Инициализируем карту для хранения данных строки -
     * сообщения.
     *
     */
    public MessageInfo() {

        parser = new XLSXParser();
        row = new HashMap<>();
    }

    /**
     * Функция - добавления значения ячейки.
     *
     * @param exrow - номер столбца
     * @param value - добавляемое значение
     */
    public void setCellValue(Integer exrow, String value) {

        row.put(exrow, value);
    }

    /**
     * Возврат карты - содержимого ячеек строки.
     *
     * @return карта со значениями ячеек строки, хранящейся в объекте и номерами
     * столбцов в качестве ключей
     */
    public Map<Integer, String> getMap() {

        return Collections.unmodifiableMap(this.row);
    }

    /**
     * Возврат содержимого ячейки строки.
     *
     * @param exrow  номер столбца
     * @return карта со значениями ячеек строки, хранящейся в объекте и номерами
     * столбцов в качестве ключей
     */
    public String getCellInfo(Integer exrow) {
        return row.get(exrow);
    }

    /**
     * Возврат значения ячеек в виде строки.
     *
     * @return строка со всеми значениями ячеек
     */
    public String getString() {

        String res = "";
        return this.row.entrySet().stream().map((entry) -> entry.getValue()).reduce(res, String::concat);
    }

    /**
     * Проверка наличия дубликатов в строке(это необходимо только для
     * заголовка).
     *
     * @return истина, если есть дубликаты, ложь - если их нет
     */
    public boolean isHasDublicate() throws IOException {

        for (int i = 0; i < row.size(); i++) {

            for (int j = i + 1; j < row.size(); j++) {

                if (row.get(i).equals(row.get(j))) {

                    MyLog.logMsg("В заголовке таблицы нет одинаковых полей.");
                    return true;
                }
            }
        }
        MyLog.logMsg("В заголовке таблицы есть дубликат(ы).");
        return false;
    }

    public boolean isSizeGood() {

        return row.size() == parser.header.row.size();
    }

    /**
     * Возврат строки - содержимого ячейки по индексу.
     *
     * @param i номер столбца, значение которого требуется от данной строки
     * @return строку - значение ячейки
     */
    public String getAt(int i) {
        return row.get(i);
    }

    /**
     * Возврат размера карты объекта, хранящей значения ячеек строки.
     *
     * @return число, обозначающее количество ячеек строки
     */
    public int getSize() {
        return row.size();
    }

    /**
     * @return the parser
     */
    public XLSXParser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(XLSXParser parser) {
        this.parser = parser;
    }
}