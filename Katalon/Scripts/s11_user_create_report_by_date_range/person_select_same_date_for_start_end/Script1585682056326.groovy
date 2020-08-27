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
 * At this test we are trying to verify what it will show on the report when we have two task on the 
 * same date.
 */
//click on the end date picker
//click on the start picker
WebUI.click(findTestObject('Report/open_start_calendar'))

def someDate = new Date()

month = someDate.getMonth()

for (int i = 0; i < (month - 3); i++) {
    //click on the previous button on the start calendar
    WebUI.click(findTestObject('Report/button_previous'))
}

WebUI.delay(2)

//select Jan 1
WebUI.click(findTestObject('Report/march_30'))

//click on the end picker
WebUI.verifyElementPresent(findTestObject('Report/open_end_calendar'), 0)

WebUI.click(findTestObject('Report/open_end_calendar'))

for (int i = 0; i < (month - 3); i++) {
    //click on the previous button on the start calendar
    WebUI.click(findTestObject('Report/button_previous'))
}

//select march 30
WebUI.click(findTestObject('Report/march_30'))

//verify table shows corect date and values
WebUI.verifyElementPresent(findTestObject('Report/td_2020-03-30'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_4'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_-4'), 0)

WebUI.verifyElementPresent(findTestObject('Report/td_Summary'), 0)

