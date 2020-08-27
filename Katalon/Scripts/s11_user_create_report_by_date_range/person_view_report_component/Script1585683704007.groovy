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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW

/**
 * At this test we verify all the report componnet on the page
 */
//verify all the header on the report table
WebUI.callTestCase(findTestCase('s11_user_create_report_by_date_range/common_select_date_range'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Report/th_Actual Complementary Function'), 0)

WebUI.verifyElementNotPresent(findTestObject('Report/th_Actual Other Assignment'), 0)

WebUI.verifyElementPresent(findTestObject('Report/th_Actual Other Not Listed'), 0)

WebUI.verifyElementPresent(findTestObject('Report/th_Actual Student Contact'), 0)

WebUI.verifyElementPresent(findTestObject('Report/th_Scheduled Paid Hours'), 0)

WebUI.verifyElementPresent(findTestObject('Report/th_Timesheet Date'), 0)

WebUI.verifyElementPresent(findTestObject('Report/th_Timesheet Total'), 0)

WebUI.verifyElementPresent(findTestObject('Report/th_Variant Hours'), 0)

//verify the user lable info such as name, program name
WebUI.verifyElementPresent(findTestObject('Report/b_Name'), 0)

WebUI.verifyElementPresent(findTestObject('Report/b_Program'), 0)

WebUI.verifyElementPresent(findTestObject('Report/b_Scheduled Hours'), 0)

WebUI.verifyElementPresent(findTestObject('Report/b_Employment Status'), 0)

