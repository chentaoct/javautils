package com.ju.dao.common.biz.manager;



import com.ju.model.basedomain.domain.UserDomain;

import java.util.List;

/**
 * Created by tao on 2017/5/28.
 */
public interface UserService {
    List<UserDomain> listUser();

}