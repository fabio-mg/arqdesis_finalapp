package br.com.newidea.curso;

/**
 * Created by fabio on 27/11/16.
 */

import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;

public class APIClient {

    private static RestAdapter REST_ADAPTER;
    private static void createAdapterIfNeeded() {
        if (REST_ADAPTER == null) {
            REST_ADAPTER = new RestAdapter.Builder()
                    .setEndpoint(
                            "http://10.0.2.2:8080/Curso")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient())
                    .build();
        }
    }
    public APIClient() {
        createAdapterIfNeeded();
    }
    public RestServices getRestService() {
        return REST_ADAPTER.create(RestServices.class);
    }
    public interface RestServices {
        @GET("/curso")
        void getAllCursos(Callback<List<Curso>> callbackCliente);
    }
}
