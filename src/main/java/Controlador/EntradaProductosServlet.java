
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

import Modelo.Almacen;
import Modelo.sql.VerProductos;
import Modelo.EntradaProducto;
import Modelo.sql.Conexion;
import Modelo.sql.EntradaProductoDao;
import java.sql.Connection;

@WebServlet(name = "EntradaProductosServlet", urlPatterns = {"/EntradaProductosServlet"})
public class EntradaProductosServlet extends HttpServlet {

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

    private int convertirIdProveedorAEmpresa(int idProveedor) {
        switch (idProveedor) {
            case 1002:
                return 1202;
            case 1012:
                return 1302;
            case 1032:
                return 1402;

            case 2002:
                return 2202;
            case 2012:
                return 2302;
            case 2032:
                return 2402;

            case 3002:
                return 3202;
            case 3012:
                return 3302;
            case 3032:
                return 3402;

            default:
                return 777; 
        }
    }
    private String convertirNombreProveedorAEmpresa(int idProveedor) {
        switch (idProveedor) {
            case 1202:
                return "Amphora Bolso Monique";
            case 1302:
                return "Studio F Bolso Mini Croco";
            case 1402:
                return "Totto Morral Crayola";
            
                
            case 2202:
                return "Studio F Cartera Kira Chain";
            case 2302:
                return "Vélez Cartera Crossbody Línea Nature";
            case 2402:
                return "Totto Cartera Urbana Aurora";

            case 3202:
                return "Totto Tenis Urban Style One";
            case 3302:
                return "Vélez Botines Urbanos Hombre";
            case 3402:
                return "Gef Tenis Mujer Básico Beige";

            default:
                return "ERROR PRODUCTO INEXISTENTE";
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] ids = request.getParameterValues("idProducto");
        String[] stocks = request.getParameterValues("stockProducto");
        String[] fechas = request.getParameterValues("fechaProducto");
        String[] proovedores = request.getParameterValues("proovedor");
        String[] precios = request.getParameterValues("precioProducto");

        for (int i = 0; i < ids.length; i++) {
            try {
                int id = Integer.parseInt(ids[i]);
                int stock = Integer.parseInt(stocks[i]);
                Date fecha = Date.valueOf(fechas[i]);
                String proovedor = proovedores[i];
                int precio = Integer.parseInt(precios[i]);
                String tipo = request.getParameter("TipoProduto");

                float newPrecio = precio + (precio * 0.30f);
                EntradaProducto producto = new EntradaProducto(id, stock, fecha, proovedor, "Entrada", precio, tipo);

                int idEmpresa = convertirIdProveedorAEmpresa(id);
                String nombreEmpresa = convertirNombreProveedorAEmpresa(idEmpresa);

                Almacen objAlmacen = new Almacen(idEmpresa, stock, newPrecio, id, tipo, nombreEmpresa);

                try (Connection conector = Conexion.hacerConexion()) {
                    
                    
                    EntradaProductoDao dao = new EntradaProductoDao(conector);
                    VerProductos daoAlmacen = new VerProductos(conector);
                    boolean exito=true;
                    
                    
                    if (dao.existeRegistro(id)) {
                        exito = dao.sumarStock(producto);                       
                    }else{
                        exito = dao.agregarRegistro(producto);                   
                    };
                    
                    
                    if (exito) {
                        boolean Existe = daoAlmacen.existeRegistro(idEmpresa);
                        System.out.println("Existencia" + Existe);
                        if (Existe) {
                            daoAlmacen.sumarStock(objAlmacen);
                        } else {
                            daoAlmacen.agregarRegistro(objAlmacen);
                        }
                    } else {
                        System.err.println("No se insertó en entrada_almacen. Se omite registro en almacen para ID: " + id);
                    }
                    
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("❌ Error en fila " + i);
                response.sendRedirect("RegistroInventario.jsp");
                return;
            }
        }

        response.sendRedirect("EntradaProductos.jsp");
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
