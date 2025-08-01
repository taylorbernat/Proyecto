<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <link rel="stylesheet" type="text/css" href="Styles/StyleKricho.css">
        <link href="https://fonts.googleapis.com/css2?family=Bungee+Inline&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
</head>
<body>
    <main id="caca">
        <form action="RegistroServlet" method="post">
            <h2>Registro</h2>
            <label for="usuario"></label>
            <input type="text" id="usuario" name="usuario"  placeholder="Usuario" required><br><br>

            <label for="cedula"></label>
            <input type="text" name="cedula" maxlength="10"  onkeypress="return soloNumeros(event)" onpaste="return false" required><br><br>

            <label for="correo"></label>
            <input type="email" id="correo" name="correo"  placeholder="Correo" required><br><br>

            <label for="contraseña"></label>
            <input type="password" id="contraseña" name="contraseña"  placeholder="Contraseña" required><br><br>

            <input type="submit" value="Registrar">
        </form>
        <img src="Cuadro.webp" id="Cuadrito">
    </main>
    <script>
        function soloNumeros(e) {
            const charCode = e.which ? e.which : e.keyCode;
            if (charCode < 48 || charCode > 57) {
                e.preventDefault(); // No deja escribir
                return false;
            }
            return true;
        }
    </script>

</body>
</html>