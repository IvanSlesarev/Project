package project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.library.service.AuthorService;
import project.library.util.JspHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private final AuthorService authorService = AuthorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var authorId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("author", authorService.findAll(authorId));

        req.getRequestDispatcher(JspHelper.getPath("author"))
                .forward(req, resp);


    }
}
