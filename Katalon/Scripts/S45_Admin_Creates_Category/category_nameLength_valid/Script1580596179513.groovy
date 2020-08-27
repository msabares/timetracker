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
 * Test valid lengths for Category Name
 * Max Boundary of 100 characters
 * Normal Use Case
 * Min Boundary of 2 characters
 * @author Matt and Nathan
 */
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'Create string max boundary'
valid_max_length = ('M' * 100)

WebUI.delay(1)

/**
 * First test of 'M' times 100
 */
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_max_length)

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryDescription'))

'Verify no error message'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameLength'), 0)

/**
 * Second test of 'Normal Length Category' times 100
 */
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name_normal)

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryDescription'))

'Verify no error message'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameLength'), 0)

/**
 * Third test of 'AB'
 */
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), valid_name_min)

'Click outside input to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryDescription'))

'Verify no error message'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameLength'), 0)

