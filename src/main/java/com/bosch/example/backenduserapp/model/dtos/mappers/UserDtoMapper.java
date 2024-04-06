package com.bosch.example.backenduserapp.model.dtos.mappers;

import com.bosch.example.backenduserapp.model.dtos.UserDto;
import com.bosch.example.backenduserapp.model.entities.User;


public class UserDtoMapper {

    private User user;

    private UserDtoMapper() {

    }

    public static UserDtoMapper builder() {
        return new UserDtoMapper();
    }

    public UserDtoMapper setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build() {
        if (user == null) {
            throw new RuntimeException("Entity User should not be null.");
        }
        return new UserDto(this.user.getId(), this.user.getUsername(), this.user.getEmail());
    }
}
