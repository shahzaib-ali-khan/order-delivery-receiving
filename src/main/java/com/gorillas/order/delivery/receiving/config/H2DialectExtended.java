package com.gorillas.order.delivery.receiving.config;

import org.hibernate.dialect.H2Dialect;

/**
 * @author Shahzaib Ali Khan
 * @since 22-01-2022
 * Class created for resolving boolean and int comparison issue in Hibernate
 */

public class H2DialectExtended extends H2Dialect {

    @Override
    public String toBooleanValueString(boolean bool) {
        return bool ? "TRUE" : "FALSE";
    }

}