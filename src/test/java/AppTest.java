import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Jobs list");
  }

  @Test
  public void jobIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#workPlace").with("Office Depot");
    fill("#title").with("Sales Asssociate");
    fill("#duties").with("selling printers");
    fill("#time").with("6 months");
    submit(".btn");
    assertThat(pageSource()).contains("Your Job has been saved.");
  }

  @Test
  public void taskIsDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#workPlace").with("Office Depot"); // this is the "id" from the form input id in index.vtl
    fill("#title").with("Sales Asssociate");
    fill("#duties").with("selling printers");
    fill("#time").with("6 months");
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("Office Depot");
    assertThat(pageSource()).contains("Sales Asssociate");
    assertThat(pageSource()).contains("selling printers");
    assertThat(pageSource()).contains("6 months");
  }

  // @Test
  // public void multipleTasksAreDisplayedTest() {
  //   goTo("http://localhost:4567/");
  //   fill("#description").with("Mow the lawn");
  //   submit(".btn");
  //   click("a", withText("Go Back"));
  //   fill ("#description").with("Buy groceries");
  //   submit(".btn");
  //   click("a", withText("Go Back"));
  //   assertThat(pageSource()).contains("Mow the lawn");
  //   assertThat(pageSource()).contains("Buy groceries");
  // }

}
