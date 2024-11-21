package project.library.dao;

import project.library.entity.BookEntity;
import project.library.exception.DaoException;
import project.library.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao {

    private static final BookDao INSTANCE = new BookDao();
    private static final String DELETE_SQL = """
            DELETE FROM book
            WHERE id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO book (name, year, pages, author_id)
            VALUES (?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE book 
            SET name = ?, 
                year = ?, 
                pages = ?,
                author_id = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id, 
                name, 
                year,  
                pages,
                author_id
            FROM book
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String YEAR = "year";
    private static final String PAGES = "pages";
    private static final String AUTHOR_ID = "author_id";

    private BookDao() {
    }

    public List<BookEntity> findAll() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<BookEntity> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<BookEntity> findById(Long id) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var resultSet = preparedStatement.executeQuery();
            BookEntity bookEntity = null;
            if (resultSet.next()) {
                bookEntity = buildBook(resultSet);

            }

            return Optional.ofNullable(bookEntity);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private static BookEntity buildBook(ResultSet resultSet) throws SQLException {
        return new BookEntity(
                resultSet.getLong(ID),
                resultSet.getString(NAME),
                resultSet.getInt(YEAR),
                resultSet.getInt(PAGES),
                resultSet.getInt(AUTHOR_ID)
        );
    }

    public void update(BookEntity bookEntity) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(UPDATE_SQL)) {
            prepareStatement.setString(1, bookEntity.getName());
            prepareStatement.setInt(2, bookEntity.getYear());
            prepareStatement.setInt(3, bookEntity.getPages());
            prepareStatement.setInt(4, bookEntity.getAuthor_id());
            prepareStatement.setLong(5, bookEntity.getId());

            prepareStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public BookEntity save(BookEntity bookEntity) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, bookEntity.getName());
            preparedStatement.setInt(2, bookEntity.getYear());
            preparedStatement.setInt(3, bookEntity.getPages());
            preparedStatement.setInt(4, bookEntity.getAuthor_id());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bookEntity.setId(generatedKeys.getLong("id"));
            }
            return bookEntity;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public boolean delete(Long id) throws SQLException {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static BookDao getInstance() {
        return INSTANCE;

    }

}
