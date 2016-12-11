package br.com.filmes.entidades;

/**
 * Created by Thalita Monteiro on 06/12/2016.
 */
public class Filmes {

    private String imagePath;
    private String overview;
    private String title;
    private String popularity;

    public String getImagePath() {
        return imagePath;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
}
