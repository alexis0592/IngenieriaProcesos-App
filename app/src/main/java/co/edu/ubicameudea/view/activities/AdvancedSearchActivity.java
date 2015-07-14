package co.edu.ubicameudea.view.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import co.edu.ubicameudea.R;
import co.edu.ubicameudea.domain.process.IBloqueProcess;
import co.edu.ubicameudea.domain.process.IDepartamentoProcess;
import co.edu.ubicameudea.domain.process.ITipoUnidadProcess;
import co.edu.ubicameudea.domain.process.IUnidadProcess;
import co.edu.ubicameudea.domain.process.impl.BloqueProcessImpl;
import co.edu.ubicameudea.domain.process.impl.DepartamentoProcessImpl;
import co.edu.ubicameudea.domain.process.impl.TipoUnidadProcessImpl;
import co.edu.ubicameudea.domain.process.impl.UnidadProcessImpl;
import co.edu.ubicameudea.model.dto.Bloque;
import co.edu.ubicameudea.model.dto.Departamento;
import co.edu.ubicameudea.model.dto.TipoUnidad;
import co.edu.ubicameudea.model.dto.Unidad;
import co.edu.ubicameudea.view.adapters.BloqueAdapter;
import co.edu.ubicameudea.view.adapters.DepartamentoAdapter;
import co.edu.ubicameudea.view.adapters.TipoUnidadAdapter;
import co.edu.ubicameudea.view.adapters.UnidadAdapter;

public class AdvancedSearchActivity extends ActionBarActivity {

    private Spinner spnTipoUnidad;
    private Spinner spnUnidades;
    private Spinner spnDepartamentos;
    private Spinner spnBloques;
    private ITipoUnidadProcess tipoUnidadProcess;
    private IDepartamentoProcess departamentoProcess;
    private IBloqueProcess bloqueProcess;
    private IUnidadProcess unidadProcess;
    private TipoUnidadAdapter tipoUnidadAdapter;
    private BloqueAdapter bloqueAdapter;
    private UnidadAdapter unidadAdapter;
    private DepartamentoAdapter departamentoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        tipoUnidadProcess = new TipoUnidadProcessImpl(this);
        departamentoProcess = new DepartamentoProcessImpl(this);
        bloqueProcess = new BloqueProcessImpl(this);
        unidadProcess = new UnidadProcessImpl(this);

        initComponents();
    }

    public void initComponents() {
        this.spnTipoUnidad = (Spinner) super.findViewById(R.id.advanced_search_spnTipoUnidad);
        this.spnUnidades = (Spinner) super.findViewById(R.id.advanced_search_spnUnidades);
        this.spnDepartamentos = (Spinner) super.findViewById(R.id.advanced_search_spnDepartamentos);
        this.spnBloques = (Spinner) super.findViewById(R.id.advanced_search_spnBloques);

        List<TipoUnidad> tiposUnidad = tipoUnidadProcess.findAll();

        tipoUnidadAdapter = new TipoUnidadAdapter(getBaseContext(),
                R.layout.layout_item_tipo_unidad, tiposUnidad);
        spnTipoUnidad.setAdapter(tipoUnidadAdapter);

        spnTipoUnidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                Integer idTipoUnidad = Integer.parseInt(((TextView) v.findViewById(R.id.item_tipo_unidad_txvId)).getText().toString());
                if (!idTipoUnidad.equals(null)) {
                    List<Unidad> unidades = unidadProcess.findUnidadesByTipo(idTipoUnidad);
                    unidadAdapter = new UnidadAdapter(getBaseContext(), R.layout.layout_item_unidad, unidades);
                    spnUnidades.setAdapter(unidadAdapter);
                    spnUnidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Integer idUnidad = Integer.parseInt(((TextView) view.findViewById(R.id.item_unidad_txvId)).getText().toString());
                            if (!idUnidad.equals(null)) {
                                List<Departamento> departamentos = departamentoProcess.findByIdUnidad(idUnidad);
                                departamentoAdapter = new DepartamentoAdapter(getBaseContext(), R.layout.layout_item_departamento, departamentos);
                                spnDepartamentos.setAdapter(departamentoAdapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        List<Bloque> bloques = bloqueProcess.findAllBloques();

        bloqueAdapter = new BloqueAdapter(getBaseContext(),
                R.layout.layout_item_bloque, bloques);
        spnBloques.setAdapter(bloqueAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced_search, menu);
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
