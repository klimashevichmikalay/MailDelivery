package main;

import java.net.URL;
import java.util.Arrays;
import java.util.Vector;
import model.Mailer;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import view.MainWindow;

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
//ПОТОКИ!!!!!!!!!!!!!!!!!    
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
//тема письма - отдельное поле
//от коого письмо - отдельное поле    
//посмотреть возможные ошибки, проверки файлом на присутствие
//новые строки в тегах письма 
//убрать пробелы из тегов    

//////////////////////////////////  
//<template> </tempate> +        //
//<siteLink></siteLink> +        //
//<mailLink></mailLink> +        //
//<image></image>               //
////////////////////////////////// 
    public static void main(String[] args) throws Exception {
        //   MyLog.logMsg("Начало работы программы.");
        //  testFormMessages();
        MainWindow frame = new MainWindow();
        //  testSend1();
        /*    String str = "Привет!\r\nЭто мой очередной тест. Ссылка на меня в ВК: "
                + "\r\n <siteLink> https://vk.com/klimashevich.mikalay </siteLink>"
                + "\r\nА это мой гитхаб:\r\n "
                + "<siteLink>https://github.com/klimashevichmikalay</siteLink>"
                + "\r\nПришите мне на почту: <mailLink>klimashevich.mikalay@mail.ru</mailLink>"
                + "\r\nПример пустыни:\r\n"
                + " <image>C:\\\\Users\\\\Public\\\\Pictures\\\\Sample Pictures\\\\Desert.jpg</image>"
                + "\r\nПустынь много не бывает:\r\n"
                + "<image>C:\\\\Users\\\\Public\\\\Pictures\\\\Sample Pictures\\\\Desert.jpg</image>"
                + "\r\nGood luck!!"
                + "\r\nНе могу удержаться.. милаху на последок:"
                + "<  image  >C:\\\\Users\\\\Public\\\\Pictures\\\\Sample Pictures\\\\Koala.jpg<  /  image >";

        Mailer m = new Mailer();
        m.Send(str);*/

        //  System.out.println(m.formMessage(str));
    }
}
