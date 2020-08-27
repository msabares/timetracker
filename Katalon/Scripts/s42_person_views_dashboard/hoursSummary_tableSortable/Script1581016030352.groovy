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

/**
 * Accaptence Test:
 * hoursSummary_tableSortable
 * 
 * Verify that the table is sorted alphabetically by default and the sort clicks work and presist when changing weeks.
 */
WebUI.delay(1)

WebDriver driver = DriverFactory.getWebDriver()

//Check sort by name
WebElement Table = driver.findElement(By.xpath('//table/tbody'))

List<WebElement> rows_table = Table.findElements(By.xpath('//table/tbody/tr'))

int rows_count = rows_table.size()

for (int row = 0; row < rows_count; row++) {
    assert (this.currentWeek_sortedListAlpha[row]).equals(rows_table.get(row).findElements(By.tagName('td')).get(0).getText())
}

//Check sort by name reverse
WebUI.delay(1)

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/th_Task NEW'))

Table = driver.findElement(By.xpath('//table/tbody'))

rows_table = Table.findElements(By.xpath('//table/tbody/tr'))

for (int row = 0; row < rows_count; row++) {
    assert (this.currentWeek_sortedListAlphaRev[row]).equals(rows_table.get(row).findElements(By.tagName('td')).get(0).getText())
}

WebUI.delay(2)

//Check sort by time
WebUI.click(findTestObject('Dashboard/hoursSummary_Component/th_Hours New'))

Table = driver.findElement(By.xpath('//table/tbody'))

rows_table = Table.findElements(By.xpath('//table/tbody/tr'))

for (int row = 0; row < rows_count; row++) {
    assert (this.currentWeek_sortedListNumer[row]).equals(rows_table.get(row).findElements(By.tagName('td')).get(1).getText())
}

WebUI.delay(2)

//Check sort by time reverse
WebUI.click(findTestObject('Dashboard/hoursSummary_Component/th_Hours New'))

Table = driver.findElement(By.xpath('//table/tbody'))

rows_table = Table.findElements(By.xpath('//table/tbody/tr'))

for (int row = 0; row < rows_count; row++) {
    assert (this.currentWeek_sortedListNumerRev[row]).equals(rows_table.get(row).findElements(By.tagName('td')).get(1).getText())
}

