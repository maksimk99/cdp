package com.epam.cdp.maksim.katuranau.module11.service.impl;

import com.epam.cdp.maksim.katuranau.module11.dao.RoleDao;
import com.epam.cdp.maksim.katuranau.module11.model.Role;
import com.epam.cdp.maksim.katuranau.module11.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
