import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.value_return as value_return
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
 * Test valid cases of description length
 * Max boundary of 100 characters
 * A normal use case
 * An empty string
 * @author Matt and Nathan
 *
 */
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/input_categoryDescription'), 5)

'Make a string at max boundary in size'
valid_desc_maxLength = ('M' * 255)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_desc_maxLength)

'Click outside of the input field to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryName'))

'Verify no error message'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_description'), 0)

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_desc_valid)

'Click outside of the input field to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryName'))

'Verify no error message'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_description'), 0)

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryDescription'), valid_desc_min)

'Click outside of the input field to activate validator'
WebUI.click(findTestObject('Object Repository/Create Category/input_categoryName'))

'Verify no error message'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_description'), 0)

