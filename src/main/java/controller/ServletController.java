package controller;

import dao.BookDao;
import java.io.IOException;
import java.util.List;
import util.EntityManagerProvider;
import javax.servlet.http.HttpServlet;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet {

    EntityManager em = EntityManagerProvider.getInstance().getEntityManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("jspex.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "list":
                    this.list(request, response);
                    break;
                default:
                    this.defaultSite(request, response);
                    break;
            }
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDao bookDao = new BookDao(em);
        List<Book> books = bookDao.getAll();

        HttpSession session = request.getSession();
        session.setAttribute("books", books);
        response.sendRedirect("view/booklist.jsp");
    }

    private void defaultSite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("jspex.jsp");
    }
}
