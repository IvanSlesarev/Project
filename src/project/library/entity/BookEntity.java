package project.library.entity;

import java.util.Objects;

public class BookEntity {

    private Long id;
    private String name;
    private Integer year;
    private Integer pages;
    private Integer author_id;

    public BookEntity(Long id, String name, Integer year, Integer pages, Integer authorId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.pages = pages;
        this.author_id = authorId;
    }

    public BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getAuthorId() {
        return author_id;
    }

    public void setAuthorId(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", authorId=" + author_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(year, that.year) && Objects.equals(pages, that.pages) && Objects.equals(author_id, that.author_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, pages, author_id);
    }

    public BookEntity save(BookEntity bookEntity) {
        return new BookEntity(id, name, year, pages, author_id);
    }
}
