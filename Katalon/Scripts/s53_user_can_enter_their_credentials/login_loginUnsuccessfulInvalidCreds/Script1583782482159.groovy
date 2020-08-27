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

//Test case to navigate to login page as neeeded
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/navigateToLogin'), [:], FailureHandling.STOP_ON_FAILURE)

//test fields are not disabled
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/verifyFieldsNotDisabled'), [:], FailureHandling.STOP_ON_FAILURE)

//set username text
WebUI.setText(findTestObject('Object Repository/Login/input_Username'), 'bob@hotmail.com')

//set password text, need to escape the $'s
WebUI.setText(findTestObject('Object Repository/Login/input_passwd'), 'password')

//Click submit
WebUI.click(findTestObject('Object Repository/Login/btn_LogIn'))

//wait for spinner to be gone, or error to be present
//WebUI.waitForElementNotVisible(findTestObject('Object Repository/Login/Spinner'), 0)
//verify error mesage there
WebUI.verifyElementPresent(findTestObject('Object Repository/Login/lbl_error'), 0)

//test to check that we are still at the login page
assert WebUI.getUrl() == 'http://localhost:8080/login'

//set username text
//TODO GRAB THE POPUP WHEN YOU ENTER AN EMPTY USERNAME, CHECK FOR THAT after clicking submit
WebUI.setText(findTestObject('Object Repository/Login/input_Username'), 'bob@bobmail.com')

//set password text, need to escape the $'s
WebUI.setText(findTestObject('Object Repository/Login/input_passwd'), 'Bob1234!')

WebUI.click(findTestObject('Object Repository/Login/btn_LogIn'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Login/lbl_error'), 0)

//test to check that we are still at the login page
assert WebUI.getUrl() == 'http://localhost:8080/login'

