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
import org.openqa.selenium.Keys as Keys

'Testing that the no connection modal shows up after waiting for timeout response from api'
WebUI.callTestCase(findTestCase('S45_Admin_Creates_Category/common_test_case/common_create_category'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Create Category/btn_createTaskCat'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'wait for timeouw'
WebUI.delay(4)

'verify error modal is shown'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/Category Connection Modal/modal_connectionError'), 
    0)

'verify normal modal not present'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/modal_createCategory'), 0)

'Verify text is present in modal'
WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Connection Modal/error_lbl_connectionError'), 
    'Connection Error')

'verify header od connection modal'
WebUI.verifyElementText(findTestObject('Object Repository/Create Category/Category Connection Modal/error_lbl_connectionModal'), 
    'Error')

'Try and close error modal by any of the disallowed methods'
WebUI.clickOffset(findTestObject('Object Repository/Create Category/Category Connection Modal/btn_noConnection_OK'), 200, 
    0)

WebUI.sendKeys(findTestObject('Object Repository/Create Category/Category Connection Modal/modal_connectionError'), Keys.chord(
        Keys.ESCAPE))


'verify modal is still present after trying to close modal'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Category/Category Connection Modal/modal_connectionError'), 0)



WebUI.click(findTestObject('Object Repository/Create Category/Category Connection Modal/btn_noConnection_OK'))



'verify error modal is gone after presssing ok'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Category/Category Connection Modal/modal_connectionError'), 
    0)

WebUI.closeBrowser()




















