package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class getIssues {
    @Given("I am an anonymous user")
    public void i_am_an_anonymous_user() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I request a list of issues for the Symfony repository from user Symfony")
    public void i_request_a_list_of_issues_for_the_symfony_repository_from_user_symfony() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should find at least {int} result")
    public void i_should_find_at_least_result(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
