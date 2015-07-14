package co.edu.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ubicameudea.database.sqlite.dao.IUbicacionDAO;
import co.edu.ubicameudea.database.sqlite.dao.impl.UbicacionDAOImpl;
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
        this.ubicacionDAO = UbicacionDAOImpl.getInstance(context);
    }

    @Override
    public Ubicacion finUbicacionByBloqAndOffice(String bloq, String office) {

        Ubicacion ubicacion = new Ubicacion();

        List<ContentValues> contentValuesList = this.ubicacionDAO.findUbicationByBloqueAndOffice(bloq, office);

        for(ContentValues contentValue:contentValuesList) {
            try {
                ubicacion = convertContentValueToUbicacion(contentValue);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return ubicacion;
    }

    public List<Ubicacion> findUbicacion(int idUnidad, int idDepartamento, int idBloque){
        List<Ubicacion> listUbicaciones = new ArrayList<Ubicacion>();

        try {
            List<ContentValues> contentValuesList = this.ubicacionDAO.findUbicacion(idUnidad, idDepartamento, idBloque);

            for(ContentValues contentValue:contentValuesList) {
                Ubicacion ubicacion = convertContentValueToUbicacion(contentValue);
                listUbicaciones.add(ubicacion);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listUbicaciones;
    }

    private Ubicacion convertContentValueToUbicacion(ContentValues contentValues) throws ParseException{
        Ubicacion ubicacion = new Ubicacion();

        ubicacion.setLatitud(Double.parseDouble(contentValues.getAsString(UbicacionContract.Column.LATITUD)));
        ubicacion.setLongitud(Double.parseDouble(contentValues.getAsString(UbicacionContract.Column.LONGITUD)));

        return ubicacion;
    }
}
