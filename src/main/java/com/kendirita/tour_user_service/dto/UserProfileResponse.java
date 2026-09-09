package com.kendirita.tour_user_service.dto;


import com.kendirita.tour_user_service.entity.Profile;
import java.util.Date;

public class UserProfileResponse {

    private String id;
    private String phone;
    private String avatarUrl;
    private Date createdAt;
    private String email;
    private Date updatedAt;

    public static UserProfileResponse from(Profile profile) {
        if (profile == null) return null;

        UserProfileResponse dto = new UserProfileResponse();
        dto.id = profile.getId();
        dto.phone = profile.getPhone();
        dto.avatarUrl = profile.getAvatarUrl();
        dto.email= profile.getEmail();
        dto.createdAt = profile.getCreatedAt();
        dto.updatedAt= profile.getUpdatedAt();
        return dto;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}