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


'Click the dropdown menu of Category'
WebUI.click(findTestObject('Create Task/input_category'))

'Verify the default value is already selected.'
WebUI.verifyOptionSelectedByValue(findTestObject('Create Task/input_category'), '', false, 0)

'click Create Task button to prompt the Category error.'
WebUI.click(findTestObject('Create Task/btn_createTask'))

WebUI.verifyElementPresent(findTestObject('Create Task/error_category'), 0)
WebUI.verifyElementText(findTestObject('Create Task/error_category'), error_message)

//Check to ensure the default value isn't able to be selected.
WebUI.selectOptionByIndex(findTestObject('Create Task/input_category'), 1) //Move to a different select option
WebUI.selectOptionByIndex(findTestObject('Create Task/input_category'), 0) //Try to move back to the default option
WebUI.verifyOptionNotSelectedByLabel(findTestObject('Create Task/input_category'), '--Please select a Category--', false, 0)
