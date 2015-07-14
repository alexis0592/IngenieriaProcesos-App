package co.edu.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.ubicameudea.database.sqlite.dao.IUbicacionDAO;
import co.edu.ubicameudea.persistance.contract.BloqueContract;
import co.edu.ubicameudea.persistance.contract.UbicacionContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class UbicacionDAOImpl implements IUbicacionDAO {

    private static final String TAG = UbicacionDAOImpl.class.getSimpleName();
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;
    private static UbicacionDAOImpl instance = null;

    public UbicacionDAOImpl(Context context){
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized UbicacionDAOImpl getInstance(Context context){
        if (instance == null){
            instance = new UbicacionDAOImpl(context);
        }

        return instance;
    }

    @Override
    public List<ContentValues> findUbicationByBloqueAndOffice(String bloque, String numOffice) {
        Log.i(TAG, "findUbicationByBloqueAndOffice");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT %s, %s FROM %s, %s WHERE %s = %s AND %s == %s AND %s == %s",
                UbicacionContract.Column.LATITUD, UbicacionContract.Column.LONGITUD, UbicacionContract.TABLE_NAME,
                BloqueContract.TABLE_NAME, UbicacionContract.Column.ID_BLOQUE, BloqueContract.Column.ID_BLOQUE,
                BloqueContract.Column.NUMERO,bloque, UbicacionContract.Column.OFICINA, numOffice);

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String[]columns = new String[]{UbicacionContract.Column.LATITUD, UbicacionContract.Column.LONGITUD};
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    public List<ContentValues> findUbicacion(int idUnidad, int idDepartamento, int idBloque){
        Log.i(TAG, "findUbicationByBloqueAndOffice");

        SQLiteDatabase sqLiteDatabase = accessorSQLiteOpenHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM %s WHERE " +
                        "((%s = %s) OR (%s = %s)) AND " +
                        "((%s = %s) OR (%s = %s)) AND " +
                        "((%s = %s) OR (%s = %s))",
                UbicacionContract.TABLE_NAME,
                UbicacionContract.Column.ID_BLOQUE, -1, UbicacionContract.Column.ID_BLOQUE, idBloque,
                UbicacionContract.Column.ID_DEPARTAMENTO, -1, UbicacionContract.Column.ID_DEPARTAMENTO, idDepartamento,
                UbicacionContract.Column.ID_UNIDAD, -1, UbicacionContract.Column.ID_UNIDAD, idUnidad);

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String[]columns = new String[]{UbicacionContract.Column.LATITUD, UbicacionContract.Column.LONGITUD};
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }

    private List<ContentValues> cursorToContentValues(Cursor cursor, String[] columns){

        List<ContentValues> contentValuesList = new ArrayList<ContentValues>();

        if ((cursor == null) || (cursor.isClosed())) {

            return (contentValuesList);
        }

        ContentValues contentValues = null;
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            contentValues = new ContentValues();

            for (String column: columns){
                contentValues.put(column, cursor.getString(cursor.getColumnIndex(column)));
            }

            contentValuesList.add(contentValues);
            cursor.moveToNext();
        }

        return contentValuesList;
    }
}
