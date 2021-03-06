package co.edu.ubicameudea.database.sqlite.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.ubicameudea.database.sqlite.dao.IBloqueDAO;
import co.edu.ubicameudea.persistance.contract.BloqueContract;

/**
 * Created by Alexis on 18/06/15.
 */
public class BloqueDAOImpl implements IBloqueDAO {

    public static final String TAG = BloqueDAOImpl.class.getSimpleName();

    private static BloqueDAOImpl instance = null;
    private AccessorSQLiteOpenHelper accessorSQLiteOpenHelper;

    private BloqueDAOImpl(Context context) {
        super();
        this.accessorSQLiteOpenHelper = new AccessorSQLiteOpenHelper(context);
    }

    public static synchronized BloqueDAOImpl getInstance(Context context) {
        if (instance == null) {
            instance = new BloqueDAOImpl(context);
        }

        return instance;
    }

    @Override
    public List<ContentValues> findAllBloque(Boolean distinct, String[] columns,
                                             String selection, String[] selectionArgs, String groupBy,
                                             String having, String orderBy, String limit) {
        Log.i(TAG,
                "finAllBloques(Boolean, String[], String, String[], String, String, String, String):List<ContentValues>");
        SQLiteDatabase sqLiteDatabase = this.accessorSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(distinct.booleanValue(), BloqueContract.TABLE_NAME, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
        List<ContentValues> contentValuesList = this.cursorToContentValues(cursor, columns);

        cursor.close();

        return contentValuesList;
    }


    private List<ContentValues> cursorToContentValues(Cursor cursor, String[] columns){
        List<ContentValues> contentValuesList = new ArrayList<ContentValues>();

        if ((cursor == null) || (cursor.isClosed())) {

            return (contentValuesList);
        }

        if (columns == null){
            columns = BloqueContract.Column.getAllColumns();
        }

        ContentValues contentValues = null;
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            contentValues = new ContentValues();

            for(String column : columns){
                contentValues.put(column, cursor.getString(cursor.getColumnIndex(column)));
            }

            contentValuesList.add(contentValues);
            cursor.moveToNext();
        }

        return contentValuesList;
    }
}
