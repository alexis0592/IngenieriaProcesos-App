package co.edu.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.edu.ubicameudea.database.sqlite.dao.IUbicacionDAO;
import co.edu.ubicameudea.database.sqlite.dao.impl.UbicacionDAO;
import co.edu.ubicameudea.domain.process.IUbicacionProcess;
import co.edu.ubicameudea.model.dto.Ubicacion;
import co.edu.ubicameudea.persistance.contract.UbicacionContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class UbicacionProcessImpl implements IUbicacionProcess {

    private static final String TAG = UbicacionProcessImpl.class.getSimpleName();
    private IUbicacionDAO ubicacionDAO;

    public UbicacionProcessImpl(Context context) {
        super();
        this.ubicacionDAO = UbicacionDAO.getInstance(context);
    }

    @Override
    public Ubicacion finUbicacionByBloqAndOffice(String bloq, String office) {

        List<Ubicacion> ubicacionList = new ArrayList<Ubicacion>();

        List<ContentValues> contentValuesList = this.ubicacionDAO.findUbicationByBloqueAndOffice(bloq, office);

        for (ContentValues contentValues : contentValuesList) {

        }

        return null;
    }

    private Ubicacion convertContentValueToUbicacion(ContentValues contentValues) {
        Ubicacion ubicacion = new Ubicacion();

        return null;
    }
}
