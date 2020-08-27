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
 * Acceptance Tests:
 * hoursSummary_DisplaysTaskHours
 * hoursSummary_DisplaysNoTaskHours
 * 
 */
/**
 * Check if Table Headers and Footers exist except the Total Hours Sum.
 */
'Header - Task'
WebUI.verifyElementVisible(findTestObject('Object Repository/Dashboard/hoursSummary_Component/th_Task NEW'))

'Header - Hours'
WebUI.verifyElementVisible(findTestObject('Object Repository/Dashboard/hoursSummary_Component/th_Hours NEW'))

'Footer - Total'
WebUI.verifyElementVisible(findTestObject('Object Repository/Dashboard/hoursSummary_Component/td_Total'))

/**
 * Check all table records exist for the CURRENT WEEK.
 */
WebUI.verifyElementVisible(findTestObject('Object Repository/Dashboard/hoursSummary_Component/td_thisWeek_Grading'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Dashboard/hoursSummary_Component/td_thisWeek_6_Grading'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_thisWeek_Meetings'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_thisWeek_1_Meetings'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_thisWeek_Prep'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_thisWeek_4_Prep'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_thisWeek_19_Total'))

/**
 * Check all table records exist for the LAST WEEK.
 */
WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_lastWeek_Conference'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_lastWeek_8_Conference'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_lastWeek_Development'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_lastWeek_8_Development'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_lastWeek_16_Total'))

/**
 * Check all table records exist for the 2 WEEKS AGO.
 */
WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Conference'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_8_Conference'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Development'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_2_Development'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Grading'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_2_Grading'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_LongName'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_2_LongName'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Meetings'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_1_Meetings'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Prep'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_2_Prep'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Teaching'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_4_Teaching'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_Vacation'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_9_Vacation'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_2WeeksAgo_30_Total'))

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/td_noTimeEntered'))

