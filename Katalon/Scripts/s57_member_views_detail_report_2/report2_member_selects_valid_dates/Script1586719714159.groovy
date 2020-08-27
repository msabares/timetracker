import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Matt Smith
 * This test verifies the ability to select valid dates
 * Verifies the appearing of report table and summary table
 * Verifies as much content as it can of said tables
 */
// Member logs in
not_run: WebUI.callTestCase(findTestCase('common_test/valid_login_IFlynn'), [:], FailureHandling.STOP_ON_FAILURE)

// Member navigates to Home view
not_run: WebUI.navigateToUrl('http://localhost:8080/reports')

// Member Selects Dashboard 2
not_run: WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/btn_report_2'))

// Member selects a start date
WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_picker_start'))

while (!(WebUI.getText(findTestObject('Object Repository/Report/Workload Breakdown Report/calendar_header')).equals('January 2020'))) {
    WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/btn_previous_month'))
}

WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_jan_01'))

// Verify instruction not visible
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/txt_select_start'), 2)

// Member selects an end date after start date
WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_picker_end'))
while (!(WebUI.getText(findTestObject('Object Repository/Report/Workload Breakdown Report/calendar_header')).equals('April 2020'))) {
	WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/btn_previous_month'))
}

WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_apr_01'))

// Verify instruction not visible
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/header_instruction_banner'), 
    2)

// Verify report exists
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/table_report'), 2)

//Verify report contents
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/th_work_assignment'), 
    2)

//Verify main report headers and footers
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/th_work_assignment'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/th_work_task'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/th_hours_worked'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/ftr_total_hours'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/ftr_data_hours'), 
    2)

//Verify main report data
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_student_contact'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_comp_func'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_non_assign'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_task_teaching'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_task_vacation'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_task_meetings'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_task_conference'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Report Data/data_task_grading'), 
    2)

// Verify summary exists
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/table_summary'), 2)

// Verify summary caption
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Summary Data/caption_summary'), 
    2)

//Verify summary data
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Summary Data/hdr_data'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Summary Data/data_est'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Summary Data/data_total'), 
    2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/Summary Data/data_variance'), 
    2)

