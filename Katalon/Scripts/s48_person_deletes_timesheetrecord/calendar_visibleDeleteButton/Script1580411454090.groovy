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


////Navigate to the page (if it wasn't already done)
//WebUI.navigateToUrl('http://localhost:8080/calendar/')
//Check that calendar is present.
//Check for the week calendar itself.
//Check that the view is WEEK
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

//Check that first record is present.
//Check that the title is "Prep"
//Check that it is Jan 6 from 800 to 1000
//Check that delete button for the record is available
WebUI.verifyElementPresent(findTestObject('Calendar/record1_deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record1_Jan6_800 - 1000Prep'), 0)

//Check that second record is present.
//Check that the title is "Teaching"
//Check that it is Jan 6 from 10 to 12
//Check that delete button for the record is available
WebUI.verifyElementPresent(findTestObject('Calendar/record2_deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record2_Jan6_1000 - 1200Teaching'), 0)

//Check that third record is present.
//Check that the title is "Meeting"
//Check that it is Jan 7 from 8 to 15
//Check that delete button for the record is available
WebUI.verifyElementPresent(findTestObject('Calendar/record3_deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record3_Jan7_800 - 300Meetings'), 0)

//Check that fourth record is present.
//Check that the title is "Conference"
//Check that it is from Jan8 0900 to Jan10 1200
//Check all 3 days
//Check that delete button for the record is available
WebUI.verifyElementPresent(findTestObject('Calendar/record4_Jan10 deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record4_Jan10_1200 - 300Conference'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record4_Jan8 deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record4_Jan8_900 - 1200Conference'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record4_Jan9 deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record4_Jan9_Conference'), 0)

//Check that fifth record is present.
//Check that the title is "Grading Papers" then many As. 255 char task name
//Check that it is on Jan 11 from 0900 to 1300
//Check that delete button for the record is available
WebUI.waitForElementPresent(findTestObject('Calendar/record5_deleteButton'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record5_Jan11_900 - 1300Grading_Papers'), 0)

//Establish variables for the second loop
boolean fwdEnough = false

//Loop clicking next week until we find the correct week. (determined by the text of the label.)
while (!(fwdEnough)) {
    //Click the previous week button
    WebUI.click(findTestObject('Calendar/button_Calendar_fc-next-button'))

    try {
        //Get the text of the header/label, and put it in the variable
        dateLabel = WebUI.getText(findTestObject('Calendar/weekLabel'))
    }
    catch (Exception e) {
    } 
    finally { 
        //Compare to what we are looking for
        fwdEnough = (dateLabel == 'Jan 12 – 18, 2020')
    }
}

//Check that sixth record is present.
//Check that the title is "Vacation"
//Check that it is from Jan13-17
//Check all 5 days
//Check all have delete buttons
WebUI.verifyElementPresent(findTestObject('Calendar/record6_Jan13_17 delete_Button'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record6_Jan13_and_17_1200Vacation'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record6_Jan14_16 delete_Button'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/record6_Jan14_16_Vacation'), 0)


