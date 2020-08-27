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


//Fill in Create Task modal.
//We'll attempt to create task 3 times, each time with one of the field being invalid.
//	Invalid Category (The default value is already invalid.)
'Set a valid title'
WebUI.setText(findTestObject('Create Task/input_title'), input_title_valid)
'Set a valid estimate'
WebUI.setText(findTestObject('Create Task/input_estimate'), input_estimate_valid)

//Check for error
'Create the task'
WebUI.click(findTestObject('Create Task/btn_createTask'))

'Checking if the modal error is present and displaying the correct content.'
WebUI.verifyElementPresent(findTestObject('Create Task/error_createEditTask'), 0)
WebUI.verifyElementText(findTestObject('Create Task/error_createEditTask'), error_message)

// Invalid Hours Estimate
'Set a invalid estimate'
WebUI.setText(findTestObject('Create Task/input_estimate'), input_estimate_invalid)
'Set a valid category'
WebUI.selectOptionByIndex(findTestObject('Create Task/input_category'), 0)

//Check for error
'Create the task'
WebUI.click(findTestObject('Object Repository/Create Task/btn_createTask'))

'Checking if the modal error is present and displaying the correct content.'
WebUI.verifyElementPresent(findTestObject('Create Task/error_createEditTask'), 0)
WebUI.verifyElementText(findTestObject('Create Task/error_createEditTask'), error_message)

// Invalid Title Estimate
'Set a invalid estimate'
WebUI.setText(findTestObject('Create Task/input_title'), input_title_invalid)

'Set a valid estimate'
WebUI.setText(findTestObject('Create Task/input_estimate'), input_estimate_valid)

//Check for error
'Create the task'
WebUI.click(findTestObject('Object Repository/Create Task/btn_createTask'))

'Checking if the modal error is present and displaying the correct content.'
WebUI.verifyElementPresent(findTestObject('Create Task/error_createEditTask'), 0)
WebUI.verifyElementText(findTestObject('Create Task/error_createEditTask'), error_message)

