package com.tests;

import com.utils.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExcelUtilTest extends TestBase {

    @Test
    public void readExcelFile(){

        //Create an object from excel utils
        //it accepts two arguments
        //Argument 1 : location of the file (path)
        //Argument 2 : sheet that we want to open(sheetName)

        ExcelUtil testData = new ExcelUtil("src/resources/EurotechTestLast.xls","Test Data");

        //How many rows in the sheet
        System.out.println("testData.rowCount() = " + testData.rowCount());

        //How many columns in the sheet
        System.out.println("testData.columnCount() = " + testData.columnCount());

        //get all columns names
        System.out.println("testData.getColumnsNames() = " + testData.getColumnsNames());


        //get all data in list of string
        List<Map<String, String>> dataList = testData.getDataList();
        for (Map<String, String> oneRow : dataList) {
            System.out.println("oneRow = " + oneRow);

        }

        //how can I get third row (berlin)
        System.out.println("dataList.get(3) = " + dataList.get(2));

        //get Fevzi's company
        System.out.println("dataList.get(4).get(\"Company\") = " + dataList.get(4).get("Company"));

        //get Mehmet's title
        System.out.println("dataList.get(4).get(\"Title\") = " + dataList.get(3).get("Title"));

        //get array
        String[][] dataArray = testData.getDataArray();
        System.out.println(Arrays.deepToString(dataArray));
    }
}
