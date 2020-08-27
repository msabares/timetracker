 /*
 * This test will verify that the TSR clicked will have its details displayed
 *
 *   Javad and jordan
 *
 */ import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
//verify comment label is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/label_Comments'), 0)
//verify offsite is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/Off-Site'), 0)
//verify onsite is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/On-Site'), 0)
//start time label is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/label_Start Time'), 0)
//verify end time label is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/label_End Time'), 0)
//verify title is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/title'), 0)
//verify commnet area is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/textarea_Comments'), 0)
//verufy the x button on the modal is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/buttonX'), 0)
//verify the edit button is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/button_Edit'), 0)
//verify the delete button is present
WebUI.verifyElementPresent(findTestObject('Calendar/s49 Test Objects/button_Delete'), 0)

