package <%= packageName %>.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import <%= packageName %>.base.BaseActivity;
import <%= packageName %>.base.BasePresenter;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);

    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}


