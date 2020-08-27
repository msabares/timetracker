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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

WebUI.click(findTestObject('Delete Work Assignment/button_NonInstructional Assignment_btn_delete'))

WebUI.verifyElementPresent(findTestObject('Delete Work Assignment/header_Please confirm you want to delete'), 0)

WebUI.verifyElementPresent(findTestObject('Delete Work Assignment/p_Description Elit sed do eiusmod tempor incididunt ut labore sit aspernatur aut odit aut fugit sed quia consequuntur'), 
    0)

WebUI.verifyElementPresent(findTestObject('Delete Work Assignment/p_Name NonInstructional Assignment'), 0)

WebUI.verifyElementPresent(findTestObject('Delete Work Assignment/p_Parent Category No parent category'), 0)

WebUI.click(findTestObject('Delete Work Assignment/button_OK'))

WebUI.verifyTextNotPresent('NonInstructional Assignment', false)

