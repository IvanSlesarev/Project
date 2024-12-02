package project.library.service;

import project.library.dao.BookDao;
import project.library.dto.BookDto.BookDto;

import java.util.List;


import static java.util.stream.Collectors.toList;

public class BookService {

    private static final BookService INSTANCE = new BookService();

    private final BookDao bookDao = BookDao.getInstance();

    private BookService() {
    }


    public List<BookDto> findAll() {
        return bookDao.findAll().stream()
                .map(bookEntity -> BookDto.builder()
                        .id(bookEntity.getId())
                        .description(
                        """
                                %s - %s
                                """.formatted(bookEntity.getName(), bookEntity.getPages()))
                        .build()
                )
                .collect(toList());
        }
        public static BookService getInstance() {
        return INSTANCE;
        }

    }
