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
'Set a valid title'
WebUI.setText(findTestObject('Create Task/input_title'), input_title)

'Set a valid estimate'
WebUI.setText(findTestObject('Create Task/input_estimate'), input_estimate)

'Select a valid category'
WebUI.selectOptionByIndex(findTestObject('Create Task/input_category'), 1)

'Create the task'
WebUI.click(findTestObject('Object Repository/Create Task/btn_createTask'))

'Verify spinner is present while making API call'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/spinner_task_loading'), 0)

//Verify confirmation modal appears.
'Confirm new modal appears'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/modal_confirm_task'), 0)

'Confirm header element is present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/modal_confirm_task_header'), 
    1)

'Confirm that a body element is present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/modal_confirm_task_body'), 0)

'Confirm footer is present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/modal_confirm_task_footer'), 
    0)

'Confirm header text is proper'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/lbl_confirm_title'), 0)

'Confirm OKAY button is present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/btn_ok'), 0)

//Verify the Content
'Confirm proper confirmation text for created task'
WebUI.verifyElementText(findTestObject('Object Repository/Create Task/Confirmation Modal/modal_confirm_task_body'), ('Task ' + 
    input_title) + ' has been created!')

'Click okay to close modal'
WebUI.click(findTestObject('Object Repository/Create Task/Confirmation Modal/btn_ok'))

'Ensure modal is no longer present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Task/Confirmation Modal/modal_confirm_task'), 0)


