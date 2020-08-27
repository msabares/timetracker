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

	Test save/update process of a valid record

**/
//Navigate to calendar page (part of common test)
not_run: WebUI.callTestCase(findTestCase('common_test/valid_login_IFlynn'), [:], FailureHandling.STOP_ON_FAILURE)

//Select a record
WebUI.click(findTestObject('Calendar/s47 Test Objects/a_1000 - 1200Prep'))

//Edit mode
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/button_Edit'))

//Enter valid data across all inputs
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_End Time'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

WebUI.setText(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), newComment)

WebUI.selectOptionByIndex(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), newTaskIndex)

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_onSite_label'))

//Check no validation messages
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Save the record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_btnSave'))

//Check that record is correctly displayed on the calendar
WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Calendar/s47 Test Objects/a_1100 - 100Grading'), 0)

//Select the record
WebUI.click(findTestObject('Calendar/s47 Test Objects/a_1100 - 100Grading'))

//Verify all data is correct
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_startTimeValue'), newStartTime + 
    '\nSelected time')

WebUI.verifyElementText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_endTimeValue'), newEndTime + '\nSelected time')

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), 'value', 
    newComment, 0)

WebUI.verifyOptionSelectedByIndex(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), newTaskIndex, 
    0)

