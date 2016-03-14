package com.queen.rxjavaretrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.queen.rxjavaretrofitdemo.R;
import com.queen.rxjavaretrofitdemo.subscribers.ProgressSubscriber;
import com.queen.rxjavaretrofitdemo.subscribers.SubscriberOnNextListener;
import com.queen.rxjavaretrofitdemo.entity.Subject;
import com.queen.rxjavaretrofitdemo.http.HttpMethods;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    /**
     * 不要用Activity直接实现这个接口，
     * 因为在一个Activity或者Fragment中，可能会发出多个请求
     */
    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            //接受到请求结果之后对UI的处理
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    //进行网络请求
    private void getMovie(){

        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this), 0, 10);
    }
}
