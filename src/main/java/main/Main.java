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
 
//ПЕРЕСМОТР СООБЩЕНИЙ ЛОГА
//ЗАПУСК БЕЗ ДЖАВА_МАШИНЫ!!!!!!!!!!!!!!!       
//РЕАЛЬНАЯ МОДЕЛЬ МВЦ    
//ТЕСТЫ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
//ДОКУМЕНТАЦИЯ
//РЕФАКТОРИНГ!!!!
  
    public static void main(String[] args) {

        MainWindow mw = new MainWindow();
        mw.setController(new Controller());
    }
}
