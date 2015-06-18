package co.edu.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Departamento {

    private int departamentoId;
    private String nombre;
    private Unidad unidad;

    private Departamento(){
        super();
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
