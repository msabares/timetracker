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



WebUI.waitForElementVisible(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/ul_taskList'), 7)

Task1 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_A'))

Task2 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_B'))

Task3 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_C'))

Task4 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_D'))

Task5 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Development'))

Task6 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_E'))

Task7 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_F'))

Task8 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_G'))

Task9 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_H'))

Task10 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Here is another long task name'))

Task11 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_I'))

Task12 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_J'))

Task13 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_K'))

Task14 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_L'))

Task15 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_M'))

Task16 = WebUI.getText(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM'))

Task17 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_N'))

Task18 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_O'))

Task19 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_P'))

Task20 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Plumbing'))

Task21 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Q'))

Task22 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_R'))

Task23 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_S'))

Task24 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_T'))

Task25 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Teaching'))

Task26 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_U'))

Task27 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_V'))

Task28 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_W'))

Task29 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_X'))

Task30 = WebUI.getText(findTestObject('TaskList/All Task items/Page_pmg-ui/li_Y'))

Task31 = WebUI.getText(findTestObject('Object Repository/TaskList/All Task items/Page_pmg-ui/li_Z'))

if (Task1 <= Task2) {
    assert true
} else {
    assert false
}

if (Task1 <= Task2) {
    assert true
} else {
    assert false
}

if (Task2 <= Task3) {
    assert true
} else {
    assert false
}

if (Task3 <= Task4) {
    assert true
} else {
    assert false
}

if (Task4 <= Task5) {
    assert true
} else {
    assert false
}

if (Task5 <= Task6) {
    assert true
} else {
    assert false
}

if (Task6 <= Task7) {
    assert true
} else {
    assert false
}

if (Task7 <= Task8) {
    assert true
} else {
    assert false
}

if (Task8 <= Task9) {
    assert true
} else {
    assert false
}

if (Task9 <= Task10) {
    assert true
} else {
    assert false
}

if (Task10 <= Task11) {
    assert true
} else {
    assert false
}

if (Task11 <= Task12) {
    assert true
} else {
    assert false
}

if (Task12 <= Task13) {
    assert true
} else {
    assert false
}

if (Task13 <= Task14) {
    assert true
} else {
    assert false
}

if (Task14 <= Task15) {
    assert true
} else {
    assert false
}

if (Task15 <= Task16) {
    assert true
} else {
    assert false
}

if (Task16 <= Task17) {
    assert true
} else {
    assert false
}

if (Task17 <= Task18) {
    assert true
} else {
    assert false
}

if (Task18 <= Task19) {
    assert true
} else {
    assert false
}

if (Task19 <= Task20) {
    assert true
} else {
    assert false
}

if (Task20 <= Task21) {
    assert true
} else {
    assert false
}

if (Task21 <= Task22) {
    assert true
} else {
    assert false
}

if (Task22 <= Task23) {
    assert true
} else {
    assert false
}

if (Task23 <= Task24) {
    assert true
} else {
    assert false
}

if (Task24 <= Task25) {
    assert true
} else {
    assert false
}

if (Task25 <= Task26) {
    assert true
} else {
    assert false
}

if (Task26 <= Task27) {
    assert true
} else {
    assert false
}

if (Task27 <= Task28) {
    assert true
} else {
    assert false
}

if (Task28 <= Task29) {
    assert true
} else {
    assert false
}

if (Task29 <= Task30) {
    assert true
} else {
    assert false
}

if (Task31 <= Task31) {
    assert true
} else {
    assert false
}

