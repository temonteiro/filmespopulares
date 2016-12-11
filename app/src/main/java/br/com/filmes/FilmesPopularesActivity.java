package br.com.filmes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.filmes.adapter.FilmesAdapter;
import br.com.filmes.connection.HttpHandler;
import br.com.filmes.entidades.Filmes;

/**
 * Created by Thalita Monteiro on 06/12/2016.
 */
public class FilmesPopularesActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private String TAG = FilmesPopularesActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<Filmes> listaFilmes;

    private static String url = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=f24be2cccc529d48452e2ce319b1a6f3";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmes_populares_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private class FilmesPopularesAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FilmesPopularesActivity.this);
            pDialog.setMessage("Aguarde...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray filmesJSON = jsonObj.getJSONArray("results");
                    listaFilmes = new ArrayList<Filmes>();

                    for (int i = 0; i < filmesJSON.length(); i++) {
                        JSONObject filme = filmesJSON.getJSONObject(i);
                        Filmes filmePOJO = new Filmes();

                        filmePOJO.setImagePath(filme.getString("poster_path"));
                        filmePOJO.setTitle(filme.getString("title"));
                        filmePOJO.setOverview(filme.getString("overview"));
                        filmePOJO.setPopularity(filme.getString("popularity"));

                        listaFilmes.add(filmePOJO);
                    }

                }catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }


            }else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();

            recyclerView.setAdapter(new FilmesAdapter(listaFilmes));
            recyclerView.setLayoutManager(new LinearLayoutManager(FilmesPopularesActivity.this));
        }
    }
}
