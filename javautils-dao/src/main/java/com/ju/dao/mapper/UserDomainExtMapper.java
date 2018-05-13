package com.ju.dao.mapper;


import com.ju.model.domain.UserDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDomainExtMapper {

    List<UserDomain> listUser();
}