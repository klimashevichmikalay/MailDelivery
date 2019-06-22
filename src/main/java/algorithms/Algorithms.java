package algorithms;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.MessageInfo;
import static model.Constants.END_IMAGE_REGEX;
import static model.Constants.END_IMAGE_WITH_SPLIT_REGEX;
import static model.Constants.END_VAR_REGEX;
import static model.Constants.IMAGE_HTML;
import static model.Constants.IMAGE_TEMPLATE;
import static model.Constants.MAIL_LINK_HTML;
import static model.Constants.MAIL_LINK_TEMPLATE;
import static model.Constants.NEW_LINE_HTML;
import static model.Constants.SITE_LINK_HTML;
import static model.Constants.SITE_LINK_TEMPLATE;
import static model.Constants.SPLIT_TAG;
import static model.Constants.START_IMAGE_REGEX;
import static model.Constants.START_IMAGE_WITH_SPLIT_REGEX;
import static model.Constants.START_VAR_REGEX;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Класс-реализация алгоритмов ля обработки строк и формирования сообщений для
 * отправки.
 *
 * @author Mikalay
 * @version 1.0
 */
public class Algorithms {

    /**
     * Замена переменных во всех письмах соответствующими значениями полей
     * таблицы. Переменная имеет имя столбца, заменяется на значение ячейки -
     * пересечения Этого столбца и строки, из которой формируется
     * соответствующее сообщение.
     *
     * @param tableHeader заголовок таблицы
     * @param messages значения ячеек строк для формирования писем
     * @param letterText текст письма для отправки
     * @return  вектор строк-писем с замененными переменными на соответствующие
     * в строке для каждого письма
     */
    public Vector<String> setTemplates(
            MessageInfo tableHeader,
            Vector<MessageInfo> messages,
            String letterText) {

        Vector<String> messagesWithTemplates = new Vector<>();
        messages.forEach((message) -> messagesWithTemplates.add(replaceAllVar(letterText, tableHeader, message)));

        return messagesWithTemplates;
    }

    /**
     * Замена переменных в письме соответствующими значениями полей таблицы.
     * Переменная имеет имя столбца, заменяется на значение ячейки - пересечения
     * этого столбца и строки, из которой формируется соответствующее сообщение.
     *
     * @param tableHeader заголовок таблицы
     * @param message значения ячеек строки для формирования писем
     * @param letterText текст письма для отправки
     * @return строка-письмо с замененными переменными на соответствующие в
     * строке для каждого письма
     */
    private String replaceAllVar(
            String letterText,
            MessageInfo tableHeader,
            MessageInfo message) {

        if (!message.isSizeGood()) {
            return null;
        }

        for (int i = 0; i < tableHeader.getSize(); i++) {
            letterText = replaceVarTo(letterText, tableHeader.getAt(i), message.getAt(i));
        }
        return letterText;
    }

    /**
     * Замена переменной в письме соответствующее значение поля таблицы.
     *
     * @param text текст письма, к котором происходит замена переменной
     * @param var имя заменяемой переменной, является названием столбца таблицы
     * @param replaceTo строка, на которую заменяется переменная, является
     * значением ячейки
     * @return строка-письмо с замененной переменной
     */
    String replaceVarTo(
            String text,
            String var,
            String replaceTo) {

        Pattern pattern = Pattern.compile(START_VAR_REGEX + var + END_VAR_REGEX);
        Matcher m = pattern.matcher(text);
        return (m.replaceAll(replaceTo));
    }

    /**
     * Формирование конечной HTML-строки для отправки.
     *
     * @param mail текст для отправки
     * @param email клиент для отправки
     * @return строка для отправки
     */
    public String createHTMLMessage(String mail, HtmlEmail email) throws MalformedURLException, EmailException {

        mail = formMailForHTML(mail);

        String[] arrayForHTLMMsg = mail.split(SPLIT_TAG);
        Vector<String> vectorForHTLMMsg = new Vector<>(Arrays.asList(arrayForHTLMMsg));

        StringBuilder msg = new StringBuilder();
        int countImages = 0;
        for (int i = 0; i < vectorForHTLMMsg.size(); i++) {
            if (i + 1 < vectorForHTLMMsg.size() && vectorForHTLMMsg.get(i).matches(START_IMAGE_REGEX)) {
                String urrr = "file:///" + vectorForHTLMMsg.get(i + 1);
                URL url = new URL(urrr);
                String cid = email.embed(url, ("Image" + countImages));
                msg.append("<img src=\"cid:" + cid + "\">");
                i++;
                countImages++;
                continue;
            }
            if (vectorForHTLMMsg.get(i).matches(END_IMAGE_REGEX)) {
                continue;
            }
            msg.append(vectorForHTLMMsg.get(i));
        }
        return msg.toString();
    }

    /**
     * Замена шаблонов ссылок и файлов на формат HTML.
     *
     * @param message письмо, в котором происходит замена
     * @return письмо, в котором шаблоны заменены на текст HTML для вставки
     * ссылок или файлов
     */
    private String formMailForHTML(String message) {

        message = message.replaceAll(MAIL_LINK_TEMPLATE, MAIL_LINK_HTML);
        message = message.replaceAll(SITE_LINK_TEMPLATE, SITE_LINK_HTML);
        message = message.replaceAll(IMAGE_TEMPLATE, IMAGE_HTML);
        message = message.replaceAll("\n", NEW_LINE_HTML);
        message = message.replaceAll(START_IMAGE_REGEX, START_IMAGE_WITH_SPLIT_REGEX);
        message = message.replaceAll(END_IMAGE_REGEX, END_IMAGE_WITH_SPLIT_REGEX);

        message = "<html>" + message + "</html>";

        return message;
    }
}
