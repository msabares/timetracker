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

'Test that only --no parent Cateory-- shows up if DB is empty'
WebUI.delay(5)
WebUI.callTestCase(findTestCase('S45_Admin_Creates_Category/common_test_case/common_create_category'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.refresh()
WebUI.click(findTestObject('Object Repository/Navbar/btn_administration'))
WebUI.delay(1)

WebUI.click(findTestObject('Create Category/btn_createTaskCat'))
WebUI.delay(1)
WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

WebUI.callTestCase(findTestCase('S45_Admin_Creates_Category/common_test_case/common_test_no_errors'), [:], FailureHandling.STOP_ON_FAILURE)


WebUI.selectOptionByLabel(findTestObject('Object Repository/Create Category/input_parentCategory'), "--No Parent Category--", false)

WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Category/input_parentCategory'), "--No Parent Category--", false,0)




