package project.library.dto.BookDto;

import lombok.*;

@Value
@Builder
public class BookDto {

    Long id;
    String description;
}
