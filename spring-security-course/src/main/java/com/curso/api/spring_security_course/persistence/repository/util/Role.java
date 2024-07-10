package com.curso.api.spring_security_course.persistence.repository.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_ADMINISTRATOR(Arrays.asList(
        RolePermission.READ_ALL_CATEGORY,
        RolePermission.READ_ONE_PRODUCTS,
        RolePermission.CREATE_ONE_PRODUCTS,
        RolePermission.UPDATE_ONE_PRODUCTS,
        RolePermission.DISABLE_ONE_PRODUCTS,

        RolePermission.READ_ALL_CATEGORY,
        RolePermission.READ_ONE_CATEGORY,
        RolePermission.CREATE_ONE_CATEGORY,
        RolePermission.UPDATE_ONE_CATEGORY,
        RolePermission.DISABLE_ONE_CATEGORY,

        RolePermission.READ_MY_PROFILE
    )),

    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_CATEGORY,
            RolePermission.READ_ONE_PRODUCTS,
            RolePermission.UPDATE_ONE_PRODUCTS,

            RolePermission.READ_ALL_CATEGORY,
            RolePermission.READ_ONE_CATEGORY,
            RolePermission.UPDATE_ONE_CATEGORY
    )),

    ROLE_CUSTOMER(Arrays.asList(
            RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
