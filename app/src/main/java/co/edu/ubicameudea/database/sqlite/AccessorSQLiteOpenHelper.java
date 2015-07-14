package co.edu.ubicameudea.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import co.edu.ubicameudea.persistance.contract.BloqueContract;
import co.edu.ubicameudea.persistance.contract.DepartamentoContract;
import co.edu.ubicameudea.persistance.contract.PersistanceContract;
import co.edu.ubicameudea.persistance.contract.TipoUnidadContract;
import co.edu.ubicameudea.persistance.contract.UbicacionContract;
import co.edu.ubicameudea.persistance.contract.UnidadContract;

/**
 * Created by Alexis on 15/06/15.
 */
public class AccessorSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TAG = AccessorSQLiteOpenHelper.class.getSimpleName();

    public AccessorSQLiteOpenHelper(Context context) {
        super(context, PersistanceContract.DATABASE_NAME, null, PersistanceContract.DATABASE_VERSION);
        Log.d(TAG, "Se creo la BD");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bloqueTableSQLCreator = String.format("CREATE TABLE %s(%s INTEGER NOT NULL," +
                        " %s TEXT PRIMARY KEY)",
                BloqueContract.TABLE_NAME,
                BloqueContract.Column.ID_BLOQUE,
                BloqueContract.Column.NUMERO);

        String  tipoUnidadTableSQLCreator = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER NOT NULL," +
                        " %s TEXT NOT NULL, PRIMARY KEY(%s))",
                TipoUnidadContract.TABLE_NAME,
                TipoUnidadContract.Column.ID_TIPO_UNIDAD,
                TipoUnidadContract.Column.NOMBRE,
               TipoUnidadContract.Column.ID_TIPO_UNIDAD);

        String unidadTableSQLCreator = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER NOT NULL," +
                " %s TEXT NOT NULL, %s INTEGER NOT NULL, PRIMARY KEY(%s), FOREIGN KEY(%s) REFERENCES %s(%s))",
                UnidadContract.TABLE_NAME,
                UnidadContract.Column.ID_UNIDAD,
                UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD,
                UnidadContract.Column.ID_UNIDAD,
                UnidadContract.Column.ID_TIPO_UNIDAD,
                TipoUnidadContract.TABLE_NAME,
                TipoUnidadContract.Column.ID_TIPO_UNIDAD);

        String departamentoTableSQLCreator = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER NOT NULL, " +
                "%s TEXT NOT NULL, %s INTEGER NOT NULL, PRIMARY KEY(%s), FOREIGN KEY(%s) REFERENCES %s(%s))",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_DEPARTAMENTO,
                DepartamentoContract.Column.NOMBRE, DepartamentoContract.Column.ID_UNIDAD,
                DepartamentoContract.Column.ID_DEPARTAMENTO, DepartamentoContract.Column.ID_UNIDAD,
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD);

        String ubicacionTableSQLCreator = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER " +
                "NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s REAL NOT NULL, %s REAL NOT NULL, " +
                "%s INTEGER NOT NULL, %s INTEGER NOT NULL, PRIMARY KEY(%s), FOREIGN KEY(%s) REFERENCES %s(%s), " +
                "FOREIGN KEY(%s) REFERENCES %s(%s), FOREIGN KEY(%s) REFERENCES %s(%s))",
                UbicacionContract.TABLE_NAME, UbicacionContract.Column.ID_UBICACION, UbicacionContract.Column.ID_BLOQUE,
                UbicacionContract.Column.OFICINA, UbicacionContract.Column.LATITUD, UbicacionContract.Column.LONGITUD,
                UbicacionContract.Column.ID_DEPARTAMENTO, UbicacionContract.Column.ID_UNIDAD, UbicacionContract.Column.ID_UBICACION,
                UbicacionContract.Column.ID_BLOQUE, BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, UbicacionContract.Column.ID_DEPARTAMENTO,
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_DEPARTAMENTO, UbicacionContract.Column.ID_UNIDAD,
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD);


        Log.d(TAG, "Se creo la tabla BLOQUE");
        Log.d(TAG, "Se creo la tabla TIPO_UNIDAD");
        Log.d(TAG, "Se creo la tabla UNIDAD");
        Log.d(TAG, "Se creo la tabla DEPARTAMENTO");

        db.execSQL(bloqueTableSQLCreator);
        db.execSQL(tipoUnidadTableSQLCreator);
        db.execSQL(unidadTableSQLCreator);
        db.execSQL(departamentoTableSQLCreator);
        db.execSQL(ubicacionTableSQLCreator);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF NOT EXISTS %s", BloqueContract.TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF NOT EXISTS %s", TipoUnidadContract.TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF NOT EXISTS %s", UnidadContract.TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF NOT EXISTS %s", DepartamentoContract.TABLE_NAME));

        this.onCreate(db);
    }
}
