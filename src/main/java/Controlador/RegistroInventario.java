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
import java.sql.Date;

/**
 *
 * @author TOLOZA
 */
@WebServlet(name = "RegistroInventario", urlPatterns = {"/RegistroInventario"})
public class RegistroInventario extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroInventario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroInventario at " + request.getContextPath() + "</h1>");
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
        String idProductos = request.getParameter("idProducto");
        String nombresProductos = request.getParameter("nombreProducto");
        int StockProductos = Integer.parseInt(request.getParameter("stockProducto"));
        String MovimientoProductos = request.getParameter("movimientoProducto");
        int precioProductos = Integer.parseInt(request.getParameter("precioProducto"));
        String fechaProductos = request.getParameter("fechaProducto");
        Date SqlFecha = Date.valueOf(fechaProductos);
        String idUsuarioProductos = request.getParameter("idUsuarioProducto");
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
