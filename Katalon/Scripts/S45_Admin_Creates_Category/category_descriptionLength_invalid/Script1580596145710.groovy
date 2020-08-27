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
 * Test handling of larger than boundary Description
 * Verify that input refuses and truncates invalid length
 * Verify no error messages present
 * @author Matt
 *
 */
'Test that Description can have a max of 255 character length in creation process'

'Click create Category > Click Category descripotion > Enter M 256 times > verify error messgae present'

'wait'
WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'Common test case that no errors are present'
WebUI.callTestCase(findTestCase('s45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

String input = 'M' * 256

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), input)

assert WebUI.getAttribute(findTestObject('Object Repository/Create Category/input_categoryDescription'), 'value').length() == 
255

'verify error message is not present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_description'), 0)

