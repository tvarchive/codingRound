package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import utils.DriverManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class DynamicTestsFactory {

    @TestFactory
    Collection<DynamicTest> getTests() {
        Set<DynamicTest> tests = new HashSet<>();

        Reflections reflections = new Reflections("tests");
        Set<Class<?>> testClasses = reflections.getTypesAnnotatedWith(DriverTest.class);
        for (Class<?> testClass: testClasses) {
            Set<Method> driverTests = findDriverTests(testClass);
            for (Method test:  driverTests) {
                tests.add(DynamicTest.dynamicTest(test.getName(), new Executable() {

                    @Override
                    public void execute() throws Throwable {
                        final Object newTestClassObject = testClass.newInstance();
                        Method beforeEach = findBeforeEach(testClass);
                        if (beforeEach != null) {
                            beforeEach.invoke(newTestClassObject);
                        }
                        Parameter[] parameters = test.getParameters();
                        WebDriver[] webDrivers = new WebDriver[parameters.length];
                        for (int i = 0; i < parameters.length; i++) {
                            Parameter parameter = parameters[i];
                            if (parameter.getParameterizedType() != WebDriver.class) {
                                throw new RuntimeException("Unknown parameter");
                            }
                            webDrivers[i] = DriverManager.getDriver();
                        }
                        test.invoke(newTestClassObject, webDrivers);
                        for (WebDriver webdriver : webDrivers) {
                            DriverManager.returnDriver(webdriver);
                        }
                    }
                }));
            }

        }
        return tests;
    }

    Method findBeforeEach(Class<?> testClass) {
        for (Method method: testClass.getMethods()) {
            for(Annotation annotation :  method.getDeclaredAnnotations()) {
                if (annotation.annotationType() == BeforeEach.class) {
                    return method;
                }
            }
        }
        return null;
    }

    Set<Method> findDriverTests(Class<?> testClass) {
        Set<Method> driverTestMethods = new HashSet<>();
        for (Method method : testClass.getMethods()) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation.annotationType() == DriverTest.class) {
                    driverTestMethods.add(method);
                }
            }
        }
        return driverTestMethods;
    }
 }
