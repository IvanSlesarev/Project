package project.library.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEntity {
    private Long id;
    private String name;
    private Integer year;
    private Integer pages;
    private AuthorEntity author_id;

    public BookEntity(Long id, String name, Integer year, Integer pages, Object authorId) {
    }
}
