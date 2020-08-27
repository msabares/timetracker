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

//RUN THIS TEST IN EDGE I SWEAR TO GOD THIS SHOULD WORK, BUT NOOOOOO
//Test case to navigate to login page as neeeded
WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/navigateToLogin'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/verifyFieldsNotDisabled'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('common_test/valid_login_BSmith'), [:], FailureHandling.STOP_ON_FAILURE)

//RUN IN EDGE
//Enter credentials
//WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/enterValidCredentials'), [:], FailureHandling.STOP_ON_FAILURE)
//wait for timesheet page to be present
//WebUI.waitForElementPresent(findTestObject('Object Repository/Calendar/CalendarComponent'), 1)
//verify some of their records 
//wait for login to appear
WebUI.waitForElementVisible(findTestObject('Object Repository/Calendar/CalendarComponent'), 10)

WebUI.verifyTextPresent('Calendar', false)

