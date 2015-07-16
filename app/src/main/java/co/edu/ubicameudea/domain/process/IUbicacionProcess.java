package co.edu.ubicameudea.domain.process;

import java.util.List;

import co.edu.ubicameudea.model.dto.TipoUnidad;
import co.edu.ubicameudea.model.dto.Ubicacion;
import co.edu.ubicameudea.model.dto.Unidad;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IUbicacionProcess {

    public Ubicacion finUbicacionByBloqAndOffice(int bloq, int office);

    public List<Ubicacion> findUbicacion(int idUnidad, int idDepartamento, int idBloque);

}
