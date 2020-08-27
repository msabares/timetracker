import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Matt Smith
 * This test verifies the ability to navigate to report 2
 * and its default values
 */
// Member Logs into site
not_run: WebUI.callTestCase(findTestCase('common_test/valid_login_IFlynn'), [:], FailureHandling.STOP_ON_FAILURE)

// Member goes to Home view
not_run: WebUI.waitForElementPresent(findTestObject('Object Repository/Calendar/CalendarComponent'), 0)

not_run: WebUI.navigateToUrl('http://localhost:8080/reports')

// Member can select Workload Breakdown Report
not_run: WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/btn_report_2'))

// Report 2 header is present
WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/header_report_2'), header_text)

// Report 2 Date selector for Start Date
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/date_picker_start'), 0)

// Date selector for end date not present
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/date_picker_end'), 0)

// Message instructing user to select dates is present
WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/header_instruction_banner'), 
    instruction_text)

// Employee labels present
WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/lbl_name'), lbl_name)

WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/lbl_program'), lbl_program)

WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/lbl_status'), lbl_status)

WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/lbl_hours'), lbl_hours)

// Employee information present
WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/txt_name'), txt_name)

WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/txt_program'), txt_program)

WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/txt_status'), txt_status)

WebUI.verifyElementText(findTestObject('Object Repository/Report/Workload Breakdown Report/txt_hours'), txt_hours)

