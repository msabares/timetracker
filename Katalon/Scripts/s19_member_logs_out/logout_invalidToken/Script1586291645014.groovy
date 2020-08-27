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
import groovy.json.JsonSlurper


//The setup suite already logs you in so we'll need to immediently log out
WebUI.click(findTestObject('Object Repository/Navbar/btn_moreOptions'))
WebUI.click(findTestObject('Object Repository/Navbar/btn_logout'))

//Get the current token to be modified later
def currentToken = WebUI.executeJavaScript('return localStorage.getItem("user");', [])

//Click the logout button to confirm logut
WebUI.click(findTestObject('Object Repository/confirm logout/btn_logout'))

//Using JsonSlurper, we'll extract the token and modify it to be invalid
JsonSlurper slurper = new JsonSlurper()
Map parsedJson = slurper.parseText(currentToken)
def modifiedToken = parsedJson.get("token") + "HELLOWORLD!@#-0923"
badTokenList.add(modifiedToken) //Add the modified token into our list to test


/*
 * Logout - Expired Token
 */

for (def token : badTokenList) {
	def tempToken = '{"token":' + '"' + token + '"' +  '}' //Create our JSON to add into local storage
	WebUI.executeJavaScript('localStorage.setItem("user", arguments[0]);', [tempToken]) //Add the json token into localStorage
	
	WebUI.delay(1)
	
	//Try to navigate into a page that requires login
	WebUI.navigateToUrl('http://localhost:8080/calendar')
	
	WebUI.delay(3)
	
	assert WebUI.getUrl() == 'http://localhost:8080/login' //Navigating to the calendar page should redirect us into the login page because of our bad token
	
	assert WebUI.executeJavaScript('return localStorage.getItem("user");', []) == null //The website should have delete our token when trying to use our bad token

}