package co.edu.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class TipoUnidad {

    private int idTipoUnidad;
    private String nombre;

    public TipoUnidad(){
        super();
    }

    public int getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(int idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
