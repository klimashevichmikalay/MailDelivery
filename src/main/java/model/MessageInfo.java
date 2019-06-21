package model;

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
     * Содержит все значения ячеек данной строки.
     */
    private final Map<Integer, String> row;

    /**
     * Конструктор класса. Инициализируем карту для хранения данных строки -
     * сообщения.
     *
     */
    public MessageInfo() {
        row = new HashMap<>();
    }

    /**
     * Функция - добавления значения ячейки.
     *
     * @param exrow - тип информации(ФИО, роль, почта и т. д.)
     * @param value - добавляемое значение
     */
    public void setCellValue(Integer exrow, String value) {
        row.put(exrow, value);
    }

  
    public Map<Integer, String> getMap()
    {
        return Collections.unmodifiableMap(this.row);
    }
    
    public String getCellInfo(Integer exrow) {
        return row.get(exrow);
    }

    public String getString() {
        String res = "";
        for (int i = 0; i < row.size(); i++) {
            res += row.get(i) + "\n";
        }
        return res;
    }

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
        if (XLSXParser.header.row.size() != row.size()) {
            return false;
        }
        return true;
    }

    public String getAt(int i)
    {
        return row.get(i);
    }
    
    public int getSize() {
        return row.size();
    }
}
