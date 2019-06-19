/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.URL;
import java.util.Arrays;
import java.util.Vector;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

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

        str = str.replaceAll("<\\s*mailLink\\s*>([^<^>]*)<\\s*/\\s*mailLink\\s*>",
                "<a href=\"mailto:$1\">$1</a>");

        str = str.replaceAll("<\\s*siteLink\\s*>([^<^>]*)<\\s*/\\s*siteLink\\s*>",
                "<a href = \"$1\">" + "$1</a>");

        str = str.replaceAll("\r\n", "<br/>");

        return str;
    }

    public void Send(String mail) throws Exception {

        mail = formMessage(mail);
        StringBuffer msg = new StringBuffer();
        msg.append("<html>");
        msg.append(mail);
        msg.append("</html>");
        SendMail(msg.toString());

    }

    public static void SendMail(String mail) throws Exception {

        HtmlEmail email = new HtmlEmail();
        email.setCharset("utf-8");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("klimashevich.nicholas@gmail.com", "rJkZ1234"));
        email.setSSLOnConnect(true);
        email.setDebug(false);
        email.setHostName("smtp.gmail.com");
        email.addTo("klimashevich.mikalay@gmail.com");
        email.setFrom("klimashevich.nicholas@gmail.com", "Nicholas");//название от кого
        email.setSubject("Test email with inline image");//это название сообщения 

        mail = mail.replaceAll("<\\s*image\\s*>",
                "<forSplit><image><forSplit>");

        mail = mail.replaceAll("<\\s*/\\s*image\\s*>",
                "<forSplit></image><forSplit>");

        String[] ops = mail.split("<forSplit>");
        Vector<String> vector = new Vector<String>(Arrays.asList(ops));

        //URL url = new URL("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
//String cid = email.embed(url, "Image");
//msg.append("<br/>");
//msg.append("<img src=\"cid:" + cid + "\">");
//msg.append("<br/>"); 
        StringBuffer msg = new StringBuffer();
        int countImages = 0;
        for (int i = 0; i < vector.size(); i++) {
            if (i + 1 < vector.size() && vector.get(i).matches("<image>")) {
                URL url = new URL("file:///" + vector.get(i + 1));
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

        String str = msg.toString();
        email.setHtmlMsg(msg.toString());
        email.send();
    }
}
