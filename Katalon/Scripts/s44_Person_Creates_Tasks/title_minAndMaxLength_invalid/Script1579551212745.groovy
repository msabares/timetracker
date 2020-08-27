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


//	Test Case: Min length of 0 characters using ' '. INVALID because input trims it. 
'Enters \' \' into the Title Field (Already set)'
WebUI.delay(1)

WebUI.setText(findTestObject('Create Task/input_title'), title_value)

'Click away to prompt the error.'
WebUI.click(findTestObject('Create Task/input_estimate'))


'Checks to see if error message is IS present for our MIN test.'
WebUI.verifyElementPresent(findTestObject('Create Task/error_title_minLength'), 0)

WebUI.verifyElementText(findTestObject('Create Task/error_title_minLength'), title_error)

'Check to see if resulting character length is 1'
input_result = WebUI.getAttribute(findTestObject('Create Task/input_title'), 'value')

assert input_result.length() == 1

//	Test Case: Min length of 0 characters using ''. INVALID
'Enters \'\' into the Title Field'
title_value = ''

WebUI.setText(findTestObject('Create Task/input_title'), title_value)

'Click away to prompt the error.'
WebUI.click(findTestObject('Create Task/input_estimate'))


'Checks to see if error message is IS present for our MIN test.'
WebUI.verifyElementPresent(findTestObject('Create Task/error_title_minLength'), 0)

WebUI.verifyElementText(findTestObject('Create Task/error_title_minLength'), title_error)

'Check to see if the resulting character length is 0'
input_result = WebUI.getAttribute(findTestObject('Create Task/input_title'), 'value')

assert input_result.length() == 0

//	Test Case: Min length of 256 characters. INVALID
'Modify our testCaseValue so that it\'s 255 characters long.'

title_value = 'a'*256

'Enters our modified string into the Title Field.'
//WebUI.setText(findTestObject('Create Task/input_title')

WebUI.setText(findTestObject('/Create Task/input_title'), title_value)


'Click away to prompt the error.'
WebUI.click(findTestObject('Create Task/input_estimate'))

WebUI.delay(1)

'Checks to see if error message is IS present for our MAX test as the browser will limit its input.'
WebUI.verifyElementNotPresent(findTestObject('Create Task/error_title_minLength'), 0)

'Ensures the input field limits the number of characters'
input_result = WebUI.getAttribute(findTestObject('Create Task/input_title'), 'value')

assert input_result.length() == 255

