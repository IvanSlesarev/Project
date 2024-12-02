package project.library.dao;

import lombok.SneakyThrows;
import project.library.entity.AuthorEntity;
import project.library.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthorDao implements Dao<Integer, AuthorEntity> {

    private static final AuthorDao INSTANCE = new AuthorDao();

    private static final String FIND_ALL_BOOKS = """
            SELECT *
            FROM author
            """;

    private AuthorDao() {
    }

    @Override
    @SneakyThrows
    public List<AuthorEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS)) {
            var resultSet = preparedStatement.executeQuery();
            List<AuthorEntity> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(buildAuthor(resultSet));
            }

            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<AuthorEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(AuthorEntity authorEntity) {
        return false;
    }

    @Override
    public void update(AuthorEntity entity) {

    }

    @Override
    public AuthorEntity save(AuthorEntity entity) {
        return null;
    }

    public static AuthorDao getInstance() {
        return INSTANCE;
    }

    private AuthorEntity buildAuthor(ResultSet resultSet) throws SQLException {
        return new AuthorEntity(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name",String.class)
        );
    }

}
