
package br.com.newidea.curso;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListarCursoActivity extends AppCompatActivity {
    public static final String ID = "br.com.newidea.curso.id";
    public static final String NOME = "br.com.newidea.curso.nome";
    public static final String DETALHE = "br.com.newidea.curso.detalhe";
    public static final String FOTO = "br.com.newidea.curso.foto";

    Activity atividade;
    ArrayList<Curso> lista;
    CursoRequester requester = new CursoRequester();
    String chave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_curso);

        atividade = this;
        Intent intent = getIntent();
        lista = (ArrayList<Curso>)intent.getSerializableExtra(MainActivity.LISTA);
        BaseAdapter adapter = new CursoAdapter(this, lista.toArray(new Curso[0]));
        ListView listView = (ListView) findViewById(R.id.lista_cursos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheCursoActivity.class);
                intent.putExtra(ID, lista.get(position).getIdCurso());
                intent.putExtra(NOME, lista.get(position).getNome());
                String detalhe = lista.get(position).getDetalhe();
                intent.putExtra(DETALHE, detalhe);
                intent.putExtra(FOTO, lista.get(position).getFoto());



                startActivity(intent);

            }

        });

    }

}
