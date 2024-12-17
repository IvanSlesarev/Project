package project.mapper;

import lombok.NoArgsConstructor;
import project.library.dto.UserDto;
import project.library.entity.UserEntity;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<UserEntity, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(UserEntity object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .image(object.getImage())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
