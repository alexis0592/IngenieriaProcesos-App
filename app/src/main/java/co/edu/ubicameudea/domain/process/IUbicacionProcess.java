package co.edu.ubicameudea.domain.process;

import co.edu.ubicameudea.model.dto.Ubicacion;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IUbicacionProcess {

    public Ubicacion finUbicacionByBloqAndOffice(String bloq, String office);

}
