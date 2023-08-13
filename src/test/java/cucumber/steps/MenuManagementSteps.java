package cucumber.steps;

import cucumber.file.RestaurantMenu;
import cucumber.file.RestaurantMenuItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class MenuManagementSteps {
    private RestaurantMenuItem newMenuItem;

    @Given("I have a menu item with name {string} and price {int}")
    public void i_have_a_menu_item_with_name_and_price(String itemName, Integer price) {
        newMenuItem = new RestaurantMenuItem(itemName, price);
        System.out.println("Step 1");
    }

    @When("I add that menu item")
    public void i_add_that_menu_item() {
        RestaurantMenu.menu.add(newMenuItem);
        System.out.println("Step 2");
    }

    @Then("Menu Item with name {string} should be added")
    public void menu_item_with_name_should_be_added(String string) {
        assertTrue(RestaurantMenu.menu.contains(newMenuItem));
        System.out.println("Step 3");
    }
}
