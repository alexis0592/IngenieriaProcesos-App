package co.edu.ubicameudea.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import co.edu.ubicameudea.model.dto.Departamento;
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
                        " %s TEXT NOT NULL, PRIMARY KEY(%s))",
                BloqueContract.TABLE_NAME,
                BloqueContract.Column.ID_BLOQUE,
                BloqueContract.Column.NUMERO,
                BloqueContract.Column.ID_BLOQUE);

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

        LlenarBaseDatos(db);
    }

    private void LlenarBaseDatos(SQLiteDatabase db) {
        insertarBloques(db);
        Log.d(TAG, "Se llenaron los bloques");
        insertarTiposUnidad(db);
        Log.d(TAG, "Se llenaron los tipos de unidad");
        insertarUnidades(db);
        Log.d(TAG, "Se llenaron las unidades");
        insertarDepartamentos(db);
        Log.d(TAG, "Se llenaron los departamentos");
        insertarUbicaciones(db);
        Log.d(TAG, "Se llenaron las ubicaciones");
    }

    private void insertarUbicaciones(SQLiteDatabase db) {
        //INGENIER�A DE SISTEMAS 10
        db.execSQL(String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (%s,%s,%s,%s,%s,%s,%s)",
                UbicacionContract.TABLE_NAME,
                UbicacionContract.Column.ID_UBICACION,
                UbicacionContract.Column.ID_BLOQUE,
                UbicacionContract.Column.OFICINA,
                UbicacionContract.Column.LATITUD,
                UbicacionContract.Column.LONGITUD,
                UbicacionContract.Column.ID_DEPARTAMENTO,
                UbicacionContract.Column.ID_UNIDAD,
                1, 19, "'201'", 6.268356, -75.567402, 10, 23));


        db.execSQL(String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (%s,%s,%s,%s,%s,%s,%s)",
                UbicacionContract.TABLE_NAME,
                UbicacionContract.Column.ID_UBICACION,
                UbicacionContract.Column.ID_BLOQUE,
                UbicacionContract.Column.OFICINA,
                UbicacionContract.Column.LATITUD,
                UbicacionContract.Column.LONGITUD,
                UbicacionContract.Column.ID_DEPARTAMENTO,
                UbicacionContract.Column.ID_UNIDAD,
                2, 18, "'325'", 6.267816, -75.567625, 10, 23));

        db.execSQL(String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (%s,%s,%s,%s,%s,%s,%s)",
                UbicacionContract.TABLE_NAME,
                UbicacionContract.Column.ID_UBICACION,
                UbicacionContract.Column.ID_BLOQUE,
                UbicacionContract.Column.OFICINA,
                UbicacionContract.Column.LATITUD,
                UbicacionContract.Column.LONGITUD,
                UbicacionContract.Column.ID_DEPARTAMENTO,
                UbicacionContract.Column.ID_UNIDAD,
                3, 20, "'234'", 6.268320, -75.567872, 10, 23));

        db.execSQL(String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES (%s,%s,%s,%s,%s,%s,%s)",
                UbicacionContract.TABLE_NAME,
                UbicacionContract.Column.ID_UBICACION,
                UbicacionContract.Column.ID_BLOQUE,
                UbicacionContract.Column.OFICINA,
                UbicacionContract.Column.LATITUD,
                UbicacionContract.Column.LONGITUD,
                UbicacionContract.Column.ID_DEPARTAMENTO,
                UbicacionContract.Column.ID_UNIDAD,
                4, 21, "'218'", 6.268104, -75.568049, 10, 23));
    }

    private void insertarDepartamentos(SQLiteDatabase db) {
        //UNIDADES ADMINISTRATIVAS:
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 1, "'Escuela de Ingenier�a El�ctrica, Electr�nica, Telecomunicaciones y TI'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 2, "'Escuela Ambiental'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 3, "'Departamento de Ingenier�a Industrial'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 4, "'Departamento de Ingenier�a Metal�rgica'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 5, "'Departamento de Ingenier�a Qu�mica'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 6, "'Departamento de Ingenier�a Sanitaria y Ambiental'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 7, "'Departamento de Ingenier�a Industrial'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 8, "'Departamento de Ingenier�a Mec�nica'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 9, "'Departamento de Ingenier�a El�ctrica'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 10, "'Departamento de Ingenier�a de Sistemas'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 11, "'Departamento de Ingenier�a Electr�nica'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 12, "'Departamento de Recursos de Apoyo Inform�ticos - DRAI'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 13, "'Centro de Investigaciones Ambientales e Ingenier�a'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 14, "'Centro de Extensi�n Acad�mica'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 15, "'Departamento de Ingenier�a Industrial'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 16, "'Programa de Bioingenier�a'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 17, "'Programa de Ingenier�a Civ�l'", 23));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                DepartamentoContract.TABLE_NAME, DepartamentoContract.Column.ID_UNIDAD, DepartamentoContract.Column.NOMBRE,
                DepartamentoContract.Column.ID_UNIDAD, 18, "'Programa de Ingenier�a de Telecomunicaciones'", 23));
    }

    private void insertarUnidades(SQLiteDatabase db) {

        //UNIDADES ADMINISTRATIVAS:
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 1, "'Vicerector�a General'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 2, "'Secretar�a General'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 3, "'Vicerector�a de Investigaci�n'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 4, "'Vicerector�a de Docencia'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 5, "'Vicerector�a de Extensi�n'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 6, "'Vicerector�a Administrativa'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 7, "'Direcci�n de Desarrollo Institucional'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 8, "'Direcci�n de Bienestar Universitario'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 9, "'Direcci�n de Regionalizaci�n'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 10, "'Direcci�n de Postgrados'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 11, "'Direcci�n de Relaciones Internacionales'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 12, "'Direcci�n de Gesti�n del Financiamiento'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 13, "'Direcci�n de Gesti�n Log�stica e Infraestructura'", 2));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 14, "'Oficina de Asesor�a Jur�dica'", 2));

        //OTROS:
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 41, "'Departamento de Bibliotecas'", 3));

        //UNIDADES ACAD�MICAS:
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 15, "'Facultad de Artes'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 16, "'Facultad de Ciencias Agrarias'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 17, "'Facultad de Ciencias Econ�micas'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 18, "'Facultad de Ciencias Exactas y Naturales'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 19, "'Facultad de Ciencias Sociales y Humanas'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 20, "'Facultad de Comunicaciones'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 21, "'Facultad de Derecho y Ciencias Pol�ticas'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 22, "'Facultad de Educaci�n'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 23, "'Facultad de Ingenier�a'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 24, "'Facultad de Enfermer�a'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 25, "'Facultad de Medicina'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 26, "'Facultad de Odontolog�a'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 27, "'Facultad de Qu�mica Farmac�utica'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 28, "'Facultad de Salud P�blica'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 29, "'Escuela de Idiomas'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 30, "'Escuela Interamericana de Bibliotecolog�a'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 31, "'Escuela de Microbiolog�a'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 32, "'Escuela de Nutrici�n y Diet�tica'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 33, "'Instituto de Educaci�n F�sica y Deportes'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 34, "'Instituto de Estudios Pol�ticos'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 35, "'Instituto de Estudios Regionale'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 36, "'Instituto de Filosof�a'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 37, "'Corporaci�n Ambiental'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 38, "'Corporaci�n de Ciencias B�sicas'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 39, "'Corporaci�n de Biom�dicas'", 1));
        db.execSQL(String.format("INSERT INTO %s (%s,%s, %s) VALUES (%s,%s,%s)",
                UnidadContract.TABLE_NAME, UnidadContract.Column.ID_UNIDAD, UnidadContract.Column.NOMBRE,
                UnidadContract.Column.ID_TIPO_UNIDAD, 40, "'Corporaci�n de Patolog�as Tropicales'", 1));
    }

    private void insertarTiposUnidad(SQLiteDatabase db) {
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                TipoUnidadContract.TABLE_NAME, TipoUnidadContract.Column.ID_TIPO_UNIDAD, TipoUnidadContract.Column.NOMBRE, 1, "'Acad�micas'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                TipoUnidadContract.TABLE_NAME, TipoUnidadContract.Column.ID_TIPO_UNIDAD, TipoUnidadContract.Column.NOMBRE, 2, "'Administrativas'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                TipoUnidadContract.TABLE_NAME, TipoUnidadContract.Column.ID_TIPO_UNIDAD, TipoUnidadContract.Column.NOMBRE, 3, "'Otras'"));
    }

    private void insertarBloques(SQLiteDatabase db) {
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 1, "'Bloque 1'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 2, "'Bloque 2'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 3, "'Bloque 3'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 4, "'Bloque 4'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 5, "'Bloque 5'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 6, "'Bloque 6'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 7, "'Bloque 7'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 8, "'Bloque 8'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 9, "'Bloque 9'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 10, "'Bloque 10'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 11, "'Bloque 11'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 12, "'Bloque 12'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 13, "'Bloque 13'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 14, "'Bloque 14'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 15, "'Bloque 15'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 16, "'Bloque 16'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 17, "'Bloque 17'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 18, "'Bloque 18'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 19, "'Bloque 19'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 20, "'Bloque 20'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 21, "'Bloque 21'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 22, "'Bloque 22'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 23, "'Bloque 23'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 24, "'Bloque 24'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 25, "'Bloque 25'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 26, "'Bloque 26'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 27, "'Bloque 27'"));
        db.execSQL(String.format("INSERT INTO %s (%s,%s) VALUES (%s,%s)",
                BloqueContract.TABLE_NAME, BloqueContract.Column.ID_BLOQUE, BloqueContract.Column.NUMERO, 28, "'Bloque 28'"));
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
