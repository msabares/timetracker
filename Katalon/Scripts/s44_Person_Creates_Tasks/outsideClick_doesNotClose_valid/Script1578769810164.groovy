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

'Ensure the modal opens'
WebUI.verifyElementPresent(findTestObject('Create Task/modal_edit_task'), 0)

'Press the escape key'
WebUI.sendKeys(findTestObject('Create Task/modal_edit_task'), Keys.chord(Keys.ESCAPE))

WebUI.delay(1)

'Ensure the modal is still open from the escape key'
WebUI.verifyElementPresent(findTestObject('Create Task/modal_edit_task'), 1)

'Check if the exit button exist in the modal'
WebUI.verifyElementNotPresent(findTestObject('Create Task/btn_edit_task_close'), 1)

'Click right below the create task button, just outside the modal'
WebUI.clickOffset(findTestObject('Create Task/btn_createTask'), 0, 50)

WebUI.delay(1)

'Ensure the modal is still present when clicking outside of the modal'
WebUI.verifyElementPresent(findTestObject('Create Task/modal_edit_task'), 1)

