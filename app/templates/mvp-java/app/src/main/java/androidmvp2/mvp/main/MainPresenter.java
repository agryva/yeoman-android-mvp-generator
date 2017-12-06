package <%= packageName %>.mvp.main;

import java.util.List;

import <%= packageName %>.base.BaseDaoTest;
import <%= packageName %>.base.BasePresenter;
import <%= packageName %>.model.MovieModel;
import <%= packageName %>.network.ApiCallback;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView mainView){
        attachView(mainView);
    }

    public void onGetMovie(){
        mvpView.showLoading();
        addSubscription(serviceApi.getMovie(), new ApiCallback<BaseDaoTest<List<MovieModel>>>() {
            @Override
            public void onSuccess(BaseDaoTest<List<MovieModel>> data) {
                mvpView.hideLoading();
                mvpView.onSuccess(data);

            }

            @Override
            public void onFailure(String msg) {
                mvpView.hideLoading();
                mvpView.onFailed(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
