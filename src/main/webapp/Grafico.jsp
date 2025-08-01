<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gráfico de Barras con Chart.js</title>
    <link rel="stylesheet" type="text/css" href="Styles/StyleGraficos.css">
    <link href="https://fonts.googleapis.com/css2?family=Bungee+Inline&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                    </ul>
                </form>
            </nav>
        </aside>

        <main id="contenidoprincipal">
            <div class="ordenar">
                <button class="ordenar-btn">ORDENAR POR ▶</button>
                <input type="text" class="ordenar-input" placeholder="Buscar..." />
            </div>

            <h2>Ventas por Mes (Ejemplo con Chart.js)</h2>
            <div class="grafico-contenedor">
                <canvas id="graficoBarras"></canvas>
            </div>
        </main>
        
        
    </div>
    <script>
        const ctx = document.getElementById('graficoBarras').getContext('2d');

        const datos = {
            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Diciembre'],
            datasets: [{
                label: 'Ventas en USD',
                data: [500, 700, 400, 850, 100],
                backgroundColor: [
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(10, 32, 235, 0.5)'
                ],
                borderColor: [
                    'rgba(75, 192, 192, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(10, 40, 235, 1)'
                ],
                borderWidth: 1
            }]
        };

        const opciones = {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };

        new Chart(ctx, {
            type: 'bar',
            data: datos,
            options: opciones
        });
    </script>
</body>
</html>