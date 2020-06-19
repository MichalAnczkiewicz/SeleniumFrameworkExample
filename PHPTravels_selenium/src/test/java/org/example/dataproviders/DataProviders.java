package org.example.dataproviders;

import org.example.utilities.CSVImporter;
import org.testng.annotations.DataProvider;

import java.nio.file.Paths;

public class DataProviders {
    @DataProvider(name = "hotels")
    public Object[][] getCSVDataForPeriodsInAdvance(){
        String filePath = Paths.get(".").toAbsolutePath() + "\\src\\test\\resources\\testdatainadvance.csv";
        return CSVImporter.readDataLineByLine(filePath, ',');
    }
}
