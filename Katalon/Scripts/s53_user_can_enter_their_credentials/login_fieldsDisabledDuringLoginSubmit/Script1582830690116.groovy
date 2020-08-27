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
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import org.openqa.selenium.WebElement as WebElement

//This test is testing that after submitting any information on login page, everything is disabled, submit button, and both username/password fields
//Test case to navigate to login page as neeeded
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/navigateToLogin'), [:], FailureHandling.STOP_ON_FAILURE)

//Verify everything is not disabled
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/verifyFieldsNotDisabled'), [:], FailureHandling.STOP_ON_FAILURE)

//enter valid info
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/enterValidCredentials'), [:], FailureHandling.STOP_ON_FAILURE)

//Verify everything is disabled

//username
WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Login/input_Username'), 'disabled', 0)

//password
WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Login/input_passwd'), 'disabled', 0)

WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Login/btn_LogIn'), 'disabled', 0)

