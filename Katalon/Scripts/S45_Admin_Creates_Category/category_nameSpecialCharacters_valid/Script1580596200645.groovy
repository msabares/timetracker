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

/***
 * Test valid characters used in Category name
 * 'Valid Category Name'
 * '15 Valid Names'
 * 'Spaces are Allowed'
 */
'wait'
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

/**
 * Valid test 1
 */
WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name_1)

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryDescription'))

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameSpecialChar'), 0)

/**
 * Valid test 2
 */
WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name_2)

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryDescription'))

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameSpecialChar'), 0)

/**
 * Valid test 3
 */
WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name_3)

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryDescription'))

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameSpecialChar'), 0)

