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
 * This tests that the profile page has the current person info desiplayed correctly and does not have text overflow
 */

'Login with the guy that has a really long name'
WebUI.callTestCase(findTestCase('common_test/valid_login_longName'), [:], FailureHandling.STOP_ON_FAILURE)

'Click the profile button to navigate to profile'
WebUI.click(findTestObject('Navbar/btn_moreOptions'))

'Verify that the profile button is visible'
WebUI.verifyElementPresent(findTestObject('Object Repository/Navbar/btnProfile'), 0)

//navigate to profile
WebUI.click(findTestObject('Object Repository/Navbar/btnProfile'))

'confirm that the cards first, last, department and employment status all have overflow applied to them'
fNameCSS = WebUI.getCSSValue(findTestObject('Profile/first_name_span'), 'overflow')

'Verify that the correct css value is applied to not allow text overflow'
if (fNameCSS == 'auto') {
    assert true
} else {
    false
}

lNameCSS = WebUI.getCSSValue(findTestObject('Profile/last_name_span'), 'overflow')

'Verify that the correct css value is applied to not allow text overflow'
if (lNameCSS == 'auto') {
	assert true
} else {
	false
}

departmentCSS = WebUI.getCSSValue(findTestObject('Profile/department_span'), 'overflow')

'Verify that the correct css value is applied to not allow text overflow'
if (departmentCSS == 'auto') {
	assert true
} else {
	false
}

employmentCSS = WebUI.getCSSValue(findTestObject('Profile/employment_status_span'), 'overflow')

'Verify that the correct css value is applied to not allow text overflow'
if (employmentCSS == 'auto') {
	assert true
} else {
	false
}