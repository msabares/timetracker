import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

WebUI.comment('verity the table appeared')

WebUI.verifyElementPresent(findTestObject('Edit Category/Table/table_Work_Assignment'), 0)

WebDriver driver = DriverFactory.getWebDriver()

WebElement table = driver.findElement(By.xpath('//table/tbody'))

WebUI.comment('get the heads of the table')

List<WebElement> heads = table.findElements(By.xpath('//table/thead/tr/th/div'))

int columns_count = heads.size()

WebUI.comment('get the rows of the table')

List<WebElement> rows_table = table.findElements(By.xpath('//table/tbody/tr'))

int rows_count = rows_table.size()

WebUI.comment('verify the heads texts equal to heads list variable')

for (int c = 0; c < (columns_count - 1); c++) {
    assert (this.tableHeads[c]).equals(heads.get(c).getText())
}

WebUI.comment('verify the text of each cell in each rows equals to items in the defined variable list')

List<WebElement> allCells = [this.row1, this.row2, this.row3, this.row4, this.row5, this.row6, this.row7]

for (int row = 0; row < rows_count; row++) {
    List<WebElement> cells = rows_table.get(row).findElements(By.tagName('td'))

    for (int c = 0; c < (columns_count - 1); c++) {
        assert ((allCells[row])[c]).equals(cells.get(c).getText())
    }
}

WebUI.comment('verify table sorted by category name')

WebUI.click(findTestObject('Edit Category/Table/div_Work Assignment'))

List<WebElement> allCells_sorted = [this.row5, this.row4, this.row1, this.row6, this.row2, this.row7, this.row3]

for (int row = 0; row < rows_count; row++) {
	List<WebElement> cells = rows_table.get(row).findElements(By.tagName('td'))

	for (int c = 0; c < (columns_count - 1); c++) {
		assert ((allCells_sorted[row])[c]).equals(cells.get(c).getText())
	}
}

