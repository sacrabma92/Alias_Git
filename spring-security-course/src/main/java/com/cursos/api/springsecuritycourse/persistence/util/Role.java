package com.cursos.api.springsecuritycourse.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_ADMINISTRATOR(Arrays.asList(
        RolePermission.READ_ALL_PRODUCTS,
        RolePermission.READ_ONE_PRODUCTS,
        RolePermission.CREATE_ONE_PRODUCTS,
        RolePermission.UPDATE_ONE_PRODUCT,
        RolePermission.DISABLE_ONE_PRODUCT,

        RolePermission.READ_ALL_PRODUCTS,
        RolePermission.READ_ONE_CATEGORY,
        RolePermission.CREATE_ONE_CATEGORY,
        RolePermission.UPDATE_ONE_PRODUCT,
        RolePermission.DISABLE_ONE_CATEGORY,

        RolePermission.READ_MY_PROFILE
    )),
    ROLE_ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_PRODUCTS,
            RolePermission.UPDATE_ONE_PRODUCT,

            RolePermission.READ_ALL_PRODUCTS,
            RolePermission.READ_ONE_CATEGORY,
            RolePermission.UPDATE_ONE_PRODUCT,

            RolePermission.READ_MY_PROFILE
    )),
    ROLE_CUSTOMER(Arrays.asList(
            RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> rolePermission;

    // Constructor de rolePermission
    Role(List<RolePermission> permissions){
        this.rolePermission = permissions;
    }

    // Getters y Setters
    public List<RolePermission> getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(List<RolePermission> rolePermission) {
        this.rolePermission = rolePermission;
    }
}
