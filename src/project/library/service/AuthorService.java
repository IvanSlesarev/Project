package project.library.service;

import project.library.dao.AuthorDao;
import project.library.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AuthorService {

    private static final AuthorService INSTANCE = new AuthorService();

    private final AuthorDao authorDao = AuthorDao.getInstance();

    private AuthorService() {
    }

    public List<AuthorDto> findAll(Integer authorId) {
        return authorDao.findAll().stream()
                .map(author -> AuthorDto.builder()
                                .id(author.getId())
                        .first_name(author.getFirst_name())
                        .last_name(author.getLast_name())
                        .build()
                )
                .collect(toList());

    }

    public static AuthorService getInstance() {
        return INSTANCE;
    }
}
