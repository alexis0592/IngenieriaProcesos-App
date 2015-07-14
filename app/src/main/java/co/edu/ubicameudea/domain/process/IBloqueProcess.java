package co.edu.ubicameudea.domain.process;

import java.util.List;

import co.edu.ubicameudea.model.dto.Bloque;
import co.edu.ubicameudea.model.dto.Unidad;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IBloqueProcess {

    public List<Bloque> findAllBloques();

}
