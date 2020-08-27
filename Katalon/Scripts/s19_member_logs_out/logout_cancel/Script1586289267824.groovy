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

/*
 * Logout - Cancel
 */
/*
 * Logout - Confirm
 */

def currentPage = WebUI.getUrl()
def currentToken = WebUI.executeJavaScript('return localStorage.getItem("user");', [])

//Click logout button
WebUI.click(findTestObject('Object Repository/Navbar/btn_moreOptions'))
WebUI.click(findTestObject('Object Repository/Navbar/btn_logout'))

//Click the cancel button to not logout
WebUI.click(findTestObject('Object Repository/confirm logout/btn_cancel'))

//Verify the user hasn't logged out my comparing variables we taken before hitting cancel

//Check the page hasn't changed
assert WebUI.getUrl() != 'http://localhost:8080/login'
assert WebUI.getUrl() == currentPage

//Check the token hasn't changed
assert currentToken == WebUI.executeJavaScript('return localStorage.getItem("user");', [])
