package project.library.dto;

import lombok.Builder;
import lombok.Value;
import project.library.entity.Gender;
import project.library.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;

}
