package repository.loginImpl;

import LoginService.LoginSessionServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

public abstract class LoginServiceSessionImpl extends LoginSessionServlet {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException;
}

