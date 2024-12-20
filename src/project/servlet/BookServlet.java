package project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.library.service.BookService;
import project.library.util.JspHelper;

import java.awt.print.Book;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("book", bookService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("book"))
                .forward(req, resp);
    }

}
