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

	Check that saving a record with invalid data doesn't work

**/
//Navigate to calendar page (part of common test)
not_run: WebUI.callTestCase(findTestCase('common_test/valid_login_IFlynn'), [:], FailureHandling.STOP_ON_FAILURE)

//Select a record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'))

//Edit mode
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/button_Edit'))

String origStartTime = WebUI.getText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_startTimeValue'))

String origEndTime = WebUI.getText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_endTimeValue'))

//Enter a start time LATER than end time
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check validation messages
WebUI.verifyElementPresent(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'), 0)

//Hit save button
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_btnSave'))

//Close record
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/buttonX'))

//Check that record remains in same spot on calendar
WebUI.verifyElementPresent(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'), 0)

//Open same record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'))

//Check that all data is original valid data
assert origStartTime == WebUI.getText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_startTimeValue'))

assert origEndTime == WebUI.getText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_endTimeValue'))

WebUI.click(findTestObject('Calendar/s49 Test Objects/buttonX'))

