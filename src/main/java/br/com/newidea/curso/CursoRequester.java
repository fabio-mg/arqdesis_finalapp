package br.com.newidea.curso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by fabio on 27/11/16.
 */

public class CursoRequester {

    OkHttpClient client = new OkHttpClient();


    public ArrayList<Curso> get (String url, String chave) throws IOException {
        ArrayList<Curso> lista = new ArrayList<>();
        System.out.println(url);
        // FormBody formBody = new FormBody.Builder().add("chave", chave).build();
        //System.out.println(formBody);
        //Request request = new Request.Builder().url(url).post(formBody).build();
        Request request = new Request.Builder().url(url).build();
        System.out.println(request);
        Response response = client.newCall(request).execute();
        System.out.println(response);
        String jsonString = response.body().string();
        System.out.println(jsonString);
        try{
            JSONArray root = new JSONArray(jsonString);
            JSONObject item = null;
            for(int i = 0; i < root.length(); i++){
                item = (JSONObject)root.get(i);

                int idCurso = item.getInt("id");
                String nome = item.getString("nome");
                String detalhe = item.getString("detalhe");

                lista.add(new Curso(idCurso, nome, detalhe));
            }
        } catch (JSONException e){
            throw new IOException(e);
        } finally {
            if(lista.size() == 0){
                lista.add(new Curso());
            }
        }
        return lista;
    }


    public Bitmap getImage(String url) throws IOException{
        Bitmap img = null;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        InputStream is = response.body().byteStream();
        img = BitmapFactory.decodeStream(is);
        is.close();
        return img;
    }


    public boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
