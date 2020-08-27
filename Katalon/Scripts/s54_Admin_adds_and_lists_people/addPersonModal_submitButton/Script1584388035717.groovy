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

/*
 * Submit Member - with all empty fields - Invalid
 */
'Click submit button'
WebUI.click(findTestObject('Object Repository/Create Member/btn_create'))

'Check if error IS present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_modal_msg'), 0)

/*
 * Submit Member - with fields filled with good data, bad data or empty - Invalid
 */
'First Name - Bad Data'
WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), 'B0bby')

'Last Name - Good Data'
WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), 'Bud')

'Click away to remove modal error msg'
WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

'Check if error IS NOT present - before submitting'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_modal_msg'), 0)

'Click submit button with the rest of the fields being empty'
WebUI.click(findTestObject('Object Repository/Create Member/btn_create'))

'Check if error IS present - after submitting'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_modal_msg'), 0)

/*
 * Submit Member - with fields filled with good data - Valid
 */
WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), 'Tom' //First Name
    )

WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), 'Riddle' //Last Name
    )

WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), 'TomRiddle@yahoo.com' //Email
    )

WebUI.setText(findTestObject('Object Repository/Create Member/input_password'), 'Ih8Harry!' //Password
    )

WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Member/select_department'), 1 //Department
    )

WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Member/select_employmentStatus'), 1 //Employment Status
    )

WebUI.setText(findTestObject('Object Repository/Create Member/input_scheduledHours'), '30' //Scheduled Hours
    )

'Click submit button'
WebUI.click(findTestObject('Object Repository/Create Member/btn_create'))

'Check main modal disappear'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/modal_createMember'), 0)

'Check confirm modal appears'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/modal_createMember_confirm'), 0)

'Veryify that tom was added'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/lbl_confirmMsg'), 0)

'Click ok button'
WebUI.click(findTestObject('Object Repository/Create Member/btn_confirmOk'))

'Check confirm modal disappears'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/modal_createMember_confirm'), 0)

'Verify tom is in the table'
WebUI.verifyElementVisible(findTestObject('Object Repository/Create Member/entry_TomRiddle'))

