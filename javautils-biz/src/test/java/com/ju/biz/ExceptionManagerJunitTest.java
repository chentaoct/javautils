package com.ju.biz;

import com.ju.biz.manager.ExceptionManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by tao on 2018/5/16.
 */
@ContextConfiguration(locations = { "classpath*:/spring/spring-pts-biz.xml"  })
@TransactionConfiguration(defaultRollback = true)
public class ExceptionManagerJunitTest {
    @Autowired
    private ExceptionManager exceptionManager;
    @Test
    public  void exceptionTest(){
        String s = exceptionManager.exceptionTest("1");
    }

}
