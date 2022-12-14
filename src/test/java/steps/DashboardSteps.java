package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("verify all the dashboard tabs")
    public void verify_all_the_dashboard_tabs(DataTable dataTable) {
        // this list is for expected values
        List<String> expectedTabs = dataTable.asList();
        // this list is for actual values
        List<String> actualTabs = new ArrayList<>();
        for(WebElement element: dash.dashboardTabs){
            // storing the string value of a webelement from app-op[]
            actualTabs.add(element.getText());
        }
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }
}
