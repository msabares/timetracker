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
 * Ensure that Parent Category displays an alphabetically sorted list
 * of existing Categories
 * @author Matt
 *
 */
WebUI.callTestCase(findTestCase('S45_Admin_Creates_Category/common_test_case/common_create_category'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'Loop through Categories in the input field, and verify that the order matches the alphabetical order of the existing Categories'
for (int i = 0; i < category_item.size(); i++) {
    WebUI.delay(1)

    WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Category/input_parentCategory'), i + 1)

    WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Category/input_parentCategory'), category_item[
        i], false, 0)
}

