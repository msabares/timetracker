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

'Click Create Person Button'
WebUI.click(findTestObject('Object Repository/Create Member/btn_createMember'))


/* fill modal with valid data*/
WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), 'Tom') //First Name
WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), 'Riddle') //Last Name
WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), 'TomRiddle@yahoo.com') //Email
WebUI.setText(findTestObject('Object Repository/Create Member/input_password'), 'Ih8Harry!') //Password
WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Member/select_department'), 1) //Department
WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Member/select_employmentStatus'), 1) //Employment Status
WebUI.setText(findTestObject('Object Repository/Create Member/input_scheduledHours'), '30') //Scheduled Hours

'cancel and close modal'
WebUI.click(findTestObject('Object Repository/Create Member/btn_cancel'))

'Original modal dissapears'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/modal_createMember'), 0)

're-open modal'
WebUI.click(findTestObject('Object Repository/Create Member/btn_createMember'))


'add person modal appears'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/modal_createMember'), 0)

'Valid Email Address'
input_Email = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_email'), 'value')
assert input_Email == "" //or null?

'Valid First Name'
input_fName = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_firstName'), 'value')
assert input_fName == "" //or null?

'Valid Last Name'
input_lName = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_lastName'), 'value')
assert input_lName == "" //or null?

'Valid Password'
input_password = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_password'), 'value')
assert input_password == "" //or null?

'Valid Scheduled Hours'
input_scheduledHours = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_scheduledHours'), 'value')
assert input_scheduledHours == "0" //or null?

'Valid Department'
input_department = WebUI.getAttribute(findTestObject('Object Repository/Create Member/lbl_Department'), 'value')
assert input_department == null //or null?

'Valid EMployment Status'
input_status = WebUI.getAttribute(findTestObject('Object Repository/Create Member/lbl_employmentStatus'), 'value')
assert input_status == null //or null?

/*
 *The Way that these errors work is that there is a single div to represent the current error message.
 *When the modal first loads That div has the id of the require errors of that input field, but will dynamically changed.
 *Because of this, we'll have to use verifyElementNotVisible for require and verifyElementNotPresent for the rest of the errors
 */

WebUI.verifyElementNotVisible(findTestObject('Object Repository/Create Member/error_firstName_required'))
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Create Member/error_lastName_required'))
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Create Member/error_email_required'))
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Create Member/error_password_require'))
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Create Member/error_department_require'))
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Create Member/error_employmentStatus_required'))

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_specialChar'), 0)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_specialChar'), 0)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_unavailable'), 0)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_notValid'), 0)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_password_notValid'), 0)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_modal_msg'), 0)




