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
	This covers six different acceptance tests
	editRecord_start_valid
	editRecord_end_valid
	editRecord_comment_minLength
	editRecord_comment_maxLength
	editRecord_changeSite
	editRecord_changeTask

**/
not_run: WebUI.callTestCase(findTestCase('common_test/valid_login_IFlynn'), [:], FailureHandling.STOP_ON_FAILURE)

//Select a timesheet record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'))

//Edit Mode
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/button_Edit'))

//Set a start time EARLIER than end time
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check no validation message
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Select an endTime that is LATER than startTime
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_End Time'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check no validation message
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Comment field is empty
WebUI.verifyElementText(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), '')

//Check no validation message
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Comment field has 255 characters entered
comment = ('M' * 255)

WebUI.setText(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), comment)

//Check no validation message
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Comment field gets 256 characters
comment = ('M' * 256)

WebUI.setText(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), comment)

//Check length of comment field is 255 characters
String input_result = WebUI.getAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), 
    'value')

assert input_result.length() == 255 //Ensures the input field limits the number of characters

WebUI.delay(1)

//Change Site
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_onSite_label'))

//Change Task
WebUI.selectOptionByIndex(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), 1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/buttonX'))

