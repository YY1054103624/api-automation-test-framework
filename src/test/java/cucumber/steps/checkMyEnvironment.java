package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class checkMyEnvironment {
    private int monkeysAlreadyHave;
    private int monkeysAdded;

    @Given("I have {int} monkeys")
    public void i_have_monkeys(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        monkeysAlreadyHave = int1;
    }
    @When("I get {int} more monkeys")
    public void i_get_more_monkeys(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        monkeysAdded = int1;
    }
    @Then("I should have {int} monkeys")
    public void i_should_have_monkeys(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("", Optional.ofNullable(int1), monkeysAlreadyHave + monkeysAdded);
    }
}
