package project.library;

import project.library.dao.BookDao;
import project.library.entity.BookEntity;

import java.sql.SQLException;
import java.util.Optional;

public class DaoRunner {

    public static void main(String[] args) {
        var books = BookDao.getInstance().findAll();
        System.out.println(books);

    }

    private static void deleteTest(Long id) throws SQLException { // пробросил эксепшн, так как не запускалось
        var bookDao = BookDao.getInstance();
        var deleteResult = bookDao.delete(id);
        System.out.println(deleteResult);
    }

    private static void saveTest() {
        var bookDao = BookDao.getInstance();
        var bookEntity = new BookEntity();
        bookEntity.setName("Kaffka");
        bookEntity.setYear(1995);
        bookEntity.setPages(1002);
        bookEntity.setAuthorId(6);

        var savedBookEntity = bookEntity.save(bookEntity); // создал в BookEntity object, потому что не запускалось(
        System.out.println(savedBookEntity);
    }
}
