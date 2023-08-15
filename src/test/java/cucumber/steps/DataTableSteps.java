package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTableSteps {
    @Given("I placed an order for the following items")
    public void i_placed_an_order_for_the_following_items(io.cucumber.datatable.DataTable dataTable) {
        // put each item into a list from row 1
//        List<String> BillData = dataTable.row(0);

//        List<List<String>> billData = dataTable.asLists();
//        List<String> firstRow = billData.get(1);
//        for (String item: firstRow) {
//            System.out.println(item);
//        }

        List<Map<String, String>> billData = dataTable.asMaps();

        for(Map<String, String> billItems: billData) {
            for(Map.Entry<String, String> billItem: billItems.entrySet()) {
                System.out.println("Key: " + billItem.getKey() + ", Value: " + billItem.getValue());
            }
        }
    }
    @When("I generate the bill")
    public void i_generate_the_bill() {
    }
    @Then("a bill for ${int} should be generated")
    public void a_bill_for_$_should_be_generated(Integer int1) {
    }
}
