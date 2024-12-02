package project.library.dto;


import lombok.*;



@Builder
@Value
public class AuthorDto {

   Integer id;
   String first_name;
   String last_name;

}
