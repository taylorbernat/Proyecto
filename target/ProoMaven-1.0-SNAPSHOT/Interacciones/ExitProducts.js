class GestorProductos {
    constructor(tablaId, tipoClase, inputTipoId, selectId, NombresProductos) {
        this.tabla = document.getElementById(tablaId);
        this.cuerpoTabla = this.tabla.querySelector("tbody");
        this.tipoProductoBoton = document.querySelectorAll("." + tipoClase);
        this.inforTipo = document.getElementById(inputTipoId);
        this.selectSelector = selectId;
        this.selectSelectorNombres = NombresProductos;

        this.productosPorCategoria = {
            Bolsos: [1002, 1012, 1032],
            Carteras: [2002, 2012, 2032],
            Zapatos: [3002, 3012, 3032]
        };

        this.productosPorProveedor = {
            Bolsos: ["Amphora Bolso Monique", "Studio F Bolso Mini Croco", "Totto Morral Crayola"],
            Carteras: ["Studio F Cartera Kira Chain", "Vélez Cartera Crossbody Línea Nature", "Totto Cartera Urbana Aurora"],
            Zapatos: ["Totto Tenis Urban Style One", "Vélez Botines Urbanos Hombre", "Gef Tenis Mujer Básico Beige"]
        };

        this.inicializarEventos();
    }

    inicializarEventos() {
        document.querySelectorAll(".stockProductos").forEach(input => {
            this.permitirSoloNumeros(input);
        });

        this.tipoProductoBoton.forEach(element => {
            element.addEventListener("click", (e) => this.cambiarCategoria(e));
        });

        this.tipoProductoBoton[0].click();

        this.tabla.addEventListener("keydown", (e) => this.manejarTeclasTabla(e));
    }

    permitirSoloNumeros(input) {
        input.addEventListener("keydown", function (e) {
            const teclasPermitidas = ["Backspace", "Delete", "Tab", "Escape", "Enter", "ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown"];
            if (
                teclasPermitidas.includes(e.key) ||
                (e.ctrlKey && ["a", "c", "v", "x"].includes(e.key.toLowerCase()))
            ) return;

            if (!/^[0-9]$/.test(e.key)) e.preventDefault();
        });
    }

    cambiarCategoria(e) {
        this.tipoProductoBoton.forEach(b => b.classList.remove("activa"));
        e.currentTarget.classList.add("activa");

        const categoria = e.target.value;
        this.inforTipo.value = categoria;

        const productos = this.productosPorCategoria[categoria] || [];
        const Proovedores = this.productosPorProveedor[categoria] || [];

        const selects = document.querySelectorAll(this.selectSelector);
        const selectsNombres = document.querySelectorAll(this.selectSelectorNombres);
        
        selects.forEach(select => {
            select.innerHTML = "";
            productos.forEach(id => {
                const op = document.createElement("option");
                op.value = id;
                op.textContent = id.toString();
                select.appendChild(op);
            });
        });

        selectsNombres.forEach(select => {
            select.innerHTML = "";
            Proovedores.forEach(nombre => {
                const op = document.createElement("option");
                op.value = nombre;
                op.textContent = nombre;
                select.appendChild(op);
            });
        });

        const filas = Array.from(this.tabla.rows).slice(1);
        filas.forEach(fila => {
            const celdaId = fila.cells[0];
            const celdaProveedor = fila.cells[2];

            const selectId = celdaId.querySelector("select");
            const selectNombre = celdaProveedor.querySelector("select");

            const valoresIds = productos.map(id => id.toString());
            const valoresNombres = Proovedores.map(n => n.toString());

            const actualizarDesdeId = () => {
                const index = valoresIds.indexOf(selectId.value);
                if (index !== -1) {
                    selectNombre.value = Proovedores[index];
                } else {
                    selectNombre.value = "";
                }
            };

            const actualizarDesdeNombre = () => {
                const index = valoresNombres.indexOf(selectNombre.value);
                if (index !== -1) {
                    selectId.value = productos[index];
                } else {
                    selectId.value = "";
                }
            };

            actualizarDesdeId();
            selectId.addEventListener("change", actualizarDesdeId);
            selectNombre.addEventListener("change", actualizarDesdeNombre);
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
        const nombres = this.productosPorProveedor[this.inforTipo.value] || [];

        let celdaId = nuevaFila.insertCell();
        let selectId = document.createElement("select");
        selectId.className = "contentTable IdsProductos";
        productos.forEach(id => {
            const op = document.createElement("option");
            op.value = id;
            op.textContent = id.toString();
            selectId.appendChild(op);
        });
        selectId.name = "idProducto";
        celdaId.appendChild(selectId);

        let celdaStock = nuevaFila.insertCell();
        let inputStock = this.crearInput("stockProducto", "number", "stockProductos");
        celdaStock.appendChild(inputStock);

        let celdaProveedor = nuevaFila.insertCell();
        let selectNombre = document.createElement("select");
        selectNombre.className = "contentTable NombresProductos";
        selectNombre.name = "NombreProducto";
        nombres.forEach(nombre => {
            const op = document.createElement("option");
            op.value = nombre;
            op.textContent = nombre;
            selectNombre.appendChild(op);
        });
        celdaProveedor.appendChild(selectNombre);

        let celdaFecha = nuevaFila.insertCell();
        let inputFecha = this.crearInput("fechaProducto", "date", "FehcaDos");
        celdaFecha.appendChild(inputFecha);

        selectId.focus();
    }

    crearInput(nombre, tipo, segundaClase) {
        const input = document.createElement("input");
        input.name = nombre;
        input.type = tipo;
        input.className = "contentTable " + (segundaClase || "");

        if (tipo === "number") {
            input.min = "0";

            input.addEventListener("keydown", function (e) {
                const teclasPermitidas = ["Backspace", "Delete", "Tab", "Escape", "Enter", "ArrowLeft", "ArrowRight", "ArrowUp", "ArrowDown"];
                if (
                    teclasPermitidas.includes(e.key) ||
                    (e.ctrlKey && ["a", "c", "x"].includes(e.key.toLowerCase()))
                ) return;
                if (!/^[0-9]$/.test(e.key)) e.preventDefault();
            });

            input.addEventListener("paste", function (e) {
                const textoPegado = (e.clipboardData || window.clipboardData).getData("text");
                if (!/^\d+$/.test(textoPegado)) e.preventDefault();
            });
        }

        return input;
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const gestor = new GestorProductos("tablaContenido", "categoria", "inputTipo", ".IdsProductos", ".NombresProductos");
});
