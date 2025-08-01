package Modelo;

public class Registro {

    private int idEmpleado;
    private String nombre;
    private String correo;
    private String contraseña;
    private boolean estado;
    private String estatus;
    private int idAdmin;

    public Registro(int idEmpleado, String nombre, String correo, String contraseña, boolean estado, String estatus, int idAdmin) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
        this.estatus = estatus;
        this.idAdmin = idAdmin;

    }
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }



  

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseñas() {
        return contraseña;
    }

    public void setIdProductosAlmacenamiento(String contraseña) {
        this.contraseña = contraseña;
    }

    
}
