package co.edu.ubicameudea.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import co.edu.ubicameudea.R;
import co.edu.ubicameudea.activities.AdvancedSearchActivity;
import co.edu.ubicameudea.activities.UdeaMapActivity;

import co.edu.ubicameudea.activities.UdeaMapActivity;
import co.edu.ubicameudea.database.sqlite.AccessorSQLiteOpenHelper;
import co.edu.ubicameudea.domain.process.impl.BloqueProcessImpl;
import co.edu.ubicameudea.domain.process.impl.UbicacionProcessImpl;
import co.edu.ubicameudea.model.dto.Bloque;
import co.edu.ubicameudea.model.dto.Ubicacion;


public class MainActivity extends ActionBarActivity {

    private ImageView imgMapButton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    public void initComponents(){
        BloqueProcessImpl bloqueProcess = new BloqueProcessImpl(this.getApplicationContext());
        this.imgMapButton = (ImageView)super.findViewById(R.id.goMapButton);
        this.editText = (EditText)super.findViewById(R.id.editTextsearch);
        this.editText.setTextColor(Color.parseColor("#FFFFFF"));
        List<Bloque> listBloque = bloqueProcess.findAllBloques();
        UbicacionProcessImpl ubicacionProcess = new UbicacionProcessImpl(this.getApplicationContext());
        Ubicacion u = ubicacionProcess.finUbicacionByBloqAndOffice("19", "201");
    }



    public void viewMapClick(View view){
        UdeaMapActivity mapActivity = new UdeaMapActivity();
        startActivity(new Intent(this, mapActivity.getClass()));
    }

    public void advancedSearchClick(View view){
        AdvancedSearchActivity advancedSearchActivity = new AdvancedSearchActivity();
        startActivity(new Intent(this, advancedSearchActivity.getClass()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
