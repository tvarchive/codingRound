# Code Review Comments

1. The page level logic should be separated from test logic for improved maintainibility and reusability. This is achieved using Page Object Model.

1. Methods like `waitFor()` were repeated in every test. Common logic related to pages can be separated in BasePage which will be extended by actual pages.

1. When using Page Factory design pattern always initialize WebElements.

1. Should avoid using hard waits like `Thread.sleep()`

1. For each test separate WebDriver session was created. This launches and quits the browser per test slowing down the execution time. Instead create the WebDriver session only once and perform all tests with the same session.

1. Use TestNG `@BeforeMethod` for launching application before every test method begins. Use TestNG `@AfterSuite` to quit session after all tests are done with execution.