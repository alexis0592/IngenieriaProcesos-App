package co.edu.ubicameudea.domain.process.impl;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ubicameudea.database.sqlite.dao.IDepartamentoDAO;
import co.edu.ubicameudea.database.sqlite.dao.impl.DepartamentoDAOImpl;
import co.edu.ubicameudea.domain.process.IDepartamentoProcess;
import co.edu.ubicameudea.model.dto.Departamento;
import co.edu.ubicameudea.persistance.contract.DepartamentoContract;

/**
 * Created by CristianCamilo on 13/07/2015.
 */
public class DepartamentoProcessImpl implements IDepartamentoProcess {

    private static final String TAG = DepartamentoProcessImpl.class.getSimpleName();
    private IDepartamentoDAO departamentoDAO;

    public DepartamentoProcessImpl(Context context) {
        super();
        this.departamentoDAO = DepartamentoDAOImpl.getInstance(context);
    }

    @Override
    public List<Departamento> findAll() {
        Log.i(TAG, "findAll");

        List<Departamento> list = new ArrayList<Departamento>();
        try {

            List<ContentValues> contentValuesList = this.departamentoDAO.findAll();

            for (ContentValues contentValue : contentValuesList) {
                list.add(this.convertContentValueToDto(contentValue));
            }

        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Departamento> findByIdUnidad(int idUnidad){
        Log.i(TAG, "findByIdUnidad");

        List<Departamento> list = new ArrayList<Departamento>();
        try {

            List<ContentValues> contentValuesList = this.departamentoDAO.findByIdUnidad(idUnidad);

            for (ContentValues contentValue : contentValuesList) {
                list.add(this.convertContentValueToDto(contentValue));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return list;
    }

    private Departamento convertContentValueToDto(ContentValues contentValues) throws ParseException {
        Departamento departamento = new Departamento();

        departamento.setDepartamentoId(Integer.parseInt(contentValues.getAsString(DepartamentoContract.Column.ID_DEPARTAMENTO)));
        departamento.setNombre(contentValues.getAsString(DepartamentoContract.Column.NOMBRE));

        return departamento;
    }
}
