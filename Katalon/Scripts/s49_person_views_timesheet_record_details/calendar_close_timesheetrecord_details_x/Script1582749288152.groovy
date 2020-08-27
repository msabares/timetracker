 /*
 * This test will verify that the record data is present and valid
 *
 *   Javad and jordan
 *
 */ import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
//Establish variables for the first loop
//Loop clicking previous week until we find the correct week. (determined by the text of the label.)
//Click the previous week button
//Get the text of the header/label, and put it in the variable
//Compare to what we are looking for
//verify if prep event is visible on the calendar
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//open to calendar 
//user back to the calendar
WebUI.verifyElementPresent(findTestObject('Calendar/CalendarComponent'), 0)

//Establish variables for the first loop
boolean backEnough = false

String dateLabel = ''

//Loop clicking previous week until we find the correct week. (determined by the text of the label.)
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
        backEnough = (dateLabel == 'Jan 5 – 11, 2020')
    }
}

//verify if prep event is visible on the calendar
WebUI.verifyElementPresent(findTestObject('Calendar/record1_Jan6_800 - 1000Prep'), 0)

//click TSR
WebUI.click(findTestObject('Calendar/record1_Jan6_800 - 1000Prep'))

//verify that the TSR detail are displayed
WebUI.waitForElementVisible(findTestObject('Calendar/s49 Test Objects/prep_modal'), 0)

WebUI.click(findTestObject('Calendar/s49 Test Objects/buttonX'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)
//veryfy that modal not present
WebUI.verifyElementNotPresent(findTestObject('Calendar/s49 Test Objects/Popover'), 0)

