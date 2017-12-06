package <%= packageName %>.network;

import java.util.List;

import <%= packageName %>.BaseDaoTest;
import <%= packageName %>.MovieModel;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

public interface ServiceApi {
    /**
     * get movie for testing
     *
     * @return
     */
    @GET("3/discover/movie?api_key=1b2f29d43bf2e4f3142530bc6929d341&sort_by=popularity.desc")
    Observable<BaseDaoTest<List<MovieModel>>> getMovie();
}
