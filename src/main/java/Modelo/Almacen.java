
package Modelo;

public class Almacen {
    private int id_productos;
    private int unidades_stock;
    private float precio_producto ;
    private int id_producots_entrada;
    private String tipo_producto;
    private String nombre_producto;

    
    public Almacen(int id_producto, int stock, float precio_productos, int id_productos_entry, String tipo_product  , String nombre_product ){
        this.id_productos=id_producto;
        this.unidades_stock=stock;
        this.precio_producto=precio_productos;
        this.id_producots_entrada=id_productos_entry;
        this.tipo_producto = tipo_product;
        this.nombre_producto = nombre_product;

    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }

    public int getUnidades_stock() {
        return unidades_stock;
    }

    public void setUnidades_stock(int unidades_stock) {
        this.unidades_stock = unidades_stock;
    }

    public float getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(float precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getId_producots_entrada() {
        return id_producots_entrada;
    }

    public void setId_producots_entrada(int id_producots_entrada) {
        this.id_producots_entrada = id_producots_entrada;
    }
    

}
