import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Matt Smith
 * This test verifies that, after generating a report, if you leave the report and return to it,
 * the report view is reset
 */
// Member Logs into site
not_run: WebUI.callTestCase(findTestCase('common_test/valid_login_IFlynn'), [:], FailureHandling.STOP_ON_FAILURE)

// Member goes to Home view
not_run: WebUI.waitForElementPresent(findTestObject('Object Repository/Calendar/CalendarComponent'), 0)

not_run: WebUI.navigateToUrl('http://localhost:8080/reports')

// Member can select Workload Breakdown Report
not_run: WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/btn_report_2'))

//Pick valid dates
WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_picker_start'))

WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_apr_01'))

WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_picker_end'))

WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/date_apr_01'))

//Verify report appears
WebUI.verifyElementPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/table_report'), 2)

//Navigate to report 1
WebUI.click(findTestObject('Object Repository/Report/btn_report_1'))

//Return to report 2
WebUI.click(findTestObject('Object Repository/Report/Workload Breakdown Report/btn_report_2'))

//Verify no table
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Report/Workload Breakdown Report/table_report'), 2)

