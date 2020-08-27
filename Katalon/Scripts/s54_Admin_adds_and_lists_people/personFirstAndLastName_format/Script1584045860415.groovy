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
 * Name Format - Valid Case
 */
'Loop through validFirstAndLastNameArray'
for (i = 0; i < validFirstAndLastNameArray.size(); i++) {
    'Set First and Last Name'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), validFirstAndLastNameArray[i])

    WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), validFirstAndLastNameArray[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_required'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_specialChar'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_required'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_specialChar'), 0)
}

/*
 * Name Format - Invalid Case
 */
'Loop through invalidFirstAndLastNameArray'
for (i = 0; i < invalidFirstAndLastNameArray.size(); i++) {
    'Set First and Last Name'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), invalidFirstAndLastNameArray[i])

    WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), invalidFirstAndLastNameArray[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

    'Check if error IS present'
    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_firstName_specialChar'), 0)

    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_lastName_specialChar'), 0)

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_required'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_required'), 0)
}

/*
 * Name Requied - Invalid Case
 */
'Loop through emptyFirstAndLastNameArray'
for (i = 0; i < emptyFirstAndLastNameArray.size(); i++) {
    'Set First and Last Name'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), emptyFirstAndLastNameArray[i])

    WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), emptyFirstAndLastNameArray[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_email'))

    'Check if error IS present'
    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_firstName_required'), 0)

    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_lastName_required'), 0)

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_specialChar'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_specialChar'), 0)
}

/*
 * Name Max Length - Invalid Case
 */
RepeatingName = (RepeatingName * 101)

'Set First and Last Name'
WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), RepeatingName)

WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), RepeatingName)

'Get the value from the input field so we can check its length. '
input_fName = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_firstName'), 'value')

input_lName = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_lastName'), 'value')

'Ensure the input field actually trunctates inputs beyond our max length'
assert (input_fName.length() == 100) && (input_lName.length() == 100)

RepeatingName = 'M' //Reset the variable

/*
 * Name Max Length - Valid Case
 */
RepeatingName = (RepeatingName * 100)

'Set First and Last Name'
WebUI.setText(findTestObject('Object Repository/Create Member/input_firstName'), RepeatingName)

WebUI.setText(findTestObject('Object Repository/Create Member/input_lastName'), RepeatingName)

'Get the value from the input field so we can check its length. '
input_fName = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_firstName'), 'value')

input_lName = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_lastName'), 'value')

'Check if error IS NOT present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_required'), 0)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_firstName_specialChar'), 0)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_required'), 0)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_lastName_specialChar'), 0)

