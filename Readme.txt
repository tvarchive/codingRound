1.Chrome driver incompatible with the browser version
2.Webdriver instantiation should be done after setting System property
3.In hotelbooking.java in order to initialize the elements used PageFactory of selenium
4.Modified the xpaths for auto suggestions in Flight and Hotel classes using xpath axes
5.Cleaned up unwanted imports
6.Created DriverManager class which takes care of test setup and tear down which is used across all the tests.
7.Added object repository properties file , class and updated SignIn test with locator value being pulled from repository
8.Implemented encapsulation in Webdriver Manager in getting the driver instance
9.Constructor overloading in Object repository manager