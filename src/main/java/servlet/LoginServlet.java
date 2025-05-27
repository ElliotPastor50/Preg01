package servlet;

import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dni = request.getParameter("ndni");
        String clave = request.getParameter("clave");

        Estudianteweb est = new EstudiantewebJpaController().buscarPorDni(dni);

        if (est != null && est.getPassEstd().equals(clave)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", est);
            response.sendRedirect("principal.html");
        } else {
            response.sendRedirect("index.html?error=1");
        }
    }
}
