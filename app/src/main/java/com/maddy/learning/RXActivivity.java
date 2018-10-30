package com.maddy.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class RXActivivity extends AppCompatActivity {

    private static final String TAG = RXActivivity.class.getSimpleName();
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxactivivity);
        textView = findViewById(R.id.textView);

        Observable<String> animalsObservable = getAnimalObservable();

        Observer<String> animalsObserver = getAnimalObserver();

        animalsObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);

    }

    private Observer<String> getAnimalObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("RX-Activity", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("RX-Activity", "Name: " + s);
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RX-Activity", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("RX-Activity", "All items are emitted!");
            }
        };
    }


    private  Observable<String> getAnimalObservable(){
        return Observable.fromArray("Ant","Bee","Cat","Dog","Buffalo","Zibra");
    }

}
