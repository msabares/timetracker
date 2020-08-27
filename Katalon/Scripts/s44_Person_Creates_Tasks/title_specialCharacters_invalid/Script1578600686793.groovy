import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'Loop through each valid Title in input_values and check if a validation error is present.'
for (String title : input_values) {
	WebUI.delay(1)
    'Set the input with value from array'
    WebUI.setText(findTestObject('Create Task/input_title'), title)

    'Click away to try prompting the error.'
    WebUI.click(findTestObject('Create Task/input_estimate'))

    'Checks to see if error message is present'
    WebUI.verifyElementPresent(findTestObject('Create Task/error_title_specialChar'), 0)

    WebUI.verifyElementText(findTestObject('Create Task/error_title_specialChar'), 'Title can only contain alphanumeric or punctuation characters.')

    'Clear Title Field input in preparation of next value'
    WebUI.setText(findTestObject('Create Task/input_title'), '')
}

