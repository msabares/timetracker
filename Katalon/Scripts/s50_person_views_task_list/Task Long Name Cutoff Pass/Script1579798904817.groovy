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

WebUI.scrollToElement(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'), 0)

WebUI.check(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'), FailureHandling.STOP_ON_FAILURE)

x = WebUI.getCSSValue(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'), 'text-overflow')

if (x) {
    assert true
} else {
    assert false
}

'check number of characters get back is less than the task title'

'check the titlre of the long tasks name'
WebUI.mouseOver(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'))

String h = WebUI.getAttribute(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'), 
    'title')

if (h == 'Here is another long task name, but in actual words and not just M\'s') {
    assert true
} else {
    assert false
}

