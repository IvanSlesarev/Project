package project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.library.dto.BookDto.BookDto;
import project.library.service.BookService;
import project.library.util.JspHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDto> bookDtos = bookService.findAll();
        req.setAttribute("book", bookDtos);
        req.getSession().setAttribute("bookMap", bookDtos.stream()
                .collect(toMap(BookDto::getId, BookDto::getDescription)));

        req.getRequestDispatcher(JspHelper.getPath("content"))
                .forward(req, resp);
    }
}
