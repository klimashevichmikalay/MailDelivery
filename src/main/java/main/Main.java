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

//РЕАЛЬНАЯ МОДЕЛЬ МВЦ  
//РЕФАКТОРИНГ!!!!
//ПЕРЕСМОТР СООБЩЕНИЙ ЛОГА     
//ДОКУМЕНТАЦИЯ    
//ТЕСТЫ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
    public static void main(String[] args) {

        MainWindow mw = new MainWindow();
        mw.setController(new Controller());
    }
}
