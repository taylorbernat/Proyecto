/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import Modelo.Almacen;
import Modelo.sql.Conexion;
import Modelo.sql.VerProductos;

import Modelo.Registro;
import Modelo.sql.RegistroDao;
import java.util.ArrayList;
@WebServlet(name = "SaltoCarrusel", urlPatterns = {"/SaltoCarrusel"})
public class SaltoCarrusel extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String Eleccion = request.getParameter("ButtonCarrusel");
        switch (Eleccion) {
            case "ManejoInventarios":
                response.sendRedirect("RegistroInventario.jsp");

                break;
            case "EntradaProductos":
                response.sendRedirect("EntradaProductos.jsp");
                break;
               
            case "SalidaProductos":
                System.out.println("Hola eleccion salida");
                response.sendRedirect("SalidaProductos.jsp");

                break;
            case "verProducto":
                System.out.println("Hola ver Productos");
                try (Connection conector = Conexion.hacerConexion()) {
                    VerProductos objAlmacen = new VerProductos(conector);
                    List<Almacen> ListaDatos = objAlmacen.obtenerTodosLosRegistros();
                    
                    request.setAttribute("productos", ListaDatos);

                    request.getRequestDispatcher("VerAlmacen.jsp").forward(request, response);

                } catch (SQLException ex) {
                Logger.getLogger(SaltoCarrusel.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            break;
           
            case "Graficos":
                response.sendRedirect("Grafico.jsp");

                break;
            case "ManejoUsuarios":
                try (Connection conector = Conexion.hacerConexion()) {
                    
                    RegistroDao DaoUSers = new RegistroDao(conector);
                    List<Registro> users= DaoUSers.obtenerTodosLosRegistros();
                    request.setAttribute("Usarios", users);
                   System.out.println("Hola eleccion salida");
                    request.getRequestDispatcher("AdministracionUsuario.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("error.jsp");
                }
              
                break;

            default:
                throw new AssertionError();
        }
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
