import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase

/**
 * Use the following fixture:
 * php bin/console doctrine:fixtures:load --group=timesheetRecordForFilter
 * 
 * NOTE: Reload fixture everytime you're running this suite as some test cases
 * 		 are dependent on others.
 */
@SetUp(skipped = false) // Please change skipped to be false to activate this method.
def setUp() {
	WebUI.callTestCase(findTestCase('common_test/valid_login_BSmith'), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(3)
}

/**
 * Clean test suites environment.
 */
@TearDown(skipped = false) // Please change skipped to be false to activate this method.
def tearDown() {
	//Close browser after suite is finished
	WebUI.closeBrowser();
}

/**
 * Run before each test case starts.
 */
@SetupTestCase(skipped = false) // Please change skipped to be false to activate this method.
def setupTestCase() {
	WebUI.click(findTestObject('Object Repository/Navbar/btn_administration'))
	WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_Work Assignment'))
	'Open Category Modal'
	WebUI.click(findTestObject('Create Category/Create_Edit_Category/button_(Click to clear sorting)_btn btn-outline-dark fas fa-plus fa-xl'))
}

/**
 * Run after each test case ends.
 */
@TearDownTestCase(skipped = false) // Please change skipped to be false to activate this method.
def tearDownTestCase() {
	//Redirect to the main paige
	WebUI.navigateToUrl("http://localhost:8080")
}