package project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.library.service.AuthorService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private final AuthorService authorService = AuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var authorId = Integer.parseInt(req.getParameter("id"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список авторов:<h2>");
            printWriter.write("<ul>");
            authorService.findAll(authorId).forEach(authorDto -> printWriter.write("""
                        <li>
                        %s
                        </li>
                        """.formatted(authorDto.getId())));
            printWriter.write("</ul>");
        }

    }
}
