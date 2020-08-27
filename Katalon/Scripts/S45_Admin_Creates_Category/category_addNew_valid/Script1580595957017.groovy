import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.eclipse.persistence.internal.jpa.parsing.jpql.antlr.JPQLParser.deleteClause_scope as deleteClause_scope
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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/**
 *
 * Test the full process of adding a valid Category
 * Enter valid inputs
 * Save the category
 * Verify values of confirmation modal
 * Verify that new Category is present in the Parent Category drop-down now
 *
 * @author Matt and Nathan
 *
 */
'Wait for modal to load'
WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/modal_createCategory'), 5)

'Enter a valid name'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_description)

'leave parent category default'
WebUI.callTestCase(findTestCase('S45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)

'click save category'
WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Save'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/spinner_loadingCategory'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/spinner_loadingCategory'), 0)

'verify confirmation modal present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/Category Confirmation Modal/modal_categoryConfirm'), 
    5)

'Need delay here'
WebUI.delay(1)

'Verify all confirmation text elements are correct'
WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_categoryCreated'), 
    confirm_header, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmName'), 
    confirm_name, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmDesc'), 
    confirm_description, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmParent'), 
    confirm_category, FailureHandling.STOP_ON_FAILURE)

'Verify main modal is not still present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'Verify cannot close confirmation modal by clicking out side or by pressing esc'
WebUI.sendKeys(findTestObject('Object Repository/Create Category/Category Confirmation Modal/modal_categoryConfirm'), Keys.chord(
        Keys.ESCAPE))

'verify cannot close modal by clikcing outside area'
WebUI.clickOffset(findTestObject('Object Repository/Create Category/Category Confirmation Modal/button_confirmOk'), 50, 
    0)

WebUI.click(findTestObject('Object Repository/Create Category/Category Confirmation Modal/button_confirmOk'))

'Verify confirmation modal is not present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/Category Confirmation Modal/modal_categoryConfirm'), 
    0)

'Going back and checking that the newly created category is in fact present in the parent drop down'

'Click create new category button'
WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_(Click to clear sorting)_btn btn-outline-dark fas fa-plus fa-xl'))

'Wait for modal to load'
WebUI.waitForElementVisible(findTestObject('Object Repository/Create Category/modal_createCategory'), 5)

'click parent dropdown'
WebUI.click(findTestObject('Object Repository/Create Category/input_parentCategory'))

'verify previously added category was added'

'Verfiy New Category is present'
WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Category/input_parentCategory'), 2)

'Verify that new category is present and in proper index.\r\nSee Test Case description for additional notes'
WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Category/input_parentCategory'), valid_name, 
    false, 0)

