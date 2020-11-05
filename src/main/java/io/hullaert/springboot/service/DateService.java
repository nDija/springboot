package io.hullaert.springboot.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface DateService {

    Date getDateTime();

    /**
     *
     * @return Current day such as Monday, Friday,...
     */
    String getCurrentDay();
}
