package project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.library.service.BookService;

import java.awt.print.Book;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список книг:</h1>");
            printWriter.write("<ul>");
            bookService.findAll().forEach(bookDto -> {
                printWriter.write("""
                        <li>
                            <a href="/books?flightId=%d">%s</a>
                        </li>
                        """.formatted(bookDto.getId(), bookDto.getDescription()));
            });
            printWriter.write("</ul>");
        }
    }

}
