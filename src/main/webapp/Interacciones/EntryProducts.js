class GestorProductos {
    constructor(tablaId, tipoClase, inputTipoId, selectId) {
        this.tabla = document.getElementById(tablaId);
        this.cuerpoTabla = this.tabla.querySelector("tbody");
        this.tipoProductoBoton = document.querySelectorAll("." + tipoClase);
        this.inforTipo = document.getElementById(inputTipoId);
        this.selectSelector = selectId;

        this.productosPorCategoria = {
            Bolsos: [1002, 1012, 1032],
            Carteras: [2002, 2012, 2032],
            Zapatos: [3002, 3012, 3032]
        };
        this.productosPorProveedor = {
            Bolsos: ["Amphora", "Studio F","Totto"],
            Carteras: ["Studio F","Vélez","Totto"],
            Zapatos: ["Totto","Vélez","Gef"]};
        this.productosPorPrecio = {
            Bolsos: [120000,135000,150000],
            Carteras: [125000,185000,142000],
            Zapatos: [178000,220000,95000]};


        this.inicializarEventos();
    }
    
   
    inicializarEventos() {
        document.querySelectorAll(".stockProductos").forEach(input => {
            this.permitirSoloNumeros(input);
        });
        let Control = true;
        
        if(Control){
            this.tipoProductoBoton.forEach(element => {
                element.addEventListener("click", (e) => this.cambiarCategoria(e));
            });
            this.tipoProductoBoton[0].click();
            Control = false;
        }
        this.tipoProductoBoton.forEach(element => {
            element.addEventListener("click", (e) => this.cambiarCategoria(e));
        });

        this.tabla.addEventListener("keydown", (e) => this.manejarTeclasTabla(e));
    }
    
    
    permitirSoloNumeros(input) {
        input.addEventListener("keydown", function (e) {
            // Permitir: backspace, delete, tab, escape, enter, flechas
            const teclasPermitidas = [
                "Backspace", "Delete", "Tab", "Escape", "Enter",
                "ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown"
            ];

            if (
                    teclasPermitidas.includes(e.key) ||
                    // Permitir Ctrl + C, Ctrl + V, Ctrl + A
                            (e.ctrlKey && ["a", "c", "v", "x"].includes(e.key.toLowerCase()))
                            ) {
                return;
            }

            // Bloquear todo lo que no sea un número del 0 al 9
            if (!/^[0-9]$/.test(e.key)) {
                e.preventDefault();
            }
        });
    }
    cambiarCategoria(e) {
        
        
        
        // Estilos activos
        this.tipoProductoBoton.forEach(b => b.classList.remove("activa"));
        e.currentTarget.classList.add("activa");

        // Obtener categoría
        const categoria = e.target.value;
        this.inforTipo.value = categoria;
        console.log("Categoría seleccionada: " + categoria);

        // Obtener productos
        const productos = this.productosPorCategoria[categoria] || [];
        const Proovedores = this.productosPorProveedor[categoria];
        const precios = this.productosPorPrecio[categoria];
       
        // Buscar todos los selects de productos

        const selects = document.querySelectorAll(this.selectSelector);
        console.log("Categoría seleccionada: " + selects);

        selects.forEach(select => {
            select.innerHTML = ""; 
            productos.forEach(id => {

                const op = document.createElement("option");
                op.value = id;
                op.textContent = id.toString();
                select.appendChild(op);
            });
        });
        
        const filas = Array.from(this.tabla.rows).slice(1);
        filas.forEach((fila, i) => {
            const celdaId = fila.cells[0];
            const celdaProveedor = fila.cells[2];
            const celdaPrecio = fila.cells[3];

            const selectId = celdaId.querySelector("select");
            const inputProveedor = celdaProveedor.querySelector("input");
            const inputPrecio = celdaPrecio.querySelector("input");

            const valores = productos.map(id => id.toString());

            // Función para actualizar los valores
            const actualizarCampos = () => {
                const index = valores.indexOf(selectId.value);
                if (index !== -1) {
                    inputProveedor.value = Proovedores[index];
                    inputPrecio.value = precios[index];
                } else {
                    inputProveedor.value = "";
                    inputPrecio.value = "";
                }
            };

            // Llenar al cargar la categoría
            actualizarCampos();

            // Llenar al cambiar la opción seleccionada
            selectId.addEventListener("change", actualizarCampos);
        });

        
        
    }
    
    manejarTeclasTabla(e) {
        const celda = e.target;
        const filaActual = celda.closest("tr");

        if (e.key === "Enter") {
            e.preventDefault();
            if (filaActual === this.cuerpoTabla.lastElementChild) {
                this.agregarFila();
            }
        }

        if ((e.key === "Delete" || e.key === "Backspace") && celda.tagName === "INPUT") {
            const input = celda;
            if (input.value.trim() === "") {
                const inputs = filaActual.querySelectorAll("input");
                const tieneDatos = Array.from(inputs).some(inp => inp !== input && inp.value.trim() !== "");

                if (!tieneDatos && this.cuerpoTabla.rows.length > 1) {
                    this.cuerpoTabla.removeChild(filaActual);
                } else if (tieneDatos && this.cuerpoTabla.rows.length > 1) {
                    const confirmar = confirm("Esta fila contiene datos. ¿Deseas eliminarla de todos modos?");
                    if (confirmar) {
                        this.cuerpoTabla.removeChild(filaActual);
                    }
                }
            }
        }
    }

    agregarFila() {
        const nuevaFila = this.cuerpoTabla.insertRow();
        nuevaFila.className = "rowTable";

        const productos = this.productosPorCategoria[this.inforTipo.value] || [];

        // --------- ID Producto ---------
        let celdaId = nuevaFila.insertCell();
        let inputId = document.createElement("select");
        inputId.className = "contentTable IdsProductos";
        productos.forEach(id => {
            const op = document.createElement("option");
            op.value = id;
            op.textContent = id.toString();
            inputId.appendChild(op);
        });
        inputId.name = "idProducto";
        celdaId.appendChild(inputId);

        // --------- Stock ---------
        let celdaStock = nuevaFila.insertCell();
        let inputStock = this.crearInput("stockProducto", "number","stockProductos");
        celdaStock.appendChild(inputStock);

        // --------- Proveedor ---------
        let celdaProveedor = nuevaFila.insertCell();
        let inputProveedor = this.crearInput("proovedor", "text","ProovedorProductos");
        celdaProveedor.appendChild(inputProveedor);

        // --------- Precio ---------
        let celdaPrecio = nuevaFila.insertCell();
        let inputPrecio = this.crearInput("precioProducto", "number","PrecioProductos");
        celdaPrecio.appendChild(inputPrecio);

        // --------- Fecha ---------
        let celdaFecha = nuevaFila.insertCell();
        let inputFecha = this.crearInput("fechaProducto", "date","FehcaDos");
        celdaFecha.appendChild(inputFecha);

        inputId.focus();
        
    }

    crearInput(nombre, tipo, segundaClase) {
        const input = document.createElement("input");
        input.name = nombre;
        input.type = tipo;
        input.className = "contentTable " + (segundaClase || "");

        if (tipo === "number") {
            input.min = "0";

            // ✅ Bloqueo de letras y caracteres no numéricos
            input.addEventListener("keydown", function (e) {
                const teclasPermitidas = [
                    "Backspace", "Delete", "Tab", "Escape", "Enter",
                    "ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown"
                ];
                if (
                        teclasPermitidas.includes(e.key) ||
                        (e.ctrlKey && ["a", "c", "x"].includes(e.key.toLowerCase()))
                        ) {
                    return;
                }
                if (!/^[0-9]$/.test(e.key)) {
                    e.preventDefault();
                }
            });

            input.addEventListener("paste", function (e) {
                const textoPegado = (e.clipboardData || window.clipboardData).getData("text");
                if (!/^\d+$/.test(textoPegado)) {
                    e.preventDefault();
                }
            });
        }
        
            this.tipoProductoBoton.forEach(element => {
                element.addEventListener("click", (e) => this.cambiarCategoria(e));
            });
            this.tipoProductoBoton[0].click();
        
        return input;
    }

}
document.addEventListener("DOMContentLoaded", () => {
    const gestor = new GestorProductos("tablaContenido", "categoria", "inputTipo", ".IdsProductos");
});