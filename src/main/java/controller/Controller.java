package controller;

import algorithms.XLSXParser;
import algorithms.Algorithms;
import java.io.IOException;
import java.util.Vector;
import model.MessageInfo;
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
    private final XLSXParser pars;

    /**
     * Конструктор.
     */
    public Controller() {

        alg = new Algorithms();
        pars = new XLSXParser();
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
        if ("".equals(file)) {

            memo.setMesage("|>Не выбран файл с \nданными для рассылки.\n\n");
            return;
        }
        file = file.replace("\\", "\\\\");

        Vector<MessageInfo> messages = pars.getMessages(file);
        MessageInfo tableHeader = XLSXParser.header;

        if (!checkAllFields(
                memo,
                messageSet,
                toolspanel,
                toolsbar,
                tableHeader)) {
            return;
        }

        memo.setMesage("\n|>Начало рассылки.\n");

        Vector<String> msgsTempl = alg.setTemplates(
                tableHeader, messages,
                messageSet.getText(),
                toolspanel.getOptionalColumnVector());

        for (int i = 0; i < msgsTempl.size(); i++) {

            if (msgsTempl.get(i) == null) {

                memo.setMesage("Err: не будет\n"
                        + "отправлено письмо"
                        + " со строки " + (i + 2) + ".\n" + "Т. к. введены\n"
                        + "не все обязательные\n"
                        + "поля.\n");
                continue;
            }
            String sender = getSender(tableHeader, messages.get(i), toolsbar);
            if ("".equals(sender)) {
                memo.setMesage("Err: не будет\n"
                        + "отправлено письмо.\n"
                        + "со строки " + (i + 2) + ".\n"
                        + "Т. к. столбца с именем\n"
                        + "адресата нет в\n"
                        + "таблице.");
                continue;
            }

            Runnable m = new Mailer(msgsTempl.get(i), sender, toolspanel, memo, i + 2);
            new Thread(m).start();
        }
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

        if (tableHeader.isHasDublicate()) {

            memo.setMesage("|>В заголовках \nтаблицы есть дубликаты.\n\n");
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

        if ("".equals(toolspanel.getLogin())) {

            memo.setMesage("|>Необходимо ввести\nлогин.\n\n");
            return false;
        }

        if ("".equals(toolspanel.getPass())) {

            memo.setMesage("|>Необходимо ввести\nпароль.\n\n");
            return false;
        }

        if ("".equals(toolspanel.getHost())) {

            memo.setMesage("|>Необходимо ввести\nхост.\n\n");
            return false;
        }

        if ("".equals(toolspanel.getPortString())) {

            memo.setMesage("|>Необходимо ввести\nпорт.\n\n");
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
