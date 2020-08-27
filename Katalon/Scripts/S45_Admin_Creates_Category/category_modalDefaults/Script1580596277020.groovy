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

/**
 * Tests default values of modal
 * Tests that information entered into inputs does not persists
 * between opening and closing of modal
 * 
 * @author Matt and Nathan
 *
 */
'Wait for modal to load'
WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/modal_createCategory'), 5)

'Common test case that no errors are present'
WebUI.callTestCase(findTestCase('s45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

'Verify no text is shown for catName'
WebUI.verifyElementText(findTestObject('Object Repository/Create Category/input_categoryDescription'), '', FailureHandling.STOP_ON_FAILURE)

'verify no text shown for catDesc'
WebUI.verifyElementText(findTestObject('Object Repository/Create Category/input_categoryName'), '', FailureHandling.STOP_ON_FAILURE)

'Verify top-level is shown in dropDown'
WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Category/input_parentCategory'), '--No Parent Category--', 
    false, 0)

'Enter some information into the inputs to test no persistence'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), invalid_title)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_description)

WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Category/input_parentCategory'), 3)

'Cancel out of modal'
WebUI.click(findTestObject('Object Repository/Create Category/btn_cancelCategory'))

'Reopen modal'
WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_(Click to clear sorting)_btn btn-outline-dark fas fa-plus fa-xl'))

'Wait for modal to load'
WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/modal_createCategory'), 5)

'Common test case that no errors are present'
WebUI.callTestCase(findTestCase('s45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

