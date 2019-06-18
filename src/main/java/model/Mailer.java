/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.URL;
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
//<image><image>    
    public String formMessage(String str) {

        str = str.replaceAll("<\\s*mailLink\\s*>([^<^>]*)<\\s*/\\s*mailLink\\s*>",
                "<a href=\"mailto:$1\"> $1 </a>");

        str = str.replaceAll("<\\s*siteLink\\s*>([^<^>]*)<\\s*/\\s*siteLink\\s*>",
                "<a href = $1>" + " $1 ");

        str = str.replaceAll("\r\n",
                "<br/>");

        return str;
    }

    public void SendMail2(String mail) throws Exception {

        mail = formMessage(mail);
        StringBuffer msg = new StringBuffer();
        msg.append("<html>");
        msg.append(mail);
        msg.append("</html>");
        SendMail(mail);

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
        email.setFrom("klimashevich.nicholas@gmail.com", "Nicholas");
        email.setSubject("Test email with inline image");
        /*  URL url = new URL("file:///C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        String cid = email.embed(url, "Apache logo");

        StringBuffer msg = new StringBuffer();
        msg.append("<html>");
        msg.append("Этот текст должен быть перед изображением!!!!");
        msg.append("<br/>");
        msg.append("<img src=\"cid:" + cid + "\">");
        msg.append("<br/>");
        msg.append("А этот - после ");
        msg.append("<br/>");
        msg.append("<img src=\"cid:" + cid + "\">");
        msg.append("<br/>");
        msg.append("Мой гитхаб:");
        msg.append("<br/>");
        msg.append("<a href = \"https://github.com/klimashevichmikalay\">");
        msg.append("https://github.com/klimashevichmikalay");
        msg.append("</a>");
        msg.append("<br/>");
        msg.append("Это ссылка на почту:");
        msg.append("<br/>");//<a href="mailto:name@email.com">Link text</a>
        msg.append("<a href=\"mailto:klimashevich.mikalay@mail.ru\">Моя почта</a>");
        msg.append("<br/>");
        msg.append("<br/>");
        msg.append("</html>");
        //msg.append("<html>");*/
        email.setHtmlMsg(mail.toString());
        email.send();
    }

}
