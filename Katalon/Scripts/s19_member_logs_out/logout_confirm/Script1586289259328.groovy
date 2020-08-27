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

/*
 * Logout - Confirm
 */
//Click logout button
WebUI.click(findTestObject('Object Repository/Navbar/btn_moreOptions'))

WebUI.click(findTestObject('Object Repository/Navbar/btn_logout'))

//Click the logout button to confirm logut
WebUI.click(findTestObject('Object Repository/confirm logout/btn_logout'))

//Verify the user is logged out by verifying the URL and elements that would be present in the Login page.
assert WebUI.getUrl() == 'http://localhost:8080/login'

//Verify Logout toast message
WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/toast message/toast_logoutMessage'), 0)

WebUI.verifyElementPresent(findTestObject('confirm logout/toast message/toast_title2'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/toast message/toast_message'), 0)

//Verify the token in localstorage was deleted.
assert WebUI.executeJavaScript('return localStorage.getItem("user");', []) == null

