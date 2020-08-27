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
 * 
 * TEST 02
 * DONE
 * Test that you can create a Task Category without setting a parent category
 * @author cst232
 *
 */

//Open Task Category modal
WebUI.click(findTestObject('Object Repository/Create Category/btn_createTaskCat'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/input_categoryName'), 1)

WebUI.delay(1)

//Fill in inputs
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name)

//Leave parentCategory empty
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_desc)

//Save Category
WebUI.click(findTestObject('Object Repository/Create Category/btn_saveCategory'))

//Verify text in confirmation modal
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/Category Confirmation Modal/modal_categoryConfirm'), 
    0)

WebUI.delay(1)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_categoryCreated'), 
    conf_header)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmMessage'), 
    conf_message)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmName'), 
    conf_name)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmDesc'), 
    conf_desc)

WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Confirmation Modal/lbl_confirmParent'), 
    conf_parent)

WebUI.click(findTestObject('Object Repository/Create Category/Category Confirmation Modal/button_confirmOk'))

