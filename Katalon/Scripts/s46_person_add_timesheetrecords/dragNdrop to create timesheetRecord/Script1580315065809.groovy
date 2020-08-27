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

WebUI.comment('Go to Calendar webpage, refresh the page to show task component and calendar component')

WebUI.delay(2)

WebUI.comment('Drag the first task element from task list, drop it to page positon with 300px right, 300px down offset from the task bar element. Wait 5 second for API response and Component update.')

WebUI.delay(2)

//testTimesheetRecord_drop_valid()
WebUI.dragAndDropByOffset(findTestObject('TaskList/All Task items/Page_pmg-ui/li_A'), 300, 200)

WebUI.delay(2)

WebUI.comment('drag the bottom edge of the event element to downside 150px, wait 5 second for API response')

WebUI.delay(5)

//testTimehseetRecord_EndTime_valid()
WebUI.dragAndDropByOffset(findTestObject('Calendar/Calendar table widget/Page_SPFA Project Management/div_A_fc-resizer fc-end-resizer'), 
    0, 150)

WebUI.delay(5)

//testTimesheetRecord_spanMultDays_invalid()
WebUI.dragAndDropByOffset(findTestObject('Calendar/Calendar table widget/Page_SPFA Project Management/div_A_fc-resizer fc-end-resizer_second'), 
    150, 150)

WebUI.comment('refresh the page to see if the record event has been created and remained on the page.')

WebUI.delay(2)

//testTimesheetRecord_save_valid
WebUI.refresh()

WebUI.verifyElementVisible(findTestObject('Calendar/Calendar table widget/Page_SPFA Project Management/a_715 - 900A'))

//testTimesheetRecord_drag_valid
WebUI.dragAndDropByOffset(findTestObject('Calendar/Calendar table widget/Page_SPFA Project Management/a_715 - 900A'), 10, 
    -75)

WebUI.delay(2)

//testTimesheetRecord_update_valid
WebUI.refresh()

//verify timesheetrecord is visible
WebUI.verifyElementVisible(findTestObject('Calendar/Calendar table widget/Page_SPFA Project Management/a_715 - 900A'))

//delay
WebUI.delay(1)

//click on th erecord
WebUI.click(findTestObject('Calendar/Calendar table widget/Page_SPFA Project Management/a_715 - 900A'))

//clcik the delete button on the modal
WebUI.click(findTestObject('Calendar/s49 Test Objects/button_Delete'))

//cleck yes on the pop over
WebUI.click(findTestObject('Calendar/button_Yes'))

//delay
WebUI.delay(2)

//verfy timeshet record not present
WebUI.verifyElementNotPresent(findTestObject('Calendar/Calendar table widget/a_700 - 1030A'), 0)

