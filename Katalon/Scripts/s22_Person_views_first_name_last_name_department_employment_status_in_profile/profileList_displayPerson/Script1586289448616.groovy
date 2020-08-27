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
 * Jordan and Ying
 * 
 * This tests that the profile page has the current person info desiplayed
 */

'/login as bob'
WebUI.callTestCase(findTestCase('common_test/valid_login_BSmith'), [:], FailureHandling.STOP_ON_FAILURE)

'check the user name showup on the button'
String username = WebUI.getText(findTestObject('Object Repository/Navbar/btn_moreOptions'))

'confirm bob is logged in'
if (username == 'Bob Smith') {
    assert true
} else {
    assert false
}

'click the button with his name to display profile and logout'
WebUI.click(findTestObject('Navbar/btn_moreOptions'))

'Verify that the profile button is visible'
WebUI.verifyElementPresent(findTestObject('Object Repository/Navbar/btnProfile'), 0)

WebUI.click(findTestObject('Object Repository/Navbar/btnProfile'))

'Verify that the profile button is visible and matches the users first and last name'
WebUI.verifyElementPresent(findTestObject('Profile/department_span'), 0)

'Verify that the first name label is present'
WebUI.verifyElementPresent(findTestObject('Profile/employment_status_span'), 0)

'Verify that the first name input is present'
WebUI.verifyElementPresent(findTestObject('Profile/first_name_span'), 0)

'Verify that the last name label is present'
WebUI.verifyElementPresent(findTestObject('Profile/last_name_span'), 0)

'Verify that the last name input is presentd'
WebUI.verifyElementPresent(findTestObject('Profile/lblDepartment'), 0)

'Verify that the department label is present'
WebUI.verifyElementPresent(findTestObject('Profile/lblEmploymentStatus'), 0)

'Verify that the department input is presentd'
WebUI.verifyElementPresent(findTestObject('Profile/lblFName'), 0)

'Verify that the employment status label is present'
WebUI.verifyElementPresent(findTestObject('Profile/lblLName'), 0)

'Verify that the employment status input is present'
WebUI.verifyElementPresent(findTestObject('Profile/Personal_Information_title'), 0)

WebUI.verifyElementPresent(findTestObject('Profile/profile_card'), 0)

WebUI.verifyElementPresent(findTestObject('Profile/profile_image'), 0)

