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



'Click the dropdown menu of Category'
WebUI.click(findTestObject('Create Task/input_category'))

'Verify that all of the Category Values are present according to their sorted order in the dropdown options.'
for (i = 0; i < category_values.size(); i++) {
	'Increment the index of the dropdown list'
	WebUI.selectOptionByIndex(findTestObject('Create Task/input_category'), i + 1)

	'Increment the category list to verify that the value of the dropdown matches the current list category'
	WebUI.verifyOptionSelectedByLabel(findTestObject('Create Task/input_category'), category_values[i], false, 0)
}

