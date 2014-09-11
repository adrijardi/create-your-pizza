package integration

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Integration tests for the main functionality of the application
 * To run this tests the computer must have Firefox(compatible version) installed, the browser will be launched and will navigate the site
 * This is also suitable for CI environments running a headless Firefox using RemoteWebDriver
 */
@ContextConfiguration(locations = "/seleniumTestContext.xml")
class IntegrationTest extends Specification {

    @Autowired
    private URI siteBase;
    @Autowired
    private WebDriver drv;

    def "Homepage loads"() {
        expect:
        homepage()
        drv.pageSource.contains("<title>Create your pizza!</title>")
    }

    def "Can select base"() {
        expect:
        homepage()
        selectFirstBase()
    }

    def "Can select base and then toppings"() {
        expect:
        homepage()
        selectFirstBase()
        selectFirstTopping()
    }

    def "The system gives me a price and I can buy"() {
        expect:
        homepage()
        selectFirstBase()
        selectFirstTopping()
        drv.findElement(By.id("totalPrice"))
        drv.findElement(By.id("buyNow")).submit()
        drv.pageSource.contains("<title>Create your pizza!</title>")
    }

    def "Toppings cannot be selected before the base"() {

    }

    private homepage() {
        drv.get(siteBase.toString() + "/")
        true
    }

    private selectFirstBase() {
        def dropdown = new Select(drv.findElement(By.id("pizza-base-select")))
        dropdown.getOptions().get(1).click();
        true
    }

    private selectFirstTopping() {
        drv.findElement(By.id("toppingLabel1")).click()
        true
    }
}
