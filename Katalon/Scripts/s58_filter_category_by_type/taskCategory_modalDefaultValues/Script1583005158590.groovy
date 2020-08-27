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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/**
 * DONE
 * TEST 01
 * This test opens the category modal after clicking Create Task
 * Verifies that the task category specific info is correct (header, category list)
 * 
 * @author cst232
 *
 */

//Open Task Category modal
WebUI.click(findTestObject('Object Repository/Create Category/btn_createTaskCat'))

WebUI.delay(1)

WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

//Verify header text
WebUI.verifyElementText(findTestObject('Object Repository/Create Category/lbl_catModalHeader'), header_text)

//Cycle through list of Categories
'Loop through Categories in the input field, and verify that the order matches the alphabetical order of the existing Categories'
for (int i = 0; i < valid_categories.size(); i++) {
    WebUI.delay(1)

    WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Category/input_parentCategory'), i + 1)

    //Verify that each category name matches expected variables
    WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Category/input_parentCategory'), valid_categories[
        i], false, 0)
}

