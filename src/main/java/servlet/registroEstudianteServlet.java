package servlet;

import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registro")
public class registroEstudianteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dni = request.getParameter("ndni");
        String appa = request.getParameter("appa");
        String apma = request.getParameter("apma");
        String nomb = request.getParameter("nomb");
        String fecha = request.getParameter("fecha");
        String login = request.getParameter("login");
        String clave = request.getParameter("clave");

        Estudianteweb nuevo = new Estudianteweb();
        nuevo.setNdniEstdWeb(dni);
        nuevo.setAppaEstdWeb(appa);
        nuevo.setApmaEstdWeb(apma);
        nuevo.setNombEstdWeb(nomb);
        nuevo.setFechNaciEstdWeb(Date.valueOf(fecha));
        nuevo.setLogiEstd(login);
        nuevo.setPassEstd(clave);

        try {
            EstudiantewebJpaController ctrl = new EstudiantewebJpaController();
            ctrl.create(nuevo);
            response.sendRedirect("index.html?registro=ok");
        } catch (IOException e) {
            response.sendRedirect("registro.html?error=registro");
        }
    }
}
