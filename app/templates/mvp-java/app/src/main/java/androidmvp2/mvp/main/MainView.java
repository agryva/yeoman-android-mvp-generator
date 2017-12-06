package <%= packageName %>.mvp.main;

import java.util.List;

import <%= packageName %>.base.BaseDaoTest;
import <%= packageName %>.base.BaseView;
import <%= packageName %>.model.MovieModel;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

public interface MainView extends BaseView {
    void onSuccess(BaseDaoTest<List<MovieModel>> data);
    void onFailed(String errorMessage);
}
