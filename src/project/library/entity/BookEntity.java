package project.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {
    private Long id;
    private String name;
    private Integer year;
    private Integer pages;
    private Integer author_id;
}
