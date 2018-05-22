package com.ju.web.controller.exception;

import com.ju.biz.manager.ExceptionManager;
import com.ju.common.exception.BusinessException;
import com.ju.model.base.BaseOneRespDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tao on 2018/5/16.
 */
@Controller
@RequestMapping("exceptionTest")
public class ExceptionController {
    private static final Logger logger = Logger.getLogger(ExceptionController.class);
    @Autowired
    private ExceptionManager exceptionManager;

    @RequestMapping("exceptionTest")
    @ResponseBody
    public BaseOneRespDTO<String> exceptionTest(@RequestParam(value = "name",required = false) String name) {
        BaseOneRespDTO<String> res = new BaseOneRespDTO<>();
        try {
            String resStr = exceptionManager.exceptionTest(name);
        } catch (BusinessException e) {
            logger.error(e);
            res.setErrCode(e.getCode());
            res.setErrMsg(e.getMessage());
        } catch (Exception e) {
        }

        return res;
    }
}
