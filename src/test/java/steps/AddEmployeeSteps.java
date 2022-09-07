package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(dash.addEmployeeOption);
    }

    @When("user enters firstName, middleName and lastName")
    public void user_enters_first_name_middle_name_and_last_name() {
        sendText(addEmployee.firstName, "Aladin");
        sendText(addEmployee.middleName, "Abu");
        sendText(addEmployee.lastName, "Jasmin");
    }

    @When("user click on save button")
    public void user_click_on_save_button() {
        click(addEmployee.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        // homework verify added employee
        System.out.println("Employee is added");
    }

    @When("user enters {string}, {string}, {string}")
    public void user_enters(String firstName, String middleName, String lastName) {
        sendText(addEmployee.firstName, firstName);
        sendText(addEmployee.middleName, middleName);
        sendText(addEmployee.lastName, lastName);
    }

    @When("user enters {string}, {string} and {string}")
    public void user_enters_and(String fn, String mn, String ln) {
        sendText(addEmployee.firstName, fn);
        sendText(addEmployee.middleName, mn);
        sendText(addEmployee.lastName, ln);
    }

    @When("user adds multiple employees and verify they are added")
    public void user_adds_multiple_employees_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        // to get the data from feature file in the form of list of maps
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> emp : employeeNames) {
            String firstNameValue = emp.get("firstName");
            String middleNameValue = emp.get("middleName");
            String lastNameValue = emp.get("lastName");

            // it will enter fresh values after each iteration
            sendText(addEmployee.firstName, firstNameValue);
            sendText(addEmployee.middleName, middleNameValue);
            sendText(addEmployee.lastName, lastNameValue);
            click(addEmployee.saveButton);

            // we don't want to execute hooks multiple time
            // we don't want to execute background miltyple time
            // we will click on add employee option to add another employee till
            // the time we are getting fresh values from feature file

            Thread.sleep(2000);
            click(dash.addEmployeeOption);
            Thread.sleep(2000);

            // verify that the employee added

        }
    }
    @When("user adds multiple employees from excel file using {string} sheet")
    public void user_adds_multiple_employees_from_excel_file_using_sheet(String sheetName) throws InterruptedException {
        List<Map<String, String>> newEmployees = ExcelReader.excelListIntoMap(Constants.TEST_DATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while (itr.hasNext()){
            Map<String, String> mapNewEmp = itr.next();

            sendText(addEmployee.firstName, mapNewEmp.get("firstName"));

            sendText(addEmployee.lastName, mapNewEmp.get("lastName"));

            sendText(addEmployee.middleName, mapNewEmp.get("middleName"));

            sendText(addEmployee.photograph, mapNewEmp.get("photograph"));

            if(!addEmployee.checkBox.isSelected()){
                click(addEmployee.checkBox);
            }

            sendText(addEmployee.usernameEmployee, mapNewEmp.get("username"));

            sendText(addEmployee.passwordEmployee, mapNewEmp.get("password"));

            sendText(addEmployee.confirmPasswordEmployee, mapNewEmp.get("confirmPassword"));

            String empIdValue = addEmployee.employeeID.getAttribute("value");
            System.out.println(empIdValue);
            click(addEmployee.saveButton);
            Thread.sleep(4000);

            jsClick(dash.employeeListOption);
            //click(dash.employeeListOption);

            Thread.sleep(2000);
            sendText(emp.idEmployeeSearch, empIdValue);
            click(emp.searchButton);

            List<WebElement> rowData = driver.findElements((By.xpath("//table[@id='resultTable']/tbody/tr")));
            for(int i=0; i<rowData.size(); i++){
                System.out.println("I am inside the loop");
                String rowText= rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") +
                        " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                System.out.println(expectedData);
                Assert.assertEquals(expectedData, rowData);
            }

            Thread.sleep(2000);
            click(dash.addEmployeeOption);
        }
    }
}
