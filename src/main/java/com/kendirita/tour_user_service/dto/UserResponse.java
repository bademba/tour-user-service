package com.kendirita.tour_user_service.dto;



import com.kendirita.tour_user_service.entity.User;

import java.util.Date;

public class UserResponse {

    private String id;
    private String email;
    private String fullName;
    private Date createdAt;
    private Date updatedAt;
    private UserProfileResponse profile;
    private UserRoleResponse userRole;

    public static UserResponse from(User user) {

        UserResponse dto = new UserResponse();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.fullName = user.getFullName();
        dto.createdAt = user.getCreatedAt();
        dto.updatedAt = user.getUpdatedAt();
        dto.profile = UserProfileResponse.from(user.getProfile());
        dto.userRole = UserRoleResponse.from(user.getUserRole());

        return dto;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public UserProfileResponse getProfile() {
        return profile;
    }

    public UserRoleResponse getUserRole() {
        return userRole;
    }

}