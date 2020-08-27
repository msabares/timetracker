import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

/**
 * At this tests we are trying to put date on the end date picker and keep the start picker blank
 */

//verify report button exists

//verify the end date picker is available
WebUI.verifyElementPresent(findTestObject('Report/open_end_calendar'), 0)

//open the end date picker
WebUI.click(findTestObject('Report/open_end_calendar'))

//click on previous button on the end datepicker
WebUI.click(findTestObject('Report/button_previous'))

//select march 23
WebUI.click(findTestObject('Report/March_23'))

//the message appears under the start picker to show the you have to select start date as well
WebUI.verifyElementPresent(findTestObject('Report/alert_Please select the start date'), 0)


