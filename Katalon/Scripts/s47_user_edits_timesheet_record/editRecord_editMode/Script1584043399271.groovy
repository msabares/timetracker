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

//Click on a timesheet record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'))

//Click on edit button
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/button_Edit'))

//Verify each element is enabled (doesn't have disabled attribute)
WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), 'disabled', 
    0)

WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_siteRadio'), 'disabled', 
    0)

WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'), 'disabled', 
    0)

WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/input_End Time'), 'disabled', 
    0)

WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), 
    'disabled', 0)

//Verify all tasks are available in picker
for (int i = 0; i < task_list.size(); i++) {
    //WebUI.delay(1)
    WebUI.selectOptionByIndex(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), i)

    WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), 
        task_list[i], false, 0)
}

//Ensure no validation errors are present on load
WebUI.verifyElementNotVisible(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_errorTime'))

//Return to calendar
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/buttonX'))

//Click on same record
WebUI.click(findTestObject('Object Repository/Calendar/s47 Test Objects/a_800 - 1000Teaching'))

//Ensure all elements are back to disabled
WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/textarea_Comments'), 'disabled', 
    0)

WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_siteRadio'), 'disabled', 
    0)

WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/input_StartTime'), 'disabled', 
    0)

WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Calendar/s49 Test Objects/input_End Time'), 'disabled', 
    0)

WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Calendar/s47 Test Objects/editRecord_taskPicker'), 'disabled', 
    0)

//Return to calendar
WebUI.click(findTestObject('Object Repository/Calendar/s49 Test Objects/buttonX'))

