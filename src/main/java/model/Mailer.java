/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
//

import java.net.URL;
import java.util.Arrays;
import java.util.Vector;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import view.MainWindow;
import view.ToolsPanel;
import static view.ToolsPanel.Encryption.SSL;
import static view.ToolsPanel.Encryption.TSL;

/**
 *
 * @author Mikalay
 */
public class Mailer {

//image    
// <siteLink></siteLink>    
//<mailLink></mailLink>
//<image></image>    
    public String formMessage(String str) {

        str = str.replaceAll("<\\s*mailLink\\s*>\\s*([^<^>]*)\\s*<\\s*/\\s*mailLink\\s*>",
                "<a href=\"mailto:$1\">$1</a>");

        str = str.replaceAll("<\\s*siteLink\\s*>\\s*([^<^>]*)\\s*<\\s*/\\s*siteLink\\s*>",
                "<a href = \"$1\">" + "$1</a>");

        str = str.replaceAll("<\\s*image\\s*>\\s*([^<^>]*)\\s*<\\s*/\\s*image\\s*>",
                "<image>$1</image>");

        str = str.replaceAll("\n", "<br/>");

        return str;
    }

    public void send(String mail, String sender, ToolsPanel tp) throws Exception {

        mail = formMessage(mail);
        StringBuffer msg = new StringBuffer();
        msg.append("<html>");
        msg.append(mail);
        msg.append("</html>");
        SendMail(msg.toString(), sender, tp);

    }

    public static void SendMail(String mail, String sender, ToolsPanel tp) throws Exception {

        HtmlEmail email = new HtmlEmail();
        email.setCharset("utf-8");
        email.setSmtpPort(tp.getPort());
        email.setAuthenticator(new DefaultAuthenticator(tp.getLogin(), tp.getPass()));

        if (tp.getEncryption() == SSL) {
            email.setSSLOnConnect(true);
        }

        if (tp.getEncryption() == TSL) {
            email.setStartTLSEnabled(true);
            email.setStartTLSRequired(true);
        }
        email.setDebug(false);
        email.setHostName(tp.getHost());
        email.addTo(sender);
        email.setFrom(tp.getLogin(), tp.getFrom());//название от кого
        email.setSubject(tp.getTopic());//это название сообщения 

        mail = mail.replaceAll("<\\s*image\\s*>",
                "<forSplit><image><forSplit>");

        mail = mail.replaceAll("<\\s*/\\s*image\\s*>",
                "<forSplit></image><forSplit>");

        String[] ops = mail.split("<forSplit>");
        Vector<String> vector = new Vector<String>(Arrays.asList(ops));

        StringBuffer msg = new StringBuffer();
        int countImages = 0;
        for (int i = 0; i < vector.size(); i++) {
            if (i + 1 < vector.size() && vector.get(i).matches("<image>")) {
                String urrr = "file:///" + vector.get(i + 1);
                URL url = new URL(urrr);
                String cid = email.embed(url, ("Image" + countImages));
                msg.append("<img src=\"cid:" + cid + "\">");
                i++;
                countImages++;
                continue;
            }
            if (vector.get(i).matches("</image>")) {
                continue;
            }
            msg.append(vector.get(i));
        }

        email.setHtmlMsg(msg.toString());
        email.send();
    }
}
