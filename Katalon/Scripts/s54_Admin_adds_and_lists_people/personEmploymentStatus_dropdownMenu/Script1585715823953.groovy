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


'Click Create Person Button'
WebUI.click(findTestObject('Object Repository/Create Member/btn_createMember'))

/*
 * Employment Status Required - Invalid
 */
'Click the Create Member button on the modal to promp the required error.'
WebUI.click(findTestObject('Object Repository/Create Member/btn_create'))

'Check error IS present'
WebUI.verifyElementPresent(findTestObject('Object Repository/Create Member/error_employmentStatus_required'), 0)

/*
 * Employment Status Required - Valid 
 */
for (i = 0; i < currentStatusList.size(); i++) {
    WebUI.selectOptionByIndex(findTestObject('Object Repository/Create Member/select_employmentStatus'), i + 1)

    WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Create Member/select_employmentStatus'), currentStatusList[
        i], false, 0)

    'Check error IS NOT present'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Create Member/error_employmentStatus_required'), 0)
}

