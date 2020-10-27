package io.hullaert.springboot.service;

import java.util.Date;

public interface DateServiceImpl {

    Date getDateTime();

    /**
     *
     * @return Current day such as Monday, Friday,...
     */
    String getCurrentDay();
}
