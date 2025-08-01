
package Modelo;

import java.util.Date;

public class EntradaProducto {
    private int idProducto;
    private int cantidadProducto;
    private Date fechaEntrega;
    private String Proovedor;
    private String Movimiento;
    private int precioProducto;
    private String tipoProducto;


    


    public EntradaProducto(int idProduct, int cantProduct, Date fechaProduct, String proovedorProduct , String MovimientoProduct, int precioProduct, String tipoProduct) {
        this.idProducto = idProduct;
        this.cantidadProducto = cantProduct;
        this.fechaEntrega = fechaProduct;
        this.Proovedor = proovedorProduct;
        this.Movimiento = MovimientoProduct;
        this.precioProducto = precioProduct;
        this.tipoProducto = tipoProduct;



    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

  
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProduct) {
        this.idProducto = idProduct;
    }

    
    
    
    public int getCantProduct() {
        return cantidadProducto;
    }

    public void setCantProduct(int cantProduct) {
        this.cantidadProducto = cantProduct;
    }
    
    
    

    public Date getFechaProduct() {
        return fechaEntrega;
    }

    public void setFechaProduct(Date fechaProduct) {
        this.fechaEntrega = fechaProduct;
    }
    public String getProovedor() {
        return Proovedor;
    }

    public void setProovedor(String Proovedor) {
        this.Proovedor = Proovedor;
    }

    public String getMovimiento() {
        return Movimiento;
    }

    public void setMovimiento(String Movimiento) {
        this.Movimiento = Movimiento;
    }
    
    

   
}