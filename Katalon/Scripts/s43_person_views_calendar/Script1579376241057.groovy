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
import org.openqa.selenium.Keys as Keys


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

WebUI.verifyElementVisible(findTestObject('Calendar/record1_Jan6_800 - 1000Prep'))

//Check that second record is present.
//Check that the title is "Teaching"
//Check that it is Jan 6 from 1000 to 1200
WebUI.verifyElementVisible(findTestObject('Calendar/record2_Jan6_1000 - 1200Teaching'))

//Check that third record is present.
//Check that the title is "Meetings"
//Check that it is on Jan 7 from 0800 to 1500
WebUI.verifyElementVisible(findTestObject('Calendar/record3_Jan7_800 - 300Meetings'))

//Check that fourth record is present.
//Check that the title is "Conference"
//Check that it is from Jan8 0900 to Jan10 1200
//Check all 3 days
WebUI.verifyElementVisible(findTestObject('Calendar/record4_Jan8_900 - 1200Conference'))

WebUI.verifyElementVisible(findTestObject('Calendar/record4_Jan9_Conference'))

WebUI.verifyElementVisible(findTestObject('Calendar/record4_Jan10_1200 - 300Conference'))

//Check that fifth record is present.
//Check that the title is "Grading Papers" then many As. 255 char task name
//Check that it is on Jan 11 from 0900 to 1300
WebUI.verifyElementVisible(findTestObject('Calendar/record5_Jan11_900 - 1300Grading_Papers'))

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
WebUI.verifyElementVisible(findTestObject('Calendar/record6_Jan13_and_17_1200Vacation'))

WebUI.verifyElementVisible(findTestObject('Calendar/record6_Jan14-16_Vacation'))


