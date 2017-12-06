package <%= packageName %>.mvp.main;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.List;

import <%= packageName %>.R;
import <%= packageName %>.base.BaseDaoTest;
import <%= packageName %>.model.MovieModel;
import <%= packageName %>.ui.MvpActivity;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvpPresenter.onGetMovie();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onSuccess(BaseDaoTest<List<MovieModel>> data) {
        if (data.getResults() != null) {
            toastShow(new Gson().toJson(data.getResults()));
        }
    }

    @Override
    public void onFailed(String errorMessage) {
        toastShow(errorMessage);
    }
}
