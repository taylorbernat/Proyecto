

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/Stylesheets1.css">

        <link href="https://fonts.googleapis.com/css2?family=Bungee+Inline&display=swap" rel="stylesheet">

        <title>inicio de sesion</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <main id="contenedorgeneral">
            <img id="logo" src="Imagenes/cuadro.png" alt="imagen">
            <div id="contenedor">
                <h1>inicio de sesion</h1>
                <form id="formularioSession" action="IniciarSessionServlet" method="post" >
                    <input  placeholder="Usuario" id="id" name="usuario">
                    <input  placeholder="Contraseña" id="id" name="contraseña">
                    <button> iniciar sesion </button>
                    
                </form>
            </div>
        </main>
    </body>
</html>
