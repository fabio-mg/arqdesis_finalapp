package br.com.newidea.curso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class DetalheCursoActivity extends AppCompatActivity {
    ImageView clienteImageView;
    Curso curso;
    CursoRequester requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_curso);
        Intent intent = getIntent();

        curso = new Curso(intent.getIntExtra(ListarCursoActivity.ID, 0),
                          intent.getStringExtra(ListarCursoActivity.NOME),
                          intent.getStringExtra(ListarCursoActivity.DETALHE));

        clienteImageView = (ImageView) findViewById(R.id.curso_image_view);
        //Drawable drawable = Util.getDrawable(this, curso.getFoto());

        requester = new CursoRequester();
        if(requester.isConnected(this)) {
            //usando AsyncTask - veja a class DownloadImageTask abaixo
            new DowloadImageTask().execute();

        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível", Toast.LENGTH_LONG);
            toast.show();
        }

        TextView nome = (TextView)findViewById(R.id.txt_curso_nome);
        TextView detalhe = (TextView)findViewById(R.id.txt_curso_detalhe);

        nome.setText(curso.getNome());
        detalhe.setText(curso.getDetalhe());
    }

    private class DowloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap img = null;
            try {
                img = requester.getImage(MainActivity.SERVIDOR +
                        MainActivity.APLICACAO + "/img/" + curso.getFoto() + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return img;
        }

        @Override
        protected void onPostExecute(Bitmap img){
            clienteImageView.setImageBitmap(img);
        }
    }
}
