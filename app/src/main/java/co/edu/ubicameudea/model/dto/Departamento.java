package co.edu.ubicameudea.model.dto;

/**
 * Created by Alexis on 18/06/15.
 */
public class Departamento {

    private int idDepartamento;
    private String nombre;
    private Unidad unidad;

    public Departamento(){
        super();
    }

    public int getDepartamentoId() {
        return idDepartamento;
    }

    public void setDepartamentoId(int departamentoId) {
        this.idDepartamento = departamentoId;
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
