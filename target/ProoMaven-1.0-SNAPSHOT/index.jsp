
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/IndexStylos.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header id="headPrincipal">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF3qUi7LzV6K8QL9Ju9URHp4GA7DFx5k3AHg&s" alt="Logo">
            <nav id="barraBotones">
                <form id="contenedorBotones" action="Saltopagina" method="get">
                    <button name="Eleccion" value="iniciarSession" class="botonesInicio" id="iniciarSession">Iniciar session</button>
                    <button name="Eleccion" value="registro" class="botonesInicio" id="registro">Registro</button>
                </form>
            </nav>
            
        </header>
        <article id="contenidoPrincipal">
            <img src="Imagenes/ejemplito.png" alt="Logo">
            <div id="contenedorTexto">
                <h2>Bienvenidos a Kpricho</h2>
                <p class="informacionEmpresa">Descubre bolsos que cuentan historias.<br>En Kpricho diseñamos bolsos únicos que combinan estilo, funcionalidad y calidad. Cada pieza está pensada para acompañarte en tu día a día, adaptándose a tu ritmo y resaltando tu personalidad. Desde diseños clásicos hasta colecciones modernas y atrevidas, nuestros bolsos están hechos con materiales atrevidas, nuestros bolsos están hechos con materiales seleccionados y mano de obra experta, cuidando cada detalle para ofrecerte un producto duradero, elegante y auténtico.</p>
            </div>
        </article>
        <h1 id="titulosInventarios">
            Inventarios
        </h1>
        
        <main id="imgCarrusel">
            <img src="">
            <img src="">
            <img src="">
            <img src="">
            <img src="">

        </main>
        
        <footer id="piePagina">
            
        </footer>
    </body>
</html>
