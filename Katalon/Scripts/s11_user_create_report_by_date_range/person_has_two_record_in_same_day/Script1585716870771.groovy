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
 * At this tests we are testing how two evenets on the same date will be shown on the report
 */
WebUI.navigateToUrl('http://localhost:8080/calendar')

//TODO: This needs to me automated
WebUI.click(findTestObject('Calendar/button_Calendar_fc-prev-button'))

WebUI.click(findTestObject('Calendar/button_Calendar_fc-prev-button'))

//drag and drop task on the calender
//If this test fails, change the x axis to 300
WebUI.dragAndDropByOffset(findTestObject('TaskList/All Task items/Page_pmg-ui/li_A'), 250, 200)

//delay
WebUI.delay(2)

//puttin comment
WebUI.comment('drag the bottom edge of the event element to downside 150px, wait 5 second for API response')

WebUI.delay(2)

//drag another tasks to have two tasks on the same day
//If this test fails, change the x axis to 300
WebUI.dragAndDropByOffset(findTestObject('TaskList/All Task items/Page_pmg-ui/li_A'), 251, 150)

WebUI.delay(2)

//go to the home page
WebUI.verifyElementPresent(findTestObject('Navbar/btn_reports'), 0)

WebUI.click(findTestObject('Navbar/btn_reports'))

WebUI.delay(1)

//click on the report button
WebUI.click(findTestObject('Object Repository/Report/btn_report_1'))

WebUI.delay(1)

//click on start picker
WebUI.click(findTestObject('Report/open_start_calendar'))

WebUI.delay(1)

//choose march 30
WebUI.click(findTestObject('Report/march_30'))

WebUI.delay(1)

//click on the end picker
WebUI.click(findTestObject('Report/open_end_calendar'))

WebUI.delay(1)

//choose march 30
WebUI.click(findTestObject('Report/march_30'))

//check correct date on the table
WebUI.verifyElementPresent(findTestObject('Report/td_2020-03-30'), 0)

//check correct values are shown on the table
WebUI.verifyElementPresent(findTestObject('Report/td_4'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_1'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_-3'), 0)

