package co.edu.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

import co.edu.ubicameudea.model.dto.Unidad;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public interface IUnidadDAO {

    public List<ContentValues> findUnidadesByTipo(int idTipo);

    public List<ContentValues> findAll();
}
