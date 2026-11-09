package servlet;

import com.mycompany.webfish.User;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.UserRepository;
import util.Html;
import util.Passwords;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try (PrintWriter out = response.getWriter()) {

            if (!isValidInput(username, password, role)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("<h3>Registration failed</h3>");
                out.println("<p>All fields are required and the role must be valid.</p>");
                return;
            }

            username = username.trim();
            role = normalizeRole(role.trim());

            if (UserRepository.userExists(username)) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                out.println("<h3>Registration failed</h3>");
                out.println("<p>Username already exists.</p>");
                return;
            }

            User user = new User(username, Passwords.hash(password), role);

            boolean registered = UserRepository.addUser(user);

            if (!registered) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                out.println("<h3>Registration failed</h3>");
                out.println("<p>Username already exists.</p>");
                return;
            }

            response.setStatus(HttpServletResponse.SC_CREATED);
            out.println("<h3>Registration successful</h3>");
            out.println("<p>User " + Html.escape(username) + " has been registered.</p>");
        }
    }

    private boolean isValidInput(String username,
                                 String password,
                                 String role) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        if (role == null || role.trim().isEmpty()) {
            return false;
        }

        return role.equalsIgnoreCase("member")
                || role.equalsIgnoreCase("churchLeader");
    }

    private String normalizeRole(String role) {
        if (role.equalsIgnoreCase("churchLeader")) {
            return "churchLeader";
        }
        return role.trim().toLowerCase();
    }
}
