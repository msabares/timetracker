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

//	Set test case - valid lower boundary
WebUI.delay(1)

'Enter 0 into Hours Estimate Field'
WebUI.setText(findTestObject('Create Task/input_estimate'), input_min)

'Click away to try prompting the error.'
WebUI.click(findTestObject('Create Task/input_title'))

'Check if validation error is not present'
WebUI.verifyElementNotPresent(findTestObject('Create Task/error_estimate'), 0)

'Clear the input'
WebUI.setText(findTestObject('Create Task/input_estimate'), '')

// Set next test case - valid upper boundary
'Enter 1000000 into Hours Estimate Field'
WebUI.setText(findTestObject('Create Task/input_estimate'), input_max)

'Click away to try prompting the error.'
WebUI.click(findTestObject('Create Task/input_title'))

'Check if validation error is not present'
WebUI.verifyElementNotPresent(findTestObject('Create Task/error_estimate'), 0)

