package com.ju.biz.manager.impl;



import com.ju.biz.manager.UserService;
import com.ju.dao.mapper.UserDomainExtMapper;
import com.ju.model.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by tao on 2017/5/28.
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDomainExtMapper userDomainExtMappers;
    public List<UserDomain> listUser() {

        return userDomainExtMappers.listUser();
    }
}
