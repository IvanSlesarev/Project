package project.mapper;

import lombok.NoArgsConstructor;
import project.library.dto.CreateUserDto;
import project.library.entity.Gender;
import project.library.entity.Role;
import project.library.entity.UserEntity;
import project.library.util.LocalDateFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {

    private static final String IMAGE_FOLDER = "users/";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public UserEntity mapFrom(CreateUserDto object) {
        return UserEntity.builder()
                .name(object.getName())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }


    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

}
