class GestorUsuarios {
    constructor() {
        this.tipoProductoBoton = document.querySelectorAll('.botonTipo');
        this.filas = document.querySelectorAll('.registro');
        this.botonesUsuario = document.querySelectorAll('button[name="BotonUser"]');
        this.botonesConfirmacionAdvertencia = document.querySelectorAll('.botonAdvertencia');
        this.contenedorPrincipal = document.getElementById("contenidoprincipal");
        this.inicializarEventos();
    }

    inicializarEventos() {
        this.tipoProductoBoton.forEach(boton => {
            boton.addEventListener("click", (e) => this.cambiarCategoria(e));
        });

        this.botonesUsuario.forEach(boton => {
            
            boton.addEventListener("click", (e) => this.enviarAccion(e));
        });
       
        

    }
    
  

    enviarAccion(e) {
        e.preventDefault();
        console.log("hola fetch borra en funcionamiento");
        const boton = e.currentTarget;
        const accion = boton.value;
        const idUsuario = boton.getAttribute("data-id");
        const estadoUsuario = boton.getAttribute("data-estado");
        let datoinput =0;

        if(boton.value === "BorrarUsuario"){
            const contenedorPrincipal = document.getElementById("contenidoprincipal");

            const contenedorGenAdvertencia = document.createElement("div");
            const contenedorTextAdvertencia = document.createElement("div");
            contenedorTextAdvertencia.classList.add("divTextoAdvertencia");
            const contenedorButtonAdvertencia = document.createElement("div");
            contenedorButtonAdvertencia.classList.add("divConfirmadorAdvertencia");



            const textoConfirmacion = document.createElement("h3");
            textoConfirmacion.classList.add("mensajeConfirmacion");
            textoConfirmacion.textContent = "Ingrese el código de confirmación";
            
           
            const textoConfirmacionExtra = document.createElement("h6");
            textoConfirmacionExtra.classList.add("mensajeConfirmacion2");
            textoConfirmacionExtra.textContent = "*Tome en cuenta que esto no se puede revertir*";
           
            const inputDatos = document.createElement("input");
            inputDatos.type="password";
            inputDatos.id="inputAdvertencia";

            
            
            const botonSi = document.createElement("button");
            botonSi.classList.add("botonAdvertencia");
            botonSi.id="BotonAdvertenciaConfirmar";
            botonSi.value="confirmar";
            botonSi.textContent = "Confirmar";

            const botonNo = document.createElement("button");
            botonNo.classList.add("botonAdvertencia");
            botonNo.id = "BotonAdvertenciaCancelar";
            botonSi.value = "cancelar";
            botonNo.textContent = "Cancelar";
                
            botonNo.addEventListener("click", (e) => {
                contenedorGenAdvertencia.remove();
            });


            inputDatos.addEventListener("input", (e) => {
                if (e.target.value.length > 2) {
                    console.log("Valor del input dinámico:", e.target.value);
                    botonSi.classList.add("Accesible");
                    botonSi.addEventListener("click",()=>{
                    datoinput= e.target.value;
                    this.apiFetch(accion, idUsuario, estadoUsuario, datoinput, boton,contenedorGenAdvertencia);
                    

                    });
                }else{
                    botonSi.classList.remove("Accesible");
                }
            });
            
            contenedorTextAdvertencia.appendChild(textoConfirmacion);
            contenedorTextAdvertencia.appendChild(textoConfirmacionExtra);
            contenedorTextAdvertencia.appendChild(inputDatos);
            contenedorButtonAdvertencia.appendChild(botonSi);
            contenedorButtonAdvertencia.appendChild(botonNo);

            contenedorGenAdvertencia.classList.add("mensajeEmergente");
            contenedorGenAdvertencia.appendChild(contenedorTextAdvertencia);
            contenedorGenAdvertencia.appendChild(contenedorButtonAdvertencia);

            contenedorPrincipal.appendChild(contenedorGenAdvertencia);
        }
        if(boton.value === "CancadoAbierto"){
            this.apiFetch(accion,idUsuario,estadoUsuario,datoinput,boton);
        }else if(boton.value === "CancadoCerrado"){ 
            this.apiFetch(accion, idUsuario, estadoUsuario, datoinput, boton);
        }
        
       
    }
    apiFetch(accion,idUsuario,estadoUsuario,datoinput,boton,contentAdver){
        
        fetch("AdministracionUsuarioServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                accion: accion,
                id: idUsuario,
                estado: estadoUsuario,
                codeElim: datoinput,
                contentAdver: contentAdver
            })
        })
                .then(res => res.json())
        
                .then(data => {
                    if (data.Confimador) {
                        const tarjeta = boton.closest(".registro");
                        const estadoTexto = tarjeta.querySelector("h4.direccion:last-of-type");

                        const nuevoEstado = (boton.value === "CancadoAbierto") ? "Inactivo" : "Activo";
                        estadoTexto.textContent = "Estado:" + nuevoEstado;

                        const nuevaImg = tarjeta.querySelector(".Candado");
                        if (boton.value === "CancadoAbierto") {
                            boton.value = "CancadoCerrado";
                            boton.setAttribute("data-estado", "false");
                            nuevaImg.src = "Imagens/CandadoCerrado.png";
                        } else if (boton.value === "CancadoCerrado") {
                            boton.value = "CancadoAbierto";
                            boton.setAttribute("data-estado", "true");
                            nuevaImg.src = "Imagens/CandadoAbierto.png";

                        } else if (boton.value === "BorrarUsuario" && data.ConfimadorElim) {
                            tarjeta.remove();
                            contentAdver.remove();

                        }

                        const msgBox = document.getElementById("mensajeRespuesta");
                        msgBox.textContent = data.mensaje;
                        msgBox.classList.add("mostrar");

                        setTimeout(() => msgBox.classList.remove("mostrar"), 3000);
                    }
                })
                .catch(err => {
                    console.error("Error en la petición:", err);
                });
    }
    cambiarCategoria(e) {
        this.tipoProductoBoton.forEach(b => b.classList.remove("activa"));
        e.currentTarget.classList.add("activa");

        const categoria = e.currentTarget.value;

        this.filas.forEach(fila => {
            const tipo = fila.getAttribute("data-tipo");
            fila.style.display = (categoria === "Todos" || tipo === categoria) ? "" : "none";
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {new GestorUsuarios();
});
