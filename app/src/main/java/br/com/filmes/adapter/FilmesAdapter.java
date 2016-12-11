package br.com.filmes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.filmes.entidades.Filmes;

/**
 * Created by Thalita Monteiro on 06/12/2016.
 */
public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.FilmeViewHolder> {

    List<Filmes> filmesLista = new ArrayList<Filmes>();

    public FilmesAdapter(List<Filmes> filmes){
        filmesLista = filmes;
    }

    @Override
    public FilmesAdapter.FilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filmes_populares, parent, false);

        return new FilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmeViewHolder holder, int position) {
        Filmes filme = filmesLista.get(position);

        holder.title_filme.setText(filme.getTitle());
        holder.overview_filme.setText(filme.getOverview());
        holder.popularidade_filme.setText(filme.getPopularity());


    }

    @Override
    public int getItemCount() {
        return filmesLista.size();
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder{

        ImageView imagem_filme;
        TextView title_filme, overview_filme, popularidade_filme;

        public FilmeViewHolder(View itemView){
            super(itemView);

            imagem_filme = (ImageView) itemView.findViewById(R.id.image_filme);
            title_filme = (TextView) itemView.findViewById(R.id.title_filme);
            overview_filme = (TextView) itemView.findViewById(R.id.overview_filme);
            popularidade_filme = (TextView) itemView.findViewById(R.id.popularidade_filme);
        }
    }
}
