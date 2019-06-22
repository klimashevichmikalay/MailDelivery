package controller;

import algorithms.Algorithms;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import view.ToolsPanel;
import model.Encryption;
import static model.Encryption.SSL;
import static model.Encryption.TSL;

/**
 * Класс для отправления письма на сервер.
 *
 * @author Mikalay
 * @version 1.0
 */
public class Mailer {

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

    /**
     * Отправка сообщения на сервер.
     *
     * @param message текст для отправки
     * @param recipient получатель
     * @param mailingSetup панель управления отправки сообщения
     */
    public void send(String message,
            String recipient,
            ToolsPanel mailingSetup) throws Exception {

        Algorithms algtthms = new Algorithms();
        HtmlEmail email = createJHTMLEmail(mailingSetup, recipient);
        email.setHtmlMsg(algtthms.createHTMLMessage(message, email));
        email.send();
    }
}
