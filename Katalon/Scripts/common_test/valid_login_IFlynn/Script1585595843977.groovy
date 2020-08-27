import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

//Change this to be the "correct" route to login page
WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/login')

WebUI.callTestCase(findTestCase('s53_user_can_enter_their_credentials/Common_Tests/deleteToken'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

WebUI.refresh()

//wait for login to appear
WebUI.waitForElementVisible(findTestObject('Object Repository/Login/card_LoginCard'), 1)

//Test that enters valid info to login and submits
//set username text
WebUI.setText(findTestObject('Object Repository/Login/input_Username'), 'IFlynn@hotmail.com')

//set password text, need to escape the $'s
WebUI.setText(findTestObject('Object Repository/Login/input_passwd'), 'Flynn123!')

//Click submit
WebUI.click(findTestObject('Object Repository/Login/btn_LogIn'))

WebUI.delay(4)

if (WebUI.getUrl().contains('login')) {
    WebUI.click(findTestObject('Object Repository/Login/btn_LogIn'))
}

