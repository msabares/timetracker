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
import java.time.temporal.ChronoUnit as ChronoUnit
import java.time.LocalDate as LocalDate
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW

/**
 * At this date we select valid date range and verify the result
 */
//click on the report button
//verify correct date and values are shown
WebUI.callTestCase(findTestCase('s11_user_create_report_by_date_range/common_select_date_range'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-01-01'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-01-15'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-01-29'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-02-12'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-02-26'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-03-11'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2020-03-25'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_Summary'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_256'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_2'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_4'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_0'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_17'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_23'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_-233'), 0)

