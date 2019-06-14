package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Класс с точкой входа в программy.
 * 
 * @author Mikalay
 */
public class Main {
   
    public static void main(String[] args) throws IOException 
    {
     
        printExselFile();
    }

    public static void printExselFile() throws FileNotFoundException, IOException {

        File myFile = new File("C:\\Users\\Mikalay\\Downloads\\file2.xlsx");
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();

        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            PrintStream printStream = new PrintStream(System.out, true, "utf-8");
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {

                    case Cell.CELL_TYPE_STRING:
                        
                       printStream.print(cell.getStringCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                       printStream.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        printStream.print(cell.getBooleanCellValue() + "\t");
                        break;
                    default:
                }
            }
            System.out.println("");
        }
    }

}
