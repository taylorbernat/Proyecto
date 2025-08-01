
package Controlador;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;

import Modelo.Registro;
import Modelo.sql.RegistroDao;
import Modelo.sql.Conexion;


@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameUser=request.getParameter("usuario");
        int id_user =Integer.parseInt(request.getParameter("cedula"));
        String emailUser = request.getParameter("correo");
        String codeUser = request.getParameter("contraseña");
        Registro nuevoRegistro = new Registro(id_user, nameUser, emailUser, codeUser, false, "Empleado", 1093742568);

        
        
        try (Connection conector = Conexion.hacerConexion()) {
            RegistroDao dao = new RegistroDao(conector);
            boolean exito = dao.agregarRegistro(nuevoRegistro);
            if (exito) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("RestroDao.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
