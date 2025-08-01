<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
     <head>

        <link rel="stylesheet" type="text/css" href="Styles/styleEntrada.css">
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
                <h2>--------------------Entrada de productos--------------------</h2>
            </div> 

            <div class="categorias">
                <button class="categoria activa" name="TipoProduto" value="Bolsos">Bolsos</button>
                <button class="categoria" name="TipoProduto" value="Carteras">Carteras</button>
                <button class="categoria" name="TipoProduto" value="Zapatos">Zapatos</button>
            </div>
            <form id="formulariosTablas" action="EntradaProductosServlet" method="post">
                <table id="tablaContenido">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Stock</th>
                            <th>Proovedor</th>
                            <th>Precio</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                    <tbody id="contentGenTable">
                        <tr class="rowToble">
                            <td><select name="idProducto" type="text" value="1113" class="contentTable IdsProductos" >
                                   <option value="1002">1002</option>
                                   <option value="1012">1012</option>
                                   <option value="1032">1032</option>
                                </select>
                            </td>
                            <td><input name="stockProducto"  type="number" value="" class="contentTable stockProductos" id min="1" required></td>
                            <td><input name="proovedor" type="text" value="Amphora" class="contentTable ProovedorProductos" readonly required></td>
                            <td><input name="precioProducto"  type="number" value="120000" class="contentTable PrecioProductos" readonly required></td>
                            <td><input name="fechaProducto" type="date" value="" class="contentTable FehcaDos" required></td>
                            <input name="TipoProduto" type="hidden" value="Bolsos" id="inputTipo">
                            
                        </tr>
                    </tbody>
                </table>
                <div id="casillaBotonEnviar">
                    <button id="botonEnviar">Enviar inventario</button>
                </div>
            </form>
        </main>
    </div>

    <script src="Interacciones/EntryProducts.js"></script>
</body>

</html>