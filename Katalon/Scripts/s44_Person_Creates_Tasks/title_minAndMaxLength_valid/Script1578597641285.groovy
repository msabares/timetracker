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

//	Test Case: Min length of 1 characters. VALID
'Enters \'a\' into the Title Field'
title_value = 'a'
WebUI.delay(1)
WebUI.setText(findTestObject('Create Task/input_title'), title_value)

'Click away to try prompting an error.'
WebUI.click(findTestObject('Create Task/input_estimate'))

WebUI.delay(1)

'Checks to see if error message is NOT present for our MIN test.'
WebUI.verifyElementNotPresent(findTestObject('Create Task/error_title_minLength'), 0)

'Clear Title Field input'
WebUI.setText(findTestObject('Create Task/input_title'), '')

//	Test Case: Min length of 255 characters. VALID
'Modify our testCaseValue so that it\'s 255 characters long.'

title_value = title_value*255
'Enters our modified string into the Title Field.'
WebUI.setText(findTestObject('/Create Task/input_title'), title_value)

'Click away to try prompting an error.'
WebUI.click(findTestObject('Create Task/input_estimate'))

WebUI.delay(1)

'Checks to see if error message is NOT present for our MAX test.'
WebUI.verifyElementNotPresent(findTestObject('Create Task/error_title_minLength'), 0)

String input_result = WebUI.getAttribute(findTestObject('Create Task/input_title'), 'value')

assert input_result.length() == 255 //Ensures the input field limits the number of characters

