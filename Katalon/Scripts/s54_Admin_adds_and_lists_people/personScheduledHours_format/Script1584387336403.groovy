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
 * Scheduled Hours Format - Valid
 */
'Loop through validHours'
for (i = 0; i < validHours.size(); i++) {
    'Set Scheduled Hours'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_scheduledHours'), validHours[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_scheduledHours_between'), 0)
}

/*
 * Scheduled Hours Format - Invalid
 */
'Loop through validHours'
for (i = 0; i < invalidHours.size(); i++) {
    'Set Scheduled Hours'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_scheduledHours'), invalidHours[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

    'Check if error IS present'
    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_scheduledHours_between'), 0)
}

/*
 * Scheduled Hours Format Not Number - Invalid
 */
'Set Scheduled Hours to text (without e)'
WebUI.setText(findTestObject('Object Repository/Create Member/input_scheduledHours'), 'Forty')

'Click away to promt error '
WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

'Check the input field to make sure none of the characters were inputed'
results = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_scheduledHours'), 'value')

assert results == ''

