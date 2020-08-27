import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

//make sure the pickers are not visible to start
WebUI.comment('//make sure the pickers are not visible to start, that previous-week button is not disabled, and current-week button is not present')

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Current-Week'), 
    0)

WebUI.verifyElementNotHasAttribute(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'), 
    'disabled', 0)

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_start_date'), 
    0)

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_end_date'), 
    0)

//Verify that button shows date pickers and disables week changer buttons
WebUI.comment('//Verify that button shows date pickers and disables week changer buttons and replaces self with current-week button')

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Custom-Range'))

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Custom-Range'), 
    0)

WebUI.verifyElementHasAttribute(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'), 
    'disabled', 0)

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_start_date'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_end_date'))

WebUI.verifyElementNotVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/error_message'))

//Select a range that has start date after end date
WebUI.comment('//Select a range that has start date after end date')

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_end_date'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_header_month'))

boolean backEnough = false

String pickerLabel = ''

//Loop clicking previous month until we find the correct month. (determined by the text of the label.)
while (!(backEnough)) {
    //Click the previous month button
    WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_button_previous_month'))

    try {
        //Get the text of the header/label, and put it in the variable
        pickerLabel = WebUI.getText(findTestObject('Object Repository/Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_header_month'))
    }
    catch (Exception e) {
    } 
    finally { 
        //Compare to what we are looking for
        backEnough = (pickerLabel == 'March 2020')
    }
}

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_date4'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/error_message'))

//Verify that returning to current week hides the pickers and the error, and re-enables the week chooser buttons
WebUI.comment('//Verify that returning to current week hides the pickers and the error, and re-enables the week chooser buttons')

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Current-Week'))

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Current-Week'), 
    0)

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_start_date'), 
    0)

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_end_date'), 
    0)

WebUI.verifyElementNotVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/error_message'))

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Custom-Range'))

WebUI.verifyElementNotPresent(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/button_Custom-Range'), 
    0)

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_end_date'))

backEnough = false

pickerLabel = ''

//Loop clicking previous month until we find the correct month. (determined by the text of the label.)
while (!(backEnough)) {
    //Click the previous month button
    WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_button_previous_month'))

    try {
        //Get the text of the header/label, and put it in the variable
        pickerLabel = WebUI.getText(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_header_month'))
    }
    catch (Exception e) {
    } 
    finally { 
        //Compare to what we are looking for
        backEnough = (pickerLabel == 'March 2020')
    }
}

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_date4'))

WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/error_message'))

//Verify that fixing the date fixes the error message
WebUI.comment('//Verify that fixing the date fixes the error message')

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/picker_start_date'))

backEnough = false

pickerLabel = ''

//Loop clicking previous month until we find the correct month. (determined by the text of the label.)
while (!(backEnough)) {
    //Click the previous month button
    WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_button_previous_month'))

    try {
        //Get the text of the header/label, and put it in the variable
        pickerLabel = WebUI.getText(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_header_month'))
    }
    catch (Exception e) {
    } 
    finally { 
        //Compare to what we are looking for
        backEnough = (pickerLabel == 'March 2020')
    }
}

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/pckr_date1'))

WebUI.verifyElementNotVisible(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/error_message'))

