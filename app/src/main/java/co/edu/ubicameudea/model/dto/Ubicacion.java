package co.edu.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Ubicacion {

    private int ubicacionId;
    private Bloque bloqueId;
    private String oficina;
    private double latitud;
    private double longitud;
    private Departamento departamento;
    private Unidad unidad;

    public Ubicacion(){
        super();
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Bloque getBloqueId() {
        return bloqueId;
    }

    public void setBloqueId(Bloque bloqueId) {
        this.bloqueId = bloqueId;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
}
