package com.sstinternaltools.sstinternal_tools.user.mapper.Implementation;

import com.sstinternaltools.sstinternal_tools.user.dto.UserResponseDto;
import com.sstinternaltools.sstinternal_tools.user.dto.UserSummaryDto;
import com.sstinternaltools.sstinternal_tools.user.dto.UserUpdateDto;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.mapper.Interface.UserDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserDtoMapper<User, UserUpdateDto, UserResponseDto, UserSummaryDto> {
    @Override
    public User fromUpdateDto(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.getUsername() != null) {
            user.setUsername(userUpdateDto.getUsername());
        }
        return user;
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setCreatedAt(user.getCreatedAt());
        userResponseDto.setUpdatedAt(user.getUpdatedAt());
        userResponseDto.setUserRoles(user.getUserRoles());
        return userResponseDto;
    }

    @Override
    public UserSummaryDto toSummaryDto(User user) {
        UserSummaryDto userSummaryDto = new UserSummaryDto();
        userSummaryDto.setUsername(user.getUsername());
        userSummaryDto.setEmail(user.getEmail());
        userSummaryDto.setUserRoles(user.getUserRoles());
        return userSummaryDto;
    }
}
