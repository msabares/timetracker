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

/**
 * Test out of bounds length entries for Name
 * Verify that single character can be input
 * Verify presence of error message
 * Verify that too long of input gets truncated
 * Verify no error message
 * Verify that input is actually 100 characters (proving truncation)
 * 
 * @author Matt
 *
 */
'wait'
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'verify no errors present at start of modal openeing'
WebUI.callTestCase(findTestCase('s45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

'send m once'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), 'M')

WebUI.click(findTestObject('Object Repository/Create Category/lbl_categoryName'))

'verify errors present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/error_category_nameLength'), 5)

input = ('M' * 101)

'send m 101 times'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), input)

'verify length was cut to 100 characters, and no errors are present'
assert WebUI.getAttribute(findTestObject('Object Repository/Create Category/input_categoryName'), 'value').length() == 100

'verify error messages'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameLength'), 0)

