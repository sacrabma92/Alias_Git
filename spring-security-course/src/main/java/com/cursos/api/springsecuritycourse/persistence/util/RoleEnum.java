package com.cursos.api.springsecuritycourse.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ADMINISTRATOR(Arrays.asList(
        RolePermissionEnum.READ_ALL_PRODUCTS,
        RolePermissionEnum.READ_ONE_PRODUCTS,
        RolePermissionEnum.CREATE_ONE_PRODUCTS,
        RolePermissionEnum.UPDATE_ONE_PRODUCT,
        RolePermissionEnum.DISABLE_ONE_PRODUCT,

        RolePermissionEnum.READ_ALL_CATEGORY,
        RolePermissionEnum.READ_ONE_CATEGORY,
        RolePermissionEnum.CREATE_ONE_CATEGORY,
        RolePermissionEnum.UPDATE_ONE_CATEGORY,
        RolePermissionEnum.DISABLE_ONE_CATEGORY,

        RolePermissionEnum.READ_MY_PROFILE
    )),
    ASSISTANT_ADMINISTRATOR(Arrays.asList(
            RolePermissionEnum.READ_ALL_PRODUCTS,
            RolePermissionEnum.READ_ONE_PRODUCTS,
            RolePermissionEnum.UPDATE_ONE_PRODUCT,

            RolePermissionEnum.READ_ALL_CATEGORY,
            RolePermissionEnum.READ_ONE_CATEGORY,
            RolePermissionEnum.UPDATE_ONE_CATEGORY,

            RolePermissionEnum.READ_MY_PROFILE
    )),
    CUSTOMER(Arrays.asList(
            RolePermissionEnum.READ_MY_PROFILE
    ));

    private List<RolePermissionEnum> rolePermissionEnum;

    // Constructor de rolePermission
    RoleEnum(List<RolePermissionEnum> permissions){
        this.rolePermissionEnum = permissions;
    }

    // Getters y Setters
    public List<RolePermissionEnum> getRolePermission() {
        return rolePermissionEnum;
    }

    public void setRolePermission(List<RolePermissionEnum> rolePermissionEnum) {
        this.rolePermissionEnum = rolePermissionEnum;
    }
}
