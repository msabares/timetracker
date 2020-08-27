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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/**
 * DONE
 * TEST 07
 * Verify that 'Edit Task Category' modal
 * only displays Task Categories in the 'Category' picker
 * 
 * Loop through data from fixtures, and data added from tests
 * and compare with list
 * @author cst232
 *
 */

WebUI.click(findTestObject('TaskList/button_Create Task'))

for (int i = 0; i < valid_categories.size(); i++) {
    WebUI.delay(1)

    WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Task/input_category'), i + 1)

    WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Task/input_category'), valid_categories[i], 
        false, 0)
}


