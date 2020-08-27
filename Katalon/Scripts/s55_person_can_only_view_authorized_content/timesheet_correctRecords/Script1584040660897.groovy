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

WebUI.callTestCase(findTestCase('common_test/valid_login_BSmith'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

//wait for calendar visible
WebUI.waitForElementVisible(findTestObject('Object Repository/Calendar/CalendarComponent'), 0)

//refresh to allow for drag and drop of task
WebUI.refresh()

//Establish variables for the first loop
boolean backEnough = false

String dateLabel = ''

//Loop clicking previous week until we find the correct week. (determined by the text of the label.)
//go to specific week
while (!(backEnough)) {
    //Click the previous week button
    WebUI.click(findTestObject('Calendar/button_Calendar_fc-prev-button'))

    try {
        //Get the text of the header/label, and put it in the variable
        dateLabel = WebUI.getText(findTestObject('Calendar/weekLabel'))
    }
    catch (Exception e) {
    } 
    finally { 
        //Compare to what we are looking for
        backEnough = (dateLabel == 'Mar 22 – 28, 2020')
    }
}

//verify no record in specific week
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Calendar/700-730-TaskA'), 0)

//add record
WebUI.dragAndDropByOffset(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_Development'), 200, 0)

WebUI.delay(3)
//Psuedo Log-out
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/deleteToken'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.refresh()

//Log in
WebUI.callTestCase(findTestCase('common_test/valid_login_BSmith'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

//wait for calendar visible
WebUI.waitForElementVisible(findTestObject('Object Repository/Calendar/CalendarComponent'), 0)

//refresh to allow for drag and drop of task
WebUI.refresh()

//Establish variables for the first loop
 backEnough = false

 dateLabel = ''

//Loop clicking previous week until we find the correct week. (determined by the text of the label.)
//go to specific week
while (!(backEnough)) {
	//Click the previous week button
	WebUI.click(findTestObject('Calendar/button_Calendar_fc-prev-button'))

	try {
		//Get the text of the header/label, and put it in the variable
		dateLabel = WebUI.getText(findTestObject('Calendar/weekLabel'))
	}
	catch (Exception e) {
	}
	finally {
		//Compare to what we are looking for
		backEnough = (dateLabel == 'Mar 22 – 28, 2020')
	}
}

//verify element that was previuosly added is in te correct location
WebUI.verifyElementPresent(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_Development'), 0)





