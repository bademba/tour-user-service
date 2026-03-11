package com.kendirita.tour_user_service.dto;


import com.kendirita.tour_user_service.entity.UserRole;

public class RoleResponse {
    private String id;
    private String role;

    public static RoleResponse from(UserRole userRole){
        if (userRole==null || userRole.getRole() == null){
            return null;
        }
        RoleResponse dto =new RoleResponse();
        dto.role= userRole.getRole().name();
        return dto;
    }

    public String getRole() {
        return role;
    }
}
