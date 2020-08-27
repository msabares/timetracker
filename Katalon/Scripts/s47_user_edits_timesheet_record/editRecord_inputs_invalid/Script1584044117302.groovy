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
	This tests two separate acceptance tests
	editRecord_start_invalid
	editRecord_end_invalid
*/

//Select a timesheet record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'))

//Edit Mode
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/button_Edit'))

//Set a start time LATER than end time
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'))

WebUI.delay(1)

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check validation message
WebUI.verifyElementPresent(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'), 0)

//Set start time back to original time
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check no more validation message
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Set end time EARLIER than start time
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_End Time'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_decreaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check validation message
WebUI.verifyElementPresent(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'), 0)

//Set end time back to original time
WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/input_End Time'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Calendar/s47 Test Objects/editRecord_increaseButton'))

WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_editTime_closeButton'))

//Check no more validation message
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Close record
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/buttonX'))

