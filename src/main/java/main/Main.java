package main;

import java.net.URL;
import model.Mailer;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

/**
 * Класс с точкой входа в программy.
 *
 * @author Mikalay
 */
public class Main {
//ТЕСТЫ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
//ВИД - МОДЕЛЬ MVC!!!!!!!!  
//РЕФАКТОРИНГ!!!!!!!!!!!!!!!!!!!!!!!!!    
//ДОКУМЕНТАЦИЯ!!!!!!!!!!!!!!!!!!!!!!!!!!
//ПРОВЕРКА УСПЕШНОЙ ОТПРАВКИ ПОЧТЫ!!!!!!!!!!!!!!!! 
//ЗАПУСК БЕЗ ДЖАВА_МАШИНЫ!!!!!!!!!!!!!!!   
//картинки в почту через ссылку    
//план на сегодня:
//проверка почты через регулярку
//добавление  в мемо - интерфейс
// в XLSXParser добавить ошибку в лог - "не тип строки"
//добавить возможность не только для одного листа    
//проверка - если есть хоть 1 null, то имя неполное, если все null - то игнорим
//при ошибке выводим строку номер и текст тот, что есть
//добавление инструкции в представление  
//более плотное логгирование и проверка возвращаемых значений для всех операций 
//просмотреть каждый catch  
// подправить документацию 
//проверка строк на размер
//проверка наличия и корректности почты 
//фиксированный ввод в текстполе
//вложение нескольких ссылок и картинок
//просмортеть шаблон письма https://commons.apache.org/proper/commons-email/userguide.html    
//ПОЗВОНИТЬ через веб-агент - для высвечивание перед ссылкой
//https://www.tutorialspoint.com/html/html_email_links.htm - разные ссылки   
//нормальная регулярка для почты    

//////////////////////////////////  
//<template> </tempate>         //
//<siteLink></siteLink>       //
//<mailLink></mailLink>       //
////////////////////////////////// 
    public static void main(String[] args) throws Exception {
        //   MyLog.logMsg("Начало работы программы.");
        //  testFormMessages();
        //   MainWindow frame = new MainWindow();
        //  testSend1();
        String str = "Hello!\r\nIt is my mail. Link for me: "
                + "\r\n <siteLink> https://vk.com/klimashevich.mikalay </siteLink>"
              + "\r\nlink for my github:\r\n "
                + "<siteLink>https://github.com/klimashevichmikalay</siteLink>"
              + "\r\nSend me: <mailLink>klimashevich.mikalay@mail.ru</mailLink>"
              + "\r\nGood luck!!";
        
      Mailer m =  new  Mailer();
      m.SendMail2(str);
      
    //  System.out.println(m.formMessage(str));

    }    
}
