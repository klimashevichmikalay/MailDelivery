package main;

import controller.Controller;
import view.MainWindow;

/**
 * Класс с точкой входа в программу.
 *
 * @author Mikalay
 * @version 1.0
 */
public class Main {
//необходимость ввести хоть что-то!?    
//isSizeGood() - УБРАТЬ!!!
//УДАЛИТЬ ПРОБЕЛЫ И ТАБУЛЯЦИЮ 
//ПЕРЕСМОТР СООБЩЕНИЙ ЛОГА И ИНТЕРФЕЙСА    
//КОММЕНТАРИИ К КОНСТАНТам    
//СООБЩЕНИЕ О НЕУДАЧНОЙ ПЕРЕДАЧИ СООБЩЕНИЯ    
//ЛОГ ВСЕГО И СООБЩЕНИЕ    
//РЕФАКТОРИНГ!!!!!!!!!!!!!!!!!!!!!!!!! 
//ИСКЛЮЧЕНИЯ!!!!!    
//БАЗОВЫЕ СТАНДАРТНЫЕ ПОЛЯ ЗАПОЛНИТЬ!!!!! и убрать проверку размера   
//ОТЧЕСТВА МОЖЕТ НЕ БЫТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    
//ДОСТАВИЛАСЬ ЛИ ПОЧТА???????!!!!! и сколько не доставилось? число 
//ТЕСТЫ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
//ВИД - МОДЕЛЬ MVC!!!!!!!!
//ДОКУМЕНТАЦИЯ!!!!!!!!!!!!!!!!!!!!!!!!!!
//ПРОВЕРКА УСПЕШНОЙ ОТПРАВКИ ПОЧТЫ!!!!!!!!!!!!!!!! 
//ЗАПУСК БЕЗ ДЖАВА_МАШИНЫ!!!!!!!!!!!!!!!   
//ПОТОКИ!!!!!!!!!!!!!!!!! 

//Если я не ввел порт??    
//все исключения заносить в лог, все исключения растаскать по блокам
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
//если неправильный тип и оно не считало его?? 
//проверить равенство обработанных сообщений и объектных(сравнить количество)    
//удалить пробелы из логина и пароля при  взятии текста из приложения и при взятии из таблицы
//при невозможности подключиться к серверу сказать об этом и записать в лог    
//переместить header в messageInfo как static 
//изменить код контроллера!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    
    public static void main(String[] args) {

        MainWindow mw = new MainWindow();
        mw.setController(new Controller());
    }
}
