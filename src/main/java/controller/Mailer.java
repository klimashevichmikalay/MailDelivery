package controller;

import algorithms.Algorithms;
import java.net.MalformedURLException;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import view.ToolsPanel;
import model.Encryption;
import static model.Encryption.SSL;
import static model.Encryption.TSL;
import view.WorkingArea;

/**
 * Класс для отправления письма на сервер.
 *
 * @author Mikalay
 * @version 1.0
 */
public class Mailer implements Runnable {

    private final String message;
    private final String recipient;
    private final ToolsPanel mailingSetup;
    private final WorkingArea memo;
    private final int line;

    public Mailer(String _message,
            String _recipient,
            ToolsPanel _mailingSetup,
            WorkingArea _memo,
            int _line) {

        this.message = _message;
        this.recipient = _recipient;
        this.mailingSetup = _mailingSetup;
        this.memo = _memo;
        this.line = _line;
    }

    /**
     * Установка шифрования по выбору пользователя на панели управления.
     *
     * @param email клиент-отправитель
     * @param encr тип шифрования
     */
    private void setEncryption(HtmlEmail email, Encryption encr) {

        switch (encr) {
            case SSL: {
                email.setSSLOnConnect(true);
                break;
            }

            case TSL: {
                email.setStartTLSEnabled(true);
                email.setStartTLSRequired(true);
                break;
            }
        }
    }

    /**
     * Создание и настройка HtmlEmail от apache common.
     *
     * @param mailingSetup панель управления
     * @param recipient получатель
     * @return клиент для отправки письма
     */
    private HtmlEmail createJHTMLEmail(ToolsPanel mailingSetup, String recipient) throws EmailException {

        HtmlEmail email = new HtmlEmail();
        email.setCharset("utf-8");
        email.setSmtpPort(mailingSetup.getPort());
        email.setAuthenticator(new DefaultAuthenticator(mailingSetup.getLogin(), mailingSetup.getPass()));
        setEncryption(email, mailingSetup.getEncryption());
        email.setDebug(false);
        email.setHostName(mailingSetup.getHost());
        email.addTo(recipient);
        email.setFrom(mailingSetup.getLogin(), mailingSetup.getFrom());
        email.setSubject(mailingSetup.getTopic());

        return email;
    }

    @Override
    public void run() {
        Algorithms algtthms = new Algorithms();
        HtmlEmail email = null;
        try {
            email = createJHTMLEmail(mailingSetup, recipient);
        } catch (EmailException ex) {
        }
        try {
            try {
                email.setHtmlMsg(algtthms.createHTMLMessage(message, email));
            } catch (MalformedURLException ex) {
                System.out.println(ex.toString());
            }
        } catch (EmailException ex) {
        }
        try {
            email.send();
        } catch (EmailException ex) {
            memo.setMesage("\nErr: не удалось\n"
                    + "отправить письмо "
                    + "со строки " + (line) + ".\n" + "Т. к. возникли проблемы\n"
                    + "отправки к серверу\n"
                    + "перепроверьте логин и пароль,\n"
                    + "порт и хост, шифрование\n"
                    + "на панели управления,\n"
                    + "либо попробуйте разослать\n"
                    + "неотправленные письма позже.\n"
                    + "\n" + ex.toString() + "\n");
        }
    }
}
