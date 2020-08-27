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
 * Email Format - Valid Case
 */
'Loop through ValidEmailArray'
for (i = 0; i < ValidEmailArray.size(); i++) {
    'Set Email'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), ValidEmailArray[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_lastName'))

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_notValid'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_required'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_unavailable'), 0)
}

/*
 * Email Format - Invalid Case
 */
'Loop through InvalidEmailArray'
for (i = 0; i < InvalidEmailArray.size(); i++) {
    'Set Email'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), InvalidEmailArray[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_lastName'))

    'Check if error IS present'
    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_email_notValid'), 0)

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_required'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_unavailable'), 0)
}

/*
 * Unqiue Email - Invalid Case
 */
'Set Email to an email that already exist in a data fixutre'
WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), 'bsmith@hotmail.com')

'Click away to promt error'
WebUI.click(findTestObject('Object Repository/Create Member/input_lastName'))

'Check if error IS present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_email_unavailable'), 0)

'Check if error IS NOT present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_notValid'), 0)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_required'), 0)

/*
 * Email Required- Invalid Case
 */
for (i = 0; i < EmptyEmailArray.size(); i++) {
    'Set Email'
    WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), EmptyEmailArray[i])

    'Click away to promt error '
    WebUI.click(findTestObject('Object Repository/Create Member/input_lastName'))

    'Check if error IS present'
    WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_email_required'), 0)

    'Check if error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_unavailable'), 0)

    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_notValid'), 0)
}

/*
 * Email Max Length - Valid Case
 */
RepeatingEmailLength = ((RepeatingEmailLength * 245) + '@yahoo.com' //255 total length
)

'Set Email'
WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), RepeatingEmailLength)

'Click away to promt error '
WebUI.click(findTestObject('Object Repository/Create Member/input_lastName'))

'Check if error IS NOT present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_unavailable'), 0)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_notValid'), 0)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_email_required'), 0)

RepeatingEmailLength = 'M' //Reset the variable

/*
 * Email Max Length - Invalid Case
 */
RepeatingEmailLength = ((RepeatingEmailLength * 246) + '@yahoo.com' //256 total length
)

WebUI.setText(findTestObject('Object Repository/Create Member/input_email'), RepeatingEmailLength)

'Get the value from the input field so we can check its length. '
input_email = WebUI.getAttribute(findTestObject('Object Repository/Create Member/input_email'), 'value')

'Ensure the input field actually trunctates inputs beyond our max length'
assert input_email.length() == 255

