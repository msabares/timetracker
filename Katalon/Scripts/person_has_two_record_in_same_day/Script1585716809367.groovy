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
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.openBrowser('')

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/login')

WebUI.rightClick(findTestObject('Object Repository/Report/Page_SPFA Project Management/input_Username_uname'))

WebUI.setText(findTestObject('Object Repository/Report/Page_SPFA Project Management/input_Username_uname'), 'IFlynn@hotmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/Report/Page_SPFA Project Management/input_Password_pwd'), 'Qs5bM2MLPUmuNjSIQxe1SQ==')

WebUI.click(findTestObject('Object Repository/Report/Page_SPFA Project Management/button_Log In'))

WebUI.click(findTestObject('Object Repository/Report/Page_SPFA Project Management/button_Log In'))

WebUI.click(findTestObject('Object Repository/Report/Page_SPFA Project Management/div_Greetings Issac Flynn  A  B  C  Confere_f5c62d'))

WebUI.click(findTestObject('Object Repository/Report/Page_SPFA Project Management/div_800 - 930C800 - 830C'))

WebUI.click(findTestObject('Object Repository/Report/Page_SPFA Project Management/div_Greetings Issac Flynn  A  B  C  Confere_f5c62d_1'))

WebUI.click(findTestObject('Object Repository/Report/Page_SPFA Project Management/div_1000 - 1230Conference800 - 930C1000 - 1_f1bfed'))

WebUI.closeBrowser()

