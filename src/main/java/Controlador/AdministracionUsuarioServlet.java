/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Registro;
import Modelo.sql.Conexion;
import Modelo.sql.RegistroDao;
import Modelo.RegistroAdmi;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.sql.Connection;
import java.util.stream.Collectors;
import org.json.JSONObject;

@WebServlet(name = "AdministracionUsuarioServlet", urlPatterns = {"/AdministracionUsuarioServlet"})
public class AdministracionUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdministracionUsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdministracionUsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject respuesta = new JSONObject();
        boolean users = false;

        try {
            BufferedReader reader = request.getReader();
            String jsonBody = reader.lines().collect(Collectors.joining());

            JSONObject datos = new JSONObject(jsonBody);

            String accion = datos.getString("accion");
            int id = datos.getInt("id");
            boolean estado = datos.getBoolean("estado");
            String[] mensajeRespuesta = {
                "Usuario Bloqueado correctamente",
                "Usuario desbloqueado correctamente",
                "Usuario Eliminado correctamente"
            };

            Registro objUser = new Registro(id, accion, accion, jsonBody, estado, accion, id);

            try (Connection conector = Conexion.hacerConexion()) {
                RegistroDao DaoUSers = new RegistroDao(conector);

                if (accion.equalsIgnoreCase("CancadoAbierto")) {
                    int codigo = datos.getInt("codeElim");

                    users = DaoUSers.actualizarEstado(objUser);
                    respuesta.put("Confimador", users);
                    respuesta.put("mensaje", mensajeRespuesta[0]);
                    respuesta.put("Estado", "Bloqueado");

                } else if (accion.equalsIgnoreCase("CancadoCerrado")) {
                    int codigo = datos.getInt("codeElim");

                    users = DaoUSers.actualizarEstado(objUser);
                    respuesta.put("Confimador", users);
                    respuesta.put("mensaje", mensajeRespuesta[1]);
                    respuesta.put("Estado", "Desbloqueado");

                } else if (accion.equalsIgnoreCase("BorrarUsuario")) {
                    int codigo = Integer.parseInt(datos.getString("codeElim"));

                    HttpSession session = request.getSession(false);
                    if (session != null && session.getAttribute("Admin") != null) {
                        RegistroAdmi Admin = (RegistroAdmi) session.getAttribute("Admin");

                        System.out.println("Código del admin: " + Admin.getIdAdmin());
                        System.out.println("Código recibido desde JS: " + codigo);
                        
                        if (Admin.getIdAdmin() == codigo) {
                            users = DaoUSers.eliminarUsuarioPorId(objUser);
                            respuesta.put("ConfimadorElim", true);
                        }
                    }

                    respuesta.put("Confimador", users);
                    respuesta.put("mensaje", mensajeRespuesta[2]);
                    respuesta.put("Estado", "Eliminado");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            // Manejo de error seguro con JSON
            respuesta.put("Confimador", false);
            respuesta.put("mensaje", "Error en el servidor: " + e.getMessage());
            respuesta.put("Estado", "Sin cambios");
        }

        // Devuelve SIEMPRE un JSON
        response.getWriter().write(respuesta.toString());
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
