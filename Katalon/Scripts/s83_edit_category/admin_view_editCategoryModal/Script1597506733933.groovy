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

WebUI.click(findTestObject('Edit Category/Table/button_(Click to clear sorting)_btn btn-outline-dark fas fa-plus fa-xl'))

'When plus icon is clicked, the header should be "Create Category", and all input fields should be clean'
WebUI.verifyElementPresent(findTestObject('Edit Category/Category_Modal/header_Create Work Assignment'), 0)

WebUI.verifyElementText(findTestObject('Create Category/input_categoryName'), '')

WebUI.verifyElementText(findTestObject('Create Category/input_categoryDescription'), '')

WebUI.selectOptionByLabel(findTestObject('Create Category/input_parentCategory'), '--No Parent Category--', false)

WebUI.click(findTestObject('Create Category/btn_cancelCategory'))

'When edit icon is clicked, the item info shouble be present in each input field'
WebUI.click(findTestObject('Edit Category/Edit Category/button_edit_2'))

WebUI.verifyElementPresent(findTestObject('Edit Category/Category_Modal/header_Edit Existing Work Assignment'), 0)

nameV1 = WebUI.getAttribute(findTestObject('Create Category/input_categoryName'), 'value')

WebUI.verifyEqual(nameV1, 'NonInstructional Assignment')

desV1 = WebUI.getAttribute(findTestObject('Create Category/input_categoryDescription'), 'value')

WebUI.verifyEqual(desV1, 'Elit, sed do eiusmod tempor incididunt ut labore sit aspernatur aut odit aut fugit, sed quia consequuntur.')

WebUI.selectOptionByLabel(findTestObject('Create Category/input_parentCategory'), '--No Parent Category--', false)

WebUI.click(findTestObject('Create Category/btn_cancelCategory'))

'check if the parent category also appeared as expected'
WebUI.click(findTestObject('Edit Category/Edit Category/button_edit_last'))

nameV2 = WebUI.getAttribute(findTestObject('Create Category/input_categoryName'), 'value')

WebUI.verifyEqual(nameV2, 'Projects')

desV2 = WebUI.getAttribute(findTestObject('Create Category/input_categoryDescription'), 'value')

WebUI.verifyEqual(desV2, 'Integer sit amet tortor a ante aliquet volutpat blandit at leo.')

WebUI.verifyElementPresent(findTestObject('Edit Category/Category_Modal/header_Edit Existing Work Assignment'), 0)

WebUI.selectOptionByLabel(findTestObject('Create Category/input_parentCategory'), 'NonInstructional Assignment', false)

WebUI.click(findTestObject('Create Category/btn_cancelCategory'))

WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Member Location'))

'check people category modal showed'
WebUI.click(findTestObject('Edit Category/Table/button_(Click to clear sorting)_btn btn-outline-dark fas fa-plus fa-xl'))

WebUI.verifyElementPresent(findTestObject('Edit Category/Category_Modal/h5_Create Member Location'), 0)

