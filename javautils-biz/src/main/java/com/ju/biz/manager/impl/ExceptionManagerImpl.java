package com.ju.biz.manager.impl;

import com.ju.biz.manager.ExceptionManager;
import com.ju.common.exception.BusinessException;
import org.springframework.stereotype.Service;

/**
 * Created by tao on 2018/5/16.
 */
@Service("exceptionManager")
public class ExceptionManagerImpl implements ExceptionManager {
    @Override
    public String exceptionTest(String name) {
        if(name.equals("1")){
            throw new BusinessException("11","22");
        }
        return "succ";
    }
}
