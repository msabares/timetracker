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



//GOING BACKWARDS WITH FOR LOOP
boolean backEnough = false
String dateLabel = ""

//Loop clicking previous week until we find the correct week. (determined by the text of the label.)
while (!backEnough)
{
	//Click the previous week button
	WebUI.click(findTestObject('Calendar/button_Calendar_fc-prev-button'))

	try{
		//Get the text of the header/label, and put it in the variable
		
		dateLabel = WebUI.getText(findTestObject('Calendar/weekLabel'))
	}
	catch(Exception e){}
	finally{
		//Compare to what we are looking for
		backEnough = dateLabel == "Jan 5 – 11, 2020"
	}
}
