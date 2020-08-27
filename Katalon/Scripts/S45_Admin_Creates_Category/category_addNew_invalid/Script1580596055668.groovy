import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/**
 *
 * Test the full process of adding an invalid Category
 * Enter an invalid Name
 * Try to save the category
 * Verify proper error messages are present
 *
 * @author Matt
 *
 */
'Wait for modal to load'
WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/modal_createCategory'), 5)

WebUI.delay(2)

'Common test case that no errors are present'
WebUI.callTestCase(findTestCase('s45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/input_categoryName'), 5)

'Enter invalid name'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), invalid_name)

'Enter valid description'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_description)

'Select valid category'
WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Category/input_parentCategory'), 2)

'verify errors  present- name error'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/error_category_nameSpecialChar'), 0)

WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Save'))

'verify modal error present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/error_createCategory'), 0)

