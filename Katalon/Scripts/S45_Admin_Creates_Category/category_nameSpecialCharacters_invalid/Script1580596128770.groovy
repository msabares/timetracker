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
 * Verify that special characters cause validation errors
 * in Name field 
 * 
 * @author Matt
 *
 */
'wait'
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'verify no errors present at start of modal openeing'
WebUI.callTestCase(findTestCase('s45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

'set text of category name to include special characters'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), '*@#&@')

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/lbl_categoryDescription'))

'verify error messages present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/error_category_nameSpecialChar'), 0)

