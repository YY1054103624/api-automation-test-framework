package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class RestaurantHooks {
    @Before("@smoke")
    public void BeforeDisplayMessage(Scenario sc) {
        System.out.println("Before " + sc.getName());
    }

    @After("@smoke")
    public void AfterDisplayMessage(Scenario sc) {
        System.out.println("After " + sc.getName());
    }
}
