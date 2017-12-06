package <%= packageName %>;

import java.util.List;

import lombok.Data;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */
@Data
public class MovieModel {
    private int vote_count;
    private int id;
    private boolean video;
    private double vote_average;
    private String title;
    private double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;
}