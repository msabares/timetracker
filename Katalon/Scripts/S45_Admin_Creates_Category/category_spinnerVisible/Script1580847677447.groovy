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
 *
 * This test verifies the use case of the loading spinner
 * Test that the spinner appears during modal load
 * Test that clicking outside of the modal during load, does not affect the load
 *
 * @author Matt and Nathan
 *
 */
'Ensure Spinner is present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/spinner_loadingCategory'), 0)

'Click outside of the modal'
WebUI.clickOffset(findTestObject('Object Repository/Create Category/modal_createCategory'), 0, 200)

'Ensure Spinner is still present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/spinner_loadingCategory'), 0)

'Enter a valid Category name'
WebUI.setText(findTestObject('Object Repository/Create Category/input_categoryName'), 'Spinner Test')

'Save valid Category'
WebUI.click(findTestObject('Object Repository/Create Category/btn_saveCategory'))

'Ensure Spinner is present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/spinner_loadingCategory'), 0)

'Click outside of the modal'
WebUI.clickOffset(findTestObject('Object Repository/Create Category/modal_createCategory'), 0, 200)

WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/Category Confirmation Modal/modal_categoryConfirm'), 
    0)

