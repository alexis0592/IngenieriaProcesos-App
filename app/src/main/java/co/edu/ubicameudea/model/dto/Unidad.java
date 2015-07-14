package co.edu.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Unidad {

    private int idUnidad;
    private String nombre;
    private TipoUnidad tipoUnidad;

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUnidad getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(TipoUnidad tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
}
