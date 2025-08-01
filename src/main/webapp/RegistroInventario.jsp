<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
     <head>
        <link rel="stylesheet" type="text/css" href="Styles/styleEntrada.css">
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
                    </ul> b
                </form>
            </nav>
        </aside>
        <main id="contenidoprincipal">
            <div class="ordenar">
                <button class="ordenar-btn">ORDENAR POR ▶</button>
                <input type="text" class="ordenar-input" />
                <h1>--------------------Registro inventarios--------------------</h1>
            </div>

            <div class="categorias">
                <button class="categoria">Bolsos</button>
                <button class="categoria">Carteras</button>
                <button class="categoria">Zapatos</button>
            </div>
            <form action="EntradaProductosServlet" method="post">
                <table id="miTabla">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Stock</th>
                            <th>Movimiento</th>
                            <th>Precio</th>
                            <th>Fecha</th>
                            <th>Id Usuario</th>
                        </tr>
                    </thead>
                    <tbody id="contentGenTable">
                        <tr class="rowToble">
                            <td><input name="idProducto" type="text" value="" class="contentTable"></td>
                            <td><input name="nombreProducto" type="text" value="" class="contentTable"></td>
                            <td><input name="stockProducto"  type="number" value="" class="contentTable"></td>
                            <td>
                                <select name="movimientoProducto" class="contentTable">
                                    <option selected>Entrada</option>
                                    <option>Salida</option>
                                </select>
                            </td>
                            <td><input name="precioProducto" type="number" value="" class="contentTable"></td>
                            <td><input name="fechaProducto" type="date" value="" class="contentTable"></td>
                            <td><input name="idUsuarioProducto" type="text" value="" class="contentTable"></td>
                        </tr>
                    </tbody>
                </table>
                <div id="casillaBotonEnviar">
                    <button id="botonEnviar">Enviar inventario</button>
                </div>
            </form>
        </main>
    </div>
</body>

</html>