package controller;

import algorithms.XLSXParser;
import algorithms.Algorithms;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MessageInfo;
import view.MainWindow;
import view.ToolsBar;
import view.ToolsPanel;
import view.WorkingArea;

/**
 * Класс, организующий работы остальных компонентов для рассылки сообщений.
 *
 * @author Mikalay
 */
public class Controller {

    /**
     * Класс обертка с алгоритмами обработки строк.
     */
    private final Algorithms alg;

    /**
     * Конструктор.
     */
    public Controller() {

        alg = new Algorithms();
    }

    /**
     * Отправление сообщения.
     *
     * @param toolsbar - верхняя панель управления, отсюда берется информация,
     * введенная пользователем
     * @param memo - вывод сообщений для пользователя
     * @param messageSet - поле ввода письма
     * @param toolspanel - панель настройки соеинения
     * @throws java.lang.Exception
     */
    public void sendMessages(
            ToolsBar toolsbar,
            WorkingArea memo,
            WorkingArea messageSet,
            ToolsPanel toolspanel) throws Exception {

        String file = toolsbar.getFile();
        file = file.replace("\\", "\\\\");

        XLSXParser pars = new XLSXParser();
        Vector<MessageInfo> messages = null;
        try {
            messages = pars.getMessages(file);

        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        MessageInfo tableHeader = pars.header;

        if (!checkAllFields(
                memo,
                messageSet,
                toolspanel,
                toolsbar,
                tableHeader)) {
            return;
        }
        memo.setMesage("\n\n\n|>Начало рассылки.\n\n");
        Mailer mailer = new Mailer();
        int countNull = 0;
        Vector<String> msgsTempl = alg.setTemplates(tableHeader, messages, messageSet.getText());
        for (int i = 0; i < msgsTempl.size(); i++) {

            if (msgsTempl.get(i) == null) {
                countNull++;
                memo.setMesage("Err: не удалось\n"
                        + "отправить письмо "
                        + "со строки " + (i + 2) + ".\n");
            } else {

                String sender = getSender(tableHeader, messages.get(i), toolsbar);
                if ("".equals(sender)) {
                    memo.setMesage("\nErr: перепроверьте\nнаписание адресата\n"
                            + "в верху приложения\n"
                            + "\nэто должно быть имя\n"
                            + "столбца таблицы");
                    return;
                } else {
                    mailer.send(msgsTempl.get(i), sender, toolspanel);
                }
            }
        }
        memo.setMesage("\n|>Разослано писем:\n" + (msgsTempl.size() - countNull) + "\n");
        memo.setMesage("\n|>Не удалось разослать:\n" + (countNull));
    }

    /**
     * Проверка, введены ли все поля.
     *
     * @param toolsbar - верхняя панель управления, отсюда берется информация,
     * введенная пользователем.
     * @param memo - вывод сообщений для пользователя
     * @param messageSet - поле ввода письма
     * @param toolspanel - панель настройки соеинения
     * @return истина, если все заполнено, ложь - если нет
     */
    boolean checkAllFields(
            WorkingArea memo,
            WorkingArea messageSet,
            ToolsPanel toolspanel,
            ToolsBar toolsbar,
            MessageInfo tableHeader) throws IOException {

        if ("".equals(toolsbar.getFile())) {
            memo.setMesage("|>Не выбран файл с \nданными для рассылки.\nРассылка не удалась.\n\n");
            return false;
        }

        if (tableHeader.isHasDublicate()) {
            memo.setMesage("|>В заголовках \nтаблицы есть дубликаты.\nРассылка не удалась.\n\n");
            return false;
        }

        if ("".equals(toolsbar.getAddressee())) {
            memo.setMesage("|>Необходимо ввести\nадресата.\n\n");
            return false;
        }

        if ("".equals(messageSet.getText())) {
            memo.setMesage("|>Необходимо ввести\nписьмо.\n\n");
            return false;
        }
        return true;
    }

    /**
     * Получить имя столбца таблицы, из которого мы будем брать адресата.
     *
     * @return имя столбца таблицы, пустая строка - если нет столбца с таким
     * именем, который указан пользователем как адресат.
     */
    private String getSender(MessageInfo tableHeader,
            MessageInfo message,
            ToolsBar toolsbar) {

        for (int i = 0; i < tableHeader.getSize(); i++) {
            if (tableHeader.getAt(i).equals(toolsbar.getAddressee())) {
                return message.getAt(i);
            }
        }
        return "";
    }
}
