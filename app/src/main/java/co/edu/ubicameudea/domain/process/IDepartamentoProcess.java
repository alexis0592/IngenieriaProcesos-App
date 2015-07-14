package co.edu.ubicameudea.domain.process;

import java.util.List;

import co.edu.ubicameudea.model.dto.Departamento;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface IDepartamentoProcess {

    public List<Departamento> findAll();

    public List<Departamento> findByIdUnidad(int idUnidad);
}
