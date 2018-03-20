import javafx.scene.control.Alert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.util.Iterator;

//TODO: Make sheet position selectable in options

public class XLSXReader {

    int sheetNumber = 4;

    public TwoDimensionalArrayList readXLSXFile(File xlsxFile) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(xlsxFile);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        TwoDimensionalArrayList<String> xlsxList = new TwoDimensionalArrayList<>();

        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(sheetNumber);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();

        int rowCount = 0;


        while (rows.hasNext()) {

            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            int columnCount = 0;
            while (cells.hasNext()) {

                cell = (XSSFCell) cells.next();
                xlsxList.addToInnerArray(rowCount, columnCount, cell.toString());
                columnCount++;
            }
            rowCount++;
        }
//        for (int x = 0; x < xlsxList.size(); x++) {
//            for (int y = 0; y < xlsxList.get(x).size(); y++)
//                System.out.print(xlsxList.getFromInnerArray(x, y));
//            System.out.println();
//        }
        return xlsxList;
    }


}
