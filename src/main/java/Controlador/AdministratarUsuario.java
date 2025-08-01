
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.stream.Collectors;
import org.json.JSONObject;


import Modelo.Registro;
import Modelo.sql.Conexion;
import Modelo.sql.RegistroDao;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "AdministratarUsuario", urlPatterns = {"/AdministratarUsuario"})
public class AdministratarUsuario extends HttpServlet {


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
        
        
        BufferedReader reader = request.getReader();
        String jsonBody = reader.lines().collect(Collectors.joining());
        
        JSONObject datos = new JSONObject(jsonBody);
        
        String accion = datos.getString("accion");
        int id = datos.getInt("id");
        boolean estado = datos.getBoolean("estado");
        
        String mensajeRespuesta = "Usuario desbloqueado correctamente";
        Registro objUser = new Registro(id, accion, accion, jsonBody, estado, accion, id);  
        
        boolean users = false;
        try (Connection conector = Conexion.hacerConexion()) {

            RegistroDao DaoUSers = new RegistroDao(conector);
            users = DaoUSers.actualizarEstado(objUser);
          
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }



        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject respuesta = new JSONObject();
        respuesta.put("Confimador", users);
        respuesta.put("mensaje", mensajeRespuesta);

        response.getWriter().write(respuesta.toString());
        processRequest(request, response);

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
