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

'Verify create person modal appears\r\n'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/modal_createMember'), 0)

'Verify create person modal has a title of Create a Person'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_modalTitle'), 0)

/*
 * Label Elements
 */
'Verify create person modal has a Label for First Name'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_firstName'), 0)

'Verify create person modal has a Label for Last Name'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_lastName'), 0)

'Verify create person modal has a Label for Password'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_password'), 0)

'Verify create person modal has a Label for department'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_Department'), 0)

'Verify create person modal has a Label for current status'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_employmentStatus'), 0)

'Verify create person modal has a Label for Scheduled Hours'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_Scheduled Hours'), 0)

/*
 * Input Elements
 * TODO: Also validate default values?
 */
'Verify create person modal has a Input field for First Name'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/input_firstName'), 0)

'Verify create person modal has a Input field for Last Name'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/input_lastName'), 0)

'Verify create person modal has a Input field for Password'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/input_password'), 0)

'Verify create person modal has a Input field for department'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/select_department'), 0)

'Verify create person modal has a Input field for Employment Status'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/select_employmentStatus'), 0)

'Verify create person modal has a Input field for Scheduled Hours'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/input_scheduledHours'), 0)

/*
 * Button Elements
 */
'Verify create person modal has a cancel button'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/btn_cancel'), 0)

'Verify create person modal has a save button'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/btn_createMember'), 0)

