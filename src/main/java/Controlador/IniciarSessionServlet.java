    package Controlador;

    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
     */

    import Modelo.Registro;
    import Modelo.RegistroAdmi;
    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;



    import Modelo.sql.RegistroDao;
    import  Modelo.sql.Conexion;
    import java.sql.Connection;
    import Modelo.sql.RegistroAdminDao;
    import jakarta.servlet.http.HttpSession;
    @WebServlet(urlPatterns = {"/IniciarSessionServlet"})
    public class IniciarSessionServlet extends HttpServlet {


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

            HttpSession sesionAnterior = request.getSession(false);
            if (sesionAnterior != null) {
                sesionAnterior.invalidate();
            }
            HttpSession sesion = request.getSession();

            String Nombre =request.getParameter("usuario");
            String contraseña = request.getParameter("contraseña");

            try (Connection conector = Conexion.hacerConexion()) {
                RegistroDao dao = new RegistroDao(conector);
                RegistroAdminDao daoAdmin = new RegistroAdminDao(conector);


                Registro user = dao.login(Nombre, contraseña);
                RegistroAdmi Admin = daoAdmin.login(Nombre, contraseña);
                if(Admin != null){
                    sesion.setAttribute("Admin", Admin);
                    sesion.setMaxInactiveInterval(120 * 120); 
                    
                    response.sendRedirect("EntradaProductos.jsp");

                }else if (user != null) {

                    sesion.setAttribute("usuario", user);
                    sesion.setMaxInactiveInterval(120 * 120);
                    response.sendRedirect("EntradaProductos.jsp");
                } else {
                    response.sendRedirect("Bloqueado.jsp");
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
        }// </editor-fold>

    }
