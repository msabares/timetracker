import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


WebUI.waitForElementVisible(findTestObject('Object Repository/Person List/table_personList'), 0)

'Check peopleList table and its headers are present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/th_Full Name'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/th_Email'), 0)

//WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/th_Department'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/table_personList'), 0)

'Check Pagination elements'
WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/div_pagination'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/btn_pg1'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/btn_pg2'), 0)

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(3 //Used specifically for running in headless, otherwise it won't work???
    )

/*
 * Validate elements are there in the expect asc order by default
 */
'Get the peopleList table'
WebElement TableAsc = driver.findElement(By.xpath('//table/tbody'))

'Get each row in the peopleListy '
List<WebElement> rows_tableAsc = TableAsc.findElements(By.xpath('//table/tbody/tr'))

'count the number of row to loop through'
int rows_countAsc = rows_tableAsc.size()

'loop and compare values with nameListAsc array'
for (int row = 0; row < rows_countAsc; row++) {
    assert (nameListAsc[row]).equals(rows_tableAsc.get(row).findElements(By.tagName('td')).get(0).getText())
}

'Go to page 2 to check the element in the second page.'
WebUI.click(findTestObject('Object Repository/Person List/btn_pg2'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/cell_yeYing'), 0)

WebUI.click(findTestObject('Object Repository/Person List/btn_pg1'))

/*
 * Validate elements are sorted in desc order
 */
'Sort in dsec order'
WebUI.click(findTestObject('Object Repository/Person List/th_Full Name'))

'Get the peopleList table'
WebElement TableDesc = driver.findElement(By.xpath('//table/tbody'))

'Get each row in the peopleListy '
List<WebElement> rows_tableDesc = TableDesc.findElements(By.xpath('//table/tbody/tr'))

'count the number of row to loop through'
int rows_countDesc = rows_tableDesc.size()

'loop and compare values with nameListAsc array in reverse oder'
for (int row = 0; row < rows_countDesc; row++) {
    System.out.println(rows_tableDesc.get(row).findElements(By.tagName('td')).get(0).getText())

    assert (nameListDesc[row]).equals(rows_tableDesc.get(row).findElements(By.tagName('td')).get(0).getText())
}

'Go to page 2 to check the element in the second page.'
WebUI.click(findTestObject('Object Repository/Person List/btn_pg2'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Person List/cell_abellSmithLuke'), 0)

