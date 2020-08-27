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

//Change this to be the "correct" route to login page
WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/login')

//wait for login to appear
WebUI.waitForElementVisible(findTestObject('Object Repository/Login/card_LoginCard'), 1)

WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/deleteToken'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.refresh()

//wait for login to appear
WebUI.waitForElementVisible(findTestObject('Object Repository/Login/card_LoginCard'), 1)

