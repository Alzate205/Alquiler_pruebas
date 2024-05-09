package Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServlet;
import mapping.dtos.userDTO;
import mapping.mappers.UserMapper;
import model.user;
import repository.Repository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.userImpl.userRepositoryJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class userServiceImpl extends HttpServlet implements Repository<userDTO> {

    private Repository<user> userRepository;

    public void UserServiceImpl() {
        this.userRepository = new userRepositoryJDBC();
    }

    public userServiceImpl(Repository<user> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(userDTO userDTO) {
        userRepository.save(UserMapper.mapFromDTO(userDTO));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<userDTO> users = list();
        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=users.xls");
        }
        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Listado de Usuarios</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listado de Usuarios!</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/users.xls" + "\">Exportar a xls</a></p>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>apellido</th>");
            out.println("<th>telefono</th>");
            out.println("<th>contraseña</th>");
            out.println("</tr>");
            out.println("</table>");
            if (!esXls) {
                out.println("</body>");
                out.println("</html>");
            }
        }
    }



    @Override
    public List<userDTO> list() {
        return userRepository.list().stream().map(UserMapper::mapFromModel).toList();
    }

    @Override
    public userDTO byId(int id) {
        user user = userRepository.byId(id);
        return UserMapper.mapFromModel(user);
    }

    @Override
    public void save(userDTO user) {
        userRepository.save(UserMapper.mapFromDTO(user));
    }



@Override
    public void delete(int id) {

    userRepository.delete(id);
    }

}
