import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * At this test we are trying to enter invalid date range and test if we have message box that
 * notify user the start date can not be bigger than the end date.
 */

//open end picker
WebUI.verifyElementPresent(findTestObject('Report/open_end_calendar'), 0)
WebUI.click(findTestObject('Report/open_end_calendar'))


//click on previous button on the date picker to go to the March month
WebUI.click(findTestObject('Report/button_previous'))

//select March 23
WebUI.click(findTestObject('Report/March_23'))

//click on the start picker
WebUI.verifyElementPresent(findTestObject('Report/open_start_calendar'), 0)
WebUI.click(findTestObject('Report/open_start_calendar'))

//click on previous button
WebUI.click(findTestObject('Report/button_previous'))

//select march 26
WebUI.click(findTestObject('Report/March_26'))

//Message appears athat start date cannot be bigger than end date
WebUI.verifyElementPresent(findTestObject('Report/error_modal'), 0)

//click on x button on the message box
WebUI.click(findTestObject('Report/button_x'))

//verify modal box not there anymore
WebUI.verifyElementNotPresent(findTestObject('Report/error_modal'), 0)

