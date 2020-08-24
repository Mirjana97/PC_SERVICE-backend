package projekat.constants;

import static projekat.constants.Roles.CUSTOMER;
import static projekat.constants.Roles.EMPLOYEE;

public class Authorization {
    public static final String HAS_EMPLOYEE_ROLE = "hasAuthority('" + EMPLOYEE + "')";
    public static final String HAS_CUSTOMER_ROLE = "hasAuthority('" + CUSTOMER + "')";
    public static final String HAS_CUSTOMER_OR_EMPLOYEE_ROLE="hasAnyAuthority('"+ CUSTOMER+"','"+ EMPLOYEE+"')";
}
