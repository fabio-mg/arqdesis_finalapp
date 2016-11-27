package br.com.newidea.curso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Curso> lista;
    CursoRequester requester;
    Intent intent;
    String chave;
    ProgressBar progressBar;
    public static final String SERVIDOR = "http://10.0.2.2:8080";
    public static final String APLICACAO = "/Curso";
    private final String RECURSO = "/curso";
    public static final String LISTA = "br.com.newIdea.curso.lista";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.timer);
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void buscarCursos(View view){
        requester = new CursoRequester();
        intent = new Intent(this, ListarCursoActivity.class);

        if(requester.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = requester.get(SERVIDOR + APLICACAO + RECURSO, chave);

                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                intent.putExtra( LISTA, lista);
                                startActivity(intent);
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
