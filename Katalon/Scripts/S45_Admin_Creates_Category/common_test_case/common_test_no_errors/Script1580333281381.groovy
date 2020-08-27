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
 * A common error check
 * Ensure no error messages in create Category modal
 * are displayed 
 */


'verify errors not present- name error'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameLength'), 5)
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_nameSpecialChar'), 5)

'verify errors not present - description error'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_category_description'), 5)

'verify main modal error isnt present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/error_createCategory'),5)