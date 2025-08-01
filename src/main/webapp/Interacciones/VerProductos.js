class GestorProductos {
    constructor(tablaId, tipoClase) {
        this.tabla = document.getElementById(tablaId);
        this.cuerpoTabla = this.tabla.querySelector("tbody");
        this.tipoProductoBoton = document.querySelectorAll("." + tipoClase);

        this.filas = Array.from(this.cuerpoTabla.querySelectorAll("tr"));

        this.inicializarEventos();
    }

    inicializarEventos() {
        this.tipoProductoBoton.forEach(element => {
            element.addEventListener("click", (e) => this.cambiarCategoria(e));
        });

        this.tabla.addEventListener("keydown", (e) => this.manejarTeclasTabla(e));
    }

    cambiarCategoria(e) {
        //-------------------- Modificacion de Boton Tipos --------------------
        this.tipoProductoBoton.forEach(b => b.classList.remove("activa"));
        e.currentTarget.classList.add("activa");



        //-------------------- Mostrar u ocultar filas --------------------
        const categoria = e.currentTarget.value;
        this.filas.forEach(fila => {
            const tipo = fila.getAttribute("data-tipo");
            if (categoria === "Todos" || tipo === categoria) {
                fila.style.display = "";
            } else {
                fila.style.display = "none";
            }
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const gestor = new GestorProductos("tablaContenido", "categoria");
});