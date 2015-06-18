package co.edu.ubicameudea.database.sqlite.dao;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by Alexis on 18/06/15.
 */
public interface IUbicacionDAO {

    public List<ContentValues> findUbicationByBloqueAndOffice(String bloque, String numOffice);
}
