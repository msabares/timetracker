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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.Point as Point
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper

WebUI.comment('S 46: Person add timesheetRecord')

//comment
WebUI.comment('Drag the first task element from task list, drop it to page positon with 300px right, 300px down offset from the task bar element. Wait 5 second for API response and Component update.')

WebUI.delay(2)

//testTimesheetRecord_drop_valid()
WebUI.dragAndDropByOffset(findTestObject('TaskList/All Task items/Page_pmg-ui/li_A'), 305, 225)

WebUI.delay(1)

//verify element is present
WebUI.verifyElementVisible(findTestObject('Calendar/a_x900 - 930A'))

//put delay
WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

//click on the delete button, make it very small to not cnflicting with drag and drop symbole
WebUI.click(findTestObject('Calendar/button_x'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

//click on the ok button on the modal 
WebUI.click(findTestObject('Delete Modal/button_OK'))

WebUI.delay(1)

//verify event is deleted
WebUI.verifyElementNotPresent(findTestObject('Calendar/a_x900 - 930A'), 0)

