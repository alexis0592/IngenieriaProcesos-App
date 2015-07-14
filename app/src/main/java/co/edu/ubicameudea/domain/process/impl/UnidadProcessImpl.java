package co.edu.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ubicameudea.database.sqlite.dao.IUnidadDAO;
import co.edu.ubicameudea.database.sqlite.dao.impl.UnidadDAOImpl;
import co.edu.ubicameudea.domain.process.IUnidadProcess;
import co.edu.ubicameudea.model.dto.Unidad;
import co.edu.ubicameudea.persistance.contract.UnidadContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class UnidadProcessImpl implements IUnidadProcess{

    private static final String TAG = UnidadProcessImpl.class.getSimpleName();
    private IUnidadDAO tipoUnidadDAO;

    public UnidadProcessImpl(Context context) {
        super();
        this.tipoUnidadDAO = UnidadDAOImpl.getInstance(context);
    }

    @Override
    public List<Unidad> findUnidadesByTipo(int idTipo){
        Log.i(TAG, "findUnidadesByTipo");

        List<Unidad> list = new ArrayList<Unidad>();
        try {

            List<ContentValues> contentValuesList = this.tipoUnidadDAO.findUnidadesByTipo(idTipo);

            for (ContentValues contentValue : contentValuesList) {
                list.add(this.convertContentValueToDto(contentValue));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }

    private Unidad convertContentValueToDto(ContentValues contentValues) throws ParseException {
        Unidad unidad = new Unidad();

        unidad.setIdUnidad(Integer.parseInt(contentValues.getAsString(UnidadContract.Column.ID_TIPO_UNIDAD)));
        unidad.setNombre(contentValues.getAsString(UnidadContract.Column.NOMBRE));

        return unidad;
    }
}
