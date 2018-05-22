package com.ju.biz;

import com.ju.biz.manager.ExceptionManager;
import com.ju.common.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by tao on 2018/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/spring-pts-biz.xml"  })
@TransactionConfiguration(defaultRollback = true)
public class ExceptionManagerJunitTest {
    @Autowired
    private ExceptionManager exceptionManager;
    @Test
    public  void exceptionTest(){
        try{
            String s = exceptionManager.exceptionTest("1");
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }

    }

}
