package project.library.dao;

import project.library.entity.BookEntity;
import project.library.util.ConnectionManager;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao implements Dao<Integer, BookEntity> {

    private static final BookDao INSTANCE = new BookDao();

    private static final String FIND_ALL = """
            SELECT *
            FROM book
            """;

    private BookDao() {
    }


    @Override
    public List<BookEntity> findAll() {
        try (var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<BookEntity> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildbook(resultSet));
            }

            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Optional<BookEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(BookEntity bookEntity) {
        return false;
    }

    @Override
    public void update(BookEntity entity) {

    }

    @Override
    public BookEntity save(BookEntity entity) {
        return null;
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }

    private BookEntity buildbook(ResultSet resultSet) throws SQLException {
        return new BookEntity(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("year", Integer.class),
                resultSet.getObject("pages", Integer.class),
                resultSet.getObject("author_id")
        );
    }
}
