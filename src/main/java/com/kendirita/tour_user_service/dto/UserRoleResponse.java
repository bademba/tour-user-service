package com.kendirita.tour_user_service.dto;


import com.kendirita.tour_user_service.entity.UserRole;

public class UserRoleResponse {
    private String id;
    private String role;
    private String email;

    public static UserRoleResponse from(UserRole userRole){
        if (userRole==null || userRole.getRole() == null){
            return null;
        }
        UserRoleResponse dto =new UserRoleResponse();
        dto.role= userRole.getRole().name();
        return dto;
    }

    public String getRole() {
        return role;
    }
}
