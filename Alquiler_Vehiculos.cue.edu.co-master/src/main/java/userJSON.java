import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.userDTO;
import model.user;
import repository.Repository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class userJSON extends HttpServlet implements Repository<userDTO> {

    private Repository<user> userRepository;

    public userJSON(Repository<user> userRepository) {
        this.userRepository = userRepository;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        user user = mapper.readValue(jsonStream, user.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de producto desde json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de producto desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: "+ user.getId() + "</li>");
            out.println("<li>name: " + user.getName() + "</li>");
            out.println("<li>lastName: " + user.getLastName() + "</li>");
            out.println("<li>phoneNumber: " + user.getPhoneNumber() + "</li>");
            out.println("<li>password: " + user.getPassword() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<user> users = userRepository.list();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    public void addUser(userDTO userDTO) {

    }

    @Override
    public List<userDTO> list() {
        return null;
    }

    @Override
    public userDTO byId(int id) {
        return null;
    }

    @Override
    public void save(userDTO userDTO) {

    }

    @Override
    public void delete(int id) {

    }
}
