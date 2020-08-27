import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

/**
 * The test will check that the hours summary component can go to previous weeks and still display the apprioate information.
 *
 */
//initialize calendar
def calSun = Calendar.instance

def calSat = Calendar.instance

//Get this Sunday
while (calSun.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
    calSun.add(Calendar.DAY_OF_WEEK, -1)
}

//Get this Saturday
while (calSat.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
    calSat.add(Calendar.DAY_OF_WEEK, 1)
}

//Set this Sunday
Date thisSunday = calSun.time

//Get and set this Saturday
Date thisSaturday = calSat.time

//Init title String here so we can access it after the if
String thisWeekTitle

if (thisSunday.month == thisSaturday.month) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('d, yyyy'))
} else if (thisSunday.year == thisSaturday.year) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('MMM d, yyyy'))
} else {
    thisWeekTitle = ((thisSunday.format('MMM d, yyyy') + ' - ') + thisSaturday.format('MMM d, yyyy'))
}

//Verify title text
WebUI.verifyElementText(findTestObject('Dashboard/hoursSummary_Component/h3_currentWeekTitle'), thisWeekTitle)

//Check for next week button disabled
WebUI.verifyElementVisible(findTestObject('Dashboard/hoursSummary_Component/btn_nextWeek disabled'))

not_run: WebUI.verifyElementNotHasAttribute(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_next_week'), 
    'disabled', 0)

//Go to previous week
WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

//Set this Sunday
calSun.add(Calendar.DAY_OF_WEEK, -7)

thisSunday = calSun.time

//Get and set this Saturday
calSat.add(Calendar.DAY_OF_WEEK, -7)

thisSaturday = calSat.time

if (thisSunday.month == thisSaturday.month) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('d, yyyy'))
} else if (thisSunday.year == thisSaturday.year) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('MMM d, yyyy'))
} else {
    thisWeekTitle = ((thisSunday.format('MMM d, yyyy') + ' - ') + thisSaturday.format('MMM d, yyyy'))
}

WebUI.verifyElementText(findTestObject('Dashboard/hoursSummary_Component/h3_currentWeekTitle'), thisWeekTitle)

WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

//Set this Sunday
calSun.add(Calendar.DAY_OF_WEEK, -7)

thisSunday = calSun.time

//Get and set this Saturday
calSat.add(Calendar.DAY_OF_WEEK, -7)

thisSaturday = calSat.time

if (thisSunday.month == thisSaturday.month) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('d, yyyy'))
} else if (thisSunday.year == thisSaturday.year) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('MMM d, yyyy'))
} else {
    thisWeekTitle = ((thisSunday.format('MMM d, yyyy') + ' - ') + thisSaturday.format('MMM d, yyyy'))
}

WebUI.verifyElementText(findTestObject('Dashboard/hoursSummary_Component/h3_currentWeekTitle'), thisWeekTitle)

//Force checking label when the week spans 2 different months
while (thisSunday.month == thisSaturday.month) {
    WebUI.delay(1)

    WebUI.click(findTestObject('Object Repository/Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

    //Set this Sunday
    calSun.add(Calendar.DAY_OF_WEEK, -7)

    thisSunday = calSun.time

    //Get and set this Saturday
    calSat.add(Calendar.DAY_OF_WEEK, -7)

    thisSaturday = calSat.time
}

if (thisSunday.month == thisSaturday.month) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('d, yyyy'))
} else if (thisSunday.year == thisSaturday.year) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('MMM d, yyyy'))
} else {
    thisWeekTitle = ((thisSunday.format('MMM d, yyyy') + ' - ') + thisSaturday.format('MMM d, yyyy'))
}

WebUI.verifyElementText(findTestObject('Dashboard/hoursSummary_Component/h3_currentWeekTitle'), thisWeekTitle)

//Force checking label when the week spans 2 different years
while (thisSunday.year == thisSaturday.year) {
    WebUI.delay(1)

    WebUI.click(findTestObject('Dashboard/hoursSummary_Component/s21_custom_time_frame/btn_prev_week'))

    //Set this Sunday
    calSun.add(Calendar.DAY_OF_WEEK, -7)

    thisSunday = calSun.time

    //Get and set this Saturday
    calSat.add(Calendar.DAY_OF_WEEK, -7)

    thisSaturday = calSat.time
}

if (thisSunday.month == thisSaturday.month) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('d, yyyy'))
} else if (thisSunday.year == thisSaturday.year) {
    thisWeekTitle = ((thisSunday.format('MMM d') + ' - ') + thisSaturday.format('MMM d, yyyy'))
} else {
    thisWeekTitle = ((thisSunday.format('MMM d, yyyy') + ' - ') + thisSaturday.format('MMM d, yyyy'))
}

WebUI.verifyElementText(findTestObject('Dashboard/hoursSummary_Component/h3_currentWeekTitle'), thisWeekTitle)

