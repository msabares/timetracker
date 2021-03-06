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

//This test will test that a normal Member will not be able to see or click the admin navbar link
WebUI.callTestCase(findTestCase('common_test/valid_login_SFields'), [:], FailureHandling.STOP_ON_FAILURE)

//wait for calendar
WebUI.waitForElementVisible(findTestObject('Object Repository/Calendar/CalendarComponent'), 0)

WebUI.verifyTextNotPresent('Administration', false)

//Verify All the other links are present in navbar
WebUI.verifyElementVisible(findTestObject('Navbar/btn_reports'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Navbar/btn_calendar'))

