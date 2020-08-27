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

//Calls common_edit_task test case
//Check that spinner is present
WebUI.verifyElementPresent(findTestObject('Create Task/spinner_task_loading'), 0)

//Verify the proper elements exist and have the proper content.
WebUI.verifyElementPresent(findTestObject('Create Task/lbl_header_title'), 0)

WebUI.verifyElementText(findTestObject('Create Task/lbl_header_title'), 'Create Task')

WebUI.verifyElementPresent(findTestObject('Create Task/lbl_title'), 0)

WebUI.verifyElementText(findTestObject('Create Task/lbl_title'), 'Title:')

WebUI.verifyElementPresent(findTestObject('Create Task/lbl_estimate'), 0)

WebUI.verifyElementText(findTestObject('Create Task/lbl_estimate'), 'Hours Estimate:')

WebUI.verifyElementPresent(findTestObject('Create Task/lbl_category'), 0)

WebUI.verifyElementText(findTestObject('Create Task/lbl_category'), 'Category:')

//Verify the proper input elements are present
WebUI.verifyElementPresent(findTestObject('Create Task/input_title'), 0)

WebUI.verifyElementPresent(findTestObject('Create Task/input_estimate'), 0)

WebUI.verifyElementPresent(findTestObject('Create Task/input_category'), 0)

//Verify the proper buttons are present
WebUI.verifyElementPresent(findTestObject('Create Task/btn_cancel'), 0)

WebUI.verifyElementPresent(findTestObject('Create Task/btn_createTask'), 0)

//Verify no error messages are present
WebUI.verifyElementNotPresent(findTestObject('Create Task/error_title_minLength'), 0)

WebUI.verifyElementNotPresent(findTestObject('Create Task/error_title_specialChar'), 0)

WebUI.verifyElementNotPresent(findTestObject('Create Task/error_estimate'), 0)

WebUI.verifyElementNotPresent(findTestObject('Create Task/error_category'), 0)

WebUI.verifyElementNotPresent(findTestObject('Create Task/error_createEditTask'), 0)

//Verifying the default values that appear when a new modal is opened.
WebUI.verifyElementAttributeValue(findTestObject('Create Task/input_title'), 'value', '', 0)

WebUI.verifyElementAttributeValue(findTestObject('Create Task/input_estimate'), 'value', '0', 0)

WebUI.verifyOptionSelectedByValue(findTestObject('Create Task/input_category'), '', false, 0)

//Verify that if we change values in the modal, close the modal and reopen it, it still should give back the default values.
WebUI.setText(findTestObject('Create Task/input_title'), 'Development')

WebUI.setText(findTestObject('Create Task/input_estimate'), '5.0')

WebUI.selectOptionByIndex(findTestObject('Create Task/input_category'), 1)

//Close Modal Window
WebUI.click(findTestObject('Create Task/btn_cancel'))

//Reopen the modal
WebUI.click(findTestObject('Object Repository/TaskList/button_Create Task'))

//Verify the fields to ensure the modifiied values don't presist.
WebUI.delay(2)

WebUI.verifyElementAttributeValue(findTestObject('Create Task/input_title'), 'value', '', 0)

WebUI.verifyElementAttributeValue(findTestObject('Create Task/input_estimate'), 'value', '0', 0)

WebUI.verifyOptionSelectedByValue(findTestObject('Create Task/input_category'), '', false, 0)

