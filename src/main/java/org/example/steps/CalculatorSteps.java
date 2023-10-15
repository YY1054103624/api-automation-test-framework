package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public class CalculatorSteps {
    private Integer firstNumber;
    private Integer secondNumber;
    private Integer actualResult;
    @Given("My calculator has been setup")
    public void my_calculator_has_been_setup() {
        System.out.println("My calculator is setup successfully.");
    }
    @When("User inputs the first number {int}")
    public void user_inputs_the_first_number(Integer firstNumber) {
        Class<CalculatorSteps> clz = CalculatorSteps.class;
        for (Method m: clz.getDeclaredMethods()) {
            System.out.println("method name: " + m.getName());
            for (Parameter p: m.getParameters()) {
                System.out.println("Parameter name: " + p.getName());
            }
        }
        this.firstNumber = firstNumber;
    }
    @When("User inputs the second number {int}")
    public void user_inputs_the_second_number(Integer secondNumber, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map= dataTable.asMaps().get(0);
        System.out.println("年龄是：" + map.get("age"));
        this.secondNumber = secondNumber;
    }
    @When("User clicks on = button")
    public void user_clicks_on_addition_button() {
        this.actualResult = this.firstNumber + this.secondNumber;
    }

    @When("User clicks on * button")
    public void user_clicks_on_multiplication_button() {
        this.actualResult = this.firstNumber * this.secondNumber;
    }

    @Then("Verify user should see then result of {int}")
    public void verify_user_should_see_then_result_of(Integer expectResult) {
        Assert.assertEquals(expectResult, actualResult);
    }
}
