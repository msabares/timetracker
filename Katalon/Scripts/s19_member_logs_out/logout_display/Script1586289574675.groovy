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
 * Logout - Display Button
 */
//Loop through all the available pages and check if the logout button is visable.
WebUI.delay(1)

for (def url : urls) {
    WebUI.navigateToUrl('http://localhost:8080' + url)

    assert WebUI.getUrl() == ('http://localhost:8080' + url //Ensure we're in the right page we intended.
    )

    WebUI.click(findTestObject('Object Repository/Navbar/btn_moreOptions'))

    WebUI.verifyElementPresent(findTestObject('Object Repository/Navbar/btn_logout'), 0)
}

/*
 * Logout - Display Modal
 */
//Click logout button and confirm the following is present
WebUI.click(findTestObject('Object Repository/Navbar/btn_logout'))

WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/modal_confirmLogout'), 0 //Modal
    )

WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/lbl_title'), 0 //Title - Logout
    )

WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/lbl_message'), 0 //Text - "Are you sure you want to log out?"
    )

WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/btn_logout'), 0 //Buttons -  Logout
    )

WebUI.verifyElementPresent(findTestObject('Object Repository/confirm logout/btn_cancel'), 0 //Buttons - Cancel
    )

