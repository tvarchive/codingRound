package tests;

import com.google.common.base.Function;
import org.testng.annotations.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExplainFunction {

    @Test
    public void testFunctionInsideFunction() {
        List<Employee> employees = new ArrayList<>();
        Employee e = new Employee();
        // setting Eid;
        Search.searchEquals(e, employees);

        Employee employee1 = Search.searchWhere(new Function<Employee, Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable Employee employee) {
                return employee.getEId() == "123";
            }
        }, employees);

        Employee employee2 = Search.searchWhere(new Function<Employee, Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable Employee employee) {
                return employee.getEId() == "245";
            }
        }, employees);

        Employee employee3 =
                Search.searchWhere(Employees.whereEmployeeIdEquals("567"), employees);
    }

    class Employee {
        private String eId;

        public String getEId() {
            return eId;
        }
    }

    static class Search {
        static <T> T searchEquals(T element, List<T> elements) {
            for (T e : elements) {
                if (e.equals(element))
                    return e;
            }
            return null;
        }

        static <T> T searchWhere(Function<T, Boolean> isTrue, List<T> elements) {
            for (T e : elements) {
                if (isTrue.apply(e))
                    return e;
            }
            return null;
        }
    }

    static class Employees {
        static Function<Employee, Boolean> whereEmployeeIdEquals(final String eId) {
            return new Function<Employee, Boolean>() {
                @Nullable
                @Override
                public Boolean apply(@Nullable Employee employee) {
                    return employee.getEId() == eId;
                }
            };
        }
    }
}
