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

WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Work Assignment'))

'edit the second category, change category name to "New Category"'
WebUI.click(findTestObject('Edit Category/Edit Category/button_edit_2'))

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), this.valid_name)

WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Save'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Create Category/Category Confirmation Modal/button_confirmOk'))

'get the second row and verify the category name has been changed'
WebElement secondRow = CustomKeywords.'dragNdrop.WebUICustomKeywords.getOneRowFromTable'(1)

assert this.valid_name.equals(secondRow.findElements(By.tagName('td')).get(0).getText())

'edit the 4th category, change the parent category '
WebUI.click(findTestObject('Edit Category/Edit Category/button_edit_4'))
WebUI.delay(2)
'select "new Category" as parent category'
WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Category/input_parentCategory'), 2, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)
WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Save'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Create Category/Category Confirmation Modal/button_confirmOk'))
WebUI.delay(2)
'get the 4th row and verify the parent category name has been changed'
WebElement forthRow = CustomKeywords.'dragNdrop.WebUICustomKeywords.getOneRowFromTable'(4)

assert this.valid_name.equals(forthRow.findElements(By.tagName('td')).get(1).getText())

'edit the last category, change category discription to "A new category for SPFA"'
WebUI.click(findTestObject('Edit Category/Edit Category/button_edit_last'))

WebUI.setText(findTestObject('Create Category/input_categoryDescription'), this.valid_description)

WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Save'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Create Category/Category Confirmation Modal/button_confirmOk'))

'get the last row and verify the category decription has been changed'
WebElement lastRow = CustomKeywords.'dragNdrop.WebUICustomKeywords.getOneRowFromTable'(6)

assert this.valid_description.equals(lastRow.findElements(By.tagName('td')).get(2).getText())

