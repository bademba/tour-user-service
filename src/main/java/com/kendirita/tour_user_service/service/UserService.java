package com.kendirita.tour_user_service.service;

import com.kendirita.tour_user_service.config.WebClientConfig;
import com.kendirita.tour_user_service.dto.ApiResponse;
import com.kendirita.tour_user_service.dto.UserProfileResponse;
import com.kendirita.tour_user_service.dto.UserResponse;
import com.kendirita.tour_user_service.dto.UserRoleResponse;
import com.kendirita.tour_user_service.entity.Profile;
import com.kendirita.tour_user_service.entity.User;
import com.kendirita.tour_user_service.entity.UserRole;
import com.kendirita.tour_user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebClient webClient;

    @Value("${services.user-roles.url}")
    private String userRolesServiceUrl;

    @Value("${services.user-profile.url}")
    private String userProfileServiceUrl;

    //create new user
    @Transactional
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("User with this email already exists");
        }

        Profile profile = user.getProfile();

        if (profile != null) {
            profile.setUser(user);
            profile.setFullName(user.getFullName());
            profile.setEmail(user.getEmail());
        }

        UserRole userRole = user.getUserRole();
        if (userRole != null){
            userRole.setUser(user);
            user.setUserRole(userRole);
        }

        return userRepository.save(user);
    }

    //Fetch user  + profile + role
    public UserResponse getUserDetails(String email){
        User user = userRepository.searchByEmail(email);
        if (user == null){
            return null;
        }

        //Call user role service
        UserRoleResponse userRole =getUserRole(email);

        //Call  user profile service
        UserProfileResponse profile = getUserProfile(email);

        //consolidate the response
        return UserResponse.from(user,userRole,profile);
    }


    //search user by email
    public User searchByEmail(String email){
        return userRepository.searchByEmail(email);
    }

    //fetch all users
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public boolean deleteByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.searchByEmail(email));
        if (user.isEmpty()) {
            return false;
        }
        userRepository.delete(user.get());
        return true;
    }

    private UserRoleResponse getUserRole(String email) {

        ApiResponse<UserRoleResponse> response = webClient
                .get()
                .uri( userRolesServiceUrl+"/v2/tour/users/user-role/{email}", email)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<UserRoleResponse>>() {})
                .block();

        return response != null ? response.getData() : null;
    }

    private UserProfileResponse getUserProfile(String email) {

        ApiResponse<UserProfileResponse> response = webClient
                .get()
                .uri(userProfileServiceUrl+"/v2/tour/users/user-profile/{email}", email)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<UserProfileResponse>>() {})
                .block();

        return response != null ? response.getData() : null;
    }

}
