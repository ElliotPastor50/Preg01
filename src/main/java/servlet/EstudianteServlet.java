package servlet;

import dao.EstudiantewebJpaController;
import dto.Estudianteweb;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Estudiante")
public class EstudianteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("agregar".equals(accion)) {
            Estudianteweb e = new Estudianteweb();
            e.setNdniEstdWeb(request.getParameter("ndni"));
            e.setAppaEstdWeb(request.getParameter("appa"));
            e.setApmaEstdWeb(request.getParameter("apma"));
            e.setNombEstdWeb(request.getParameter("nomb"));
            e.setFechNaciEstdWeb(Date.valueOf(request.getParameter("fecha")));
            e.setLogiEstd(request.getParameter("login"));
            e.setPassEstd("123456"); // Por defecto

            new EstudiantewebJpaController().create(e);
            response.sendRedirect("principal.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("listar".equals(accion)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            List<Estudianteweb> lista = new EstudiantewebJpaController().findEstudiantewebEntities();

            // Armar JSON manualmente (sin Gson)
            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < lista.size(); i++) {
                Estudianteweb e = lista.get(i);
                json.append("{")
                    .append("\"ndni\":\"").append(e.getNdniEstdWeb()).append("\",")
                    .append("\"appa\":\"").append(e.getAppaEstdWeb()).append("\",")
                    .append("\"apma\":\"").append(e.getApmaEstdWeb()).append("\",")
                    .append("\"nomb\":\"").append(e.getNombEstdWeb()).append("\",")
                    .append("\"fecha\":\"").append(e.getFechNaciEstdWeb()).append("\",")
                    .append("\"login\":\"").append(e.getLogiEstd()).append("\"")
                    .append("}");
                if (i < lista.size() - 1) {
                    json.append(",");
                }
            }

            json.append("]");
            response.getWriter().write(json.toString());
        }
    }
}

