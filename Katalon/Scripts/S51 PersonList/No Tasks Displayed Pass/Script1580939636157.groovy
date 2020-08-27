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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/**
 *
 * @author Jordan and
 *
 *
*This test checks if no people are displayed and that the error message is displayed
 */
//WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/navigateToLogin'), [:], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/enterValidCredentials'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('common_test/valid_login_BSmith'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.click(findTestObject('Login/btn_LogIn'))

WebUI.delay(5)

WebUI.navigateToUrl('http://localhost:8080/calendar')

WebUI.verifyElementPresent(findTestObject('Calendar/s51 Test Objects/h1_No Records Available'), 5)

WebUI.closeBrowser()

