package com.solution.loginSolution.User.OAuth2.OAuth2UserAttributes;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Entity.Role;
import com.solution.loginSolution.User.General.Enum.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2UserAttributes {

    private Map<String, Object> attributes;

    protected UserType userType;
    protected String email;
    protected String name;

    public OAuth2UserAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public GeneralUser toGeneralUser() {
        return toGeneralUser(Role.ROLE_MEMBER);
    }

    public GeneralUser toGeneralUser(Role role) {
        return GeneralUser.builder()
                .userType(getUserType())
                .userName(getName())
                .userEmail(getEmail())
                .role(role)
                .build();
    }
}
