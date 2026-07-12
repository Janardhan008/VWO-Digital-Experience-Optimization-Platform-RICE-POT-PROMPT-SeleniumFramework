package utils;

import constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public final class TestDataProvider {

    private TestDataProvider() {}

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(Method method) {
        return getDataFromExcel("LoginData", method.getName());
    }

    @DataProvider(name = "experimentData")
    public static Object[][] getExperimentData(Method method) {
        return getDataFromExcel("ExperimentData", method.getName());
    }

    @DataProvider(name = "personalizationData")
    public static Object[][] getPersonalizationData(Method method) {
        return getDataFromExcel("PersonalizationData", method.getName());
    }

    @DataProvider(name = "insightsData")
    public static Object[][] getInsightsData(Method method) {
        return getDataFromExcel("InsightsData", method.getName());
    }

    private static Object[][] getDataFromExcel(String sheetName, String testCaseName) {
        String filePath = FrameworkConstants.TEST_DATA_RESOURCES + "/VWOTestData.xlsx";
        List<Map<String, String>> allRows = ExcelReader.getTestData(filePath, sheetName);
        List<Map<String, String>> filteredRows = allRows.stream()
                .filter(row -> row.get("TestCaseName") == null
                        || row.get("TestCaseName").equalsIgnoreCase(testCaseName)
                        || row.get("TestCaseName").equalsIgnoreCase("ALL"))
                .toList();

        if (filteredRows.isEmpty()) {
            return new Object[][]{{}};
        }

        Object[][] data = new Object[filteredRows.size()][1];
        for (int i = 0; i < filteredRows.size(); i++) {
            data[i][0] = filteredRows.get(i);
        }
        return data;
    }
}
