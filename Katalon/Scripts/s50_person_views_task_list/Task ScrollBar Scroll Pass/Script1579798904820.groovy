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


'use pre-existing browser'
WebUI.waitForElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/ul_taskList'), 0)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_A'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_B'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_C'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_D'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Development'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_E'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_F'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_G'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_H'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_I'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_J'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_K'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_L'), 5)

WebUI.verifyElementPresent(findTestObject('TaskList/All Task items/Page_pmg-ui/li_M'), 5)

WebUI.scrollToElement(findTestObject('TaskList/All Task items/Page_pmg-ui/li_N'), -200)

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_N'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_O'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_P'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Plumbing'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Q'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_R'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_S'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_T'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Teaching'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_U'))

WebUI.verifyElementVisible(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_V'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_W'))

WebUI.scrollToElement(findTestObject('TaskList/All Task items/Page_pmg-ui/li_R'), 5)

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_X'))

WebUI.verifyElementVisible(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Y'))

WebUI.verifyElementVisible(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_Z'))

