package <%= packageName %>;

import <%= packageName %>.network.ApiClient;
import <%= packageName %>.network.ServiceApi;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by irfanirawansukirman on 02/10/17.
 */

public class BasePresenter<V> {

    public V mvpView;
    protected ServiceApi serviceApi;
    private CompositeSubscription mCompositeSubsc;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        serviceApi = ApiClient.request().create(ServiceApi.class);
    }


    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (mCompositeSubsc != null && mCompositeSubsc.hasSubscriptions()) {
            mCompositeSubsc.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubsc == null) {
            mCompositeSubsc = new CompositeSubscription();
        }
        mCompositeSubsc.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}

