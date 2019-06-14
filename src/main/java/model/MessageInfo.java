package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Класс, который содержит все необходимые данные для отправки письма: конечную
 * почту, ФИО для обращения, новую роль в организации, дополнительный текст.
 *
 * @author Mikalay
 */
/**
 * Перечисление всех заголовков таблицы XLSX(Фамилия, Имя, Отчество, Роль,
 * Email, Логин, Пароль).
 */
enum ExcelRow {
    SURNAME,
    NAME,
    PATRONYMIC,
    ROLE,
    EMAIL,
    LOGIN,
    PASSWORD
};

public class MessageInfo {

    /**
     * Содержит все значения ячеек данной строки.
     */
    private Map<ExcelRow, String> row;

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
    void setCellValue(ExcelRow exrow, String value) {
        row.put(exrow, value);
    }

    /**
     * Получить значение ячейки из этой строки по типу этой ячейки(ФИО, роль или
     * другой тип).
     *
     * @param exrow - тип информации(ФИО, роль, почта и т. д.)
     * @return строку - значение ячейки
     */
    String getCellInfo(ExcelRow exrow) {
        return row.get(exrow);
    }
}
