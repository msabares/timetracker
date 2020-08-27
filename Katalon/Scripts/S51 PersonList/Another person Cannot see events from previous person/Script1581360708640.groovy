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


//Log in as Sienna
WebUI.callTestCase(findTestCase('common_test/valid_login_SFields'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.delay(3)

WebUI.refresh()

CustomKeywords.'dragNdrop.WebUICustomKeywords.selectPrevWeek'('Jan 12 – 18, 2020')

WebUI.verifyElementPresent(findTestObject('Calendar/a_900 - 100W'), 0)

WebUI.verifyElementPresent(findTestObject('Calendar/a_800 - 900V'), 0)

WebUI.verifyElementNotPresent(findTestObject('Calendar/a_1200Vacation'), 0)

WebUI.verifyElementNotPresent(findTestObject('Calendar/a_Vacation'), 0)

WebUI.closeBrowser()

