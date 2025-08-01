<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" type="text/css" href="Styles/styleVerObjetos.css">
        <link rel="stylesheet" type="text/css" href="Styles/MediasQuery/StyleEntradaLaptops.css">

        <link href="https://fonts.googleapis.com/css2?family=Bungee+Inline&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="contenedor">
            <aside class="general">
                <h2>K-PRICHO</h2>
                <nav>
                    <form action="SaltoCarrusel" method="get">
                        <ul>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="EntradaProductos"><img src="https://cdn-icons-png.flaticon.com/512/2311/2311524.png" class="icono"/> Entrada de productos</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="SalidaProductos"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> Salida de productos</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="verProducto"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> ver productos</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="ManejoInventarios"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828911.png" class="icono"/> Manejo de inventarios</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="Graficos"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> Ver Gradico</button></li>


                            <c:choose>
                                <c:when test="${usuario.estatus}">
                                    <h4 class="direccion">Estado:Activo</h4>
                                </c:when>
                                <c:when test="${Admin.estatus =='Admin'}">
                                    <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="ManejoUsuarios"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> Modificar USer</button></li>
                                    <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="Graficos"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> Modificar Inventario</button></li>
                                        </c:when>
                                        <c:otherwise>
                                    <h4 class="direccion">Estado:Inactivo</h4>
                                </c:otherwise>
                            </c:choose>


                            <c:if test="">                      

                            </c:if>
                        </ul>
                    </form>
                </nav>
            </aside>

            <main id="contenidoprincipal">
                <div class="ordenar">
                    <button class="ordenar-btn">ORDENAR POR ▶</button>
                    <input type="text" class="ordenar-input" />
                    <h2>--------------------Salida de productos--------------------</h2>
                </div> 

                <div class="categorias">
                    <button class="categoria" name="TipoProduto" value="Bolsos">Bolsos</button>
                    <button class="categoria" name="TipoProduto" value="Carteras">Carteras</button>
                    <button class="categoria" name="TipoProduto" value="Zapatos">Zapatos</button>
                    <button class="categoria activa" name="TipoProduto" value="Todos">Todos</button>

                    
                </div>
                <form id="formulariosTablas" action="EntradaProductosServlet" method="post">
                    <table id="tablaContenido">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Stock</th>
                                <th>Precio</th>
                                <th>ID entrada Producto</th>
                                <th>Tipo</th>
                                <th>Nombre del Producto</th>

                            </tr>
                        </thead>
                        <tbody id="contentGenTable">
                                          
                        <c:if test="${not empty productos}">
                            <c:forEach var="item" items="${productos}">
                                <tr class="rowToble" data-tipo="${item.tipo_producto}">
                                    <td >${item.id_productos}</td>
                                    <td >${item.unidades_stock}</td>
                                    <td >${item.precio_producto}</td>
                                    <td >${item.id_producots_entrada}</td>
                                    <td class="IdEmpresa">${item.tipo_producto}</td>
                                    <td >${item.nombre_producto}</td>

                                </tr>
                            </c:forEach>
                        </c:if>
 
                        </tbody>
                    </table>
                   
                </form>
            </main>
        </div>

        <script src="Interacciones/VerProductos.js"></script>
    </body>

</html>