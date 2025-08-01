<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        
        <link rel="stylesheet" type="text/css" href="Styles/StyleManejoUser.css">
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
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="ManejoInventarios"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828911.png" class="icono"/> Manejo de inventarios</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="EntradaProductos"><img src="https://cdn-icons-png.flaticon.com/512/2311/2311524.png" class="icono"/> Entrada de productos</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="verProducto"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> ver productos</button></li>
                            <li><button class="ButtonCarruselCS" name="ButtonCarrusel" value="SalidaProductos"><img src="https://cdn-icons-png.flaticon.com/512/1828/1828743.png" class="icono"/> Salida de productos</button></li>
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
                <div id="Registros">
                    <c:if test="${not empty Usarios}">
                        <c:forEach var="item" items="${Usarios}">
                            <div class="registro">
                                <img src="Imagens/UserIMG.png" class="imagenUsuario" alt="alt"/> 
                                <div class="datosUsuario">
                                    <h4 class="nombre">Nombre:${item.nombre}</h4>
                                    <h4 class="identificar">Id:${item.idEmpleado}</h4>
                                    <h4 class="direccion">Email:${item.correo}</h4>
                                    <c:choose>
                                        <c:when test="${item.estado}">
                                            <h4 class="direccion">Estado:Activo</h4>
                                        </c:when>
                                        <c:otherwise>
                                            <h4 class="direccion">Estado:Inactivo</h4>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <article id="decicionesUser">
                                    <c:choose>
                                        <c:when test="${item.estado}">
                                            <button data-id="${item.idEmpleado}" data-estado="${item.estado}" name="BotonUser" value="CancadoAbierto" class="bottonEleccion"><img class="Candado" src="Imagens/CandadoAbierto.png" alt=""/></button>
                                        </c:when>
                                        <c:otherwise>
                                            <button data-id="${item.idEmpleado}" data-estado="${item.estado}" name="BotonUser" value="CancadoCerrado" class="bottonEleccion"><img class="Candado" src="Imagens/CandadoCerrado.png" alt=""/></button>
                                        </c:otherwise>
                                    </c:choose>
                                            <button  data-id="${item.idEmpleado}" data-estado="${item.estado}"  name="BotonUser" value="BorrarUsuario" class="bottonEleccion" id="equisBoton"><img class="equis" src="Imagens/Equis.png" alt=""/></button>
                                </article>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <div id="mensajeRespuesta" class="mensaje"></div>

            </main>
        </div>

        <script src="Interacciones/ManejoUsuarios.js"></script>
    </body>

</html>