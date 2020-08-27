 /*
 * This test will verify that the popover has labels present
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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

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
WebUI.verifyElementPresent(findTestObject('Calendar/record2_Jan6_1000 - 1200Teaching'), 0)

//click prep record 
WebUI.click(findTestObject('Calendar/record2_Jan6_1000 - 1200Teaching'))

//verify that the popover has its labels displayed
WebUI.waitForElementVisible(findTestObject('Calendar/s49 Test Objects/teaching_popover'), 1)
//get the title text to test later
String Title = WebUI.getText(findTestObject('Calendar/s49 Test Objects/h5_Teaching - Mon 06 Jan 2020'))
//get the start time to test later
String startTime = WebUI.getText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_startTimeValue'))
//get the end time to test later
String endTime = WebUI.getText(findTestObject('Object Repository/Calendar/s49 Test Objects/input_endTimeValue'))
//get the text area value
String comment = WebUI.getAttribute(findTestObject('Calendar/s49 Test Objects/teaching_textarea_Comments'), 'value')

//check the title is match with data base
assert (Title == 'Teaching - Mon, 06 Jan 2020')
//if (Title == 'Teaching - Mon, 06 Jan 2020') {
//    assert true
//} else {
//    assert false
//}

//check the start time is match with data base
assert (startTime == '10:00 AM\nSelected time')
//if (startTime == '10:00:00') {
//    assert true
//} else {
//    assert false
//}

//check the end time is match with data base
assert (endTime == '12:00 PM\nSelected time')
//if (endTime == '12:00:00') {
//    assert true
//} else {
//    assert false
//}

//check the comment is match with data base
assert (comment == 'Teaching Math')
//if (comment == 'Teaching Math') {
//    assert true
//} else {
//    assert false
//}

