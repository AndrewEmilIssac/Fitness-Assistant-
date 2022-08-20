package com.fitness.assistant.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;


public class GoogleFit {
    FitnessOptions fitnessOptions;
    DataReadRequest readRequest;
    GoogleSignInAccount account;
    Context context;
    static GoogleFit googleFit;

    public static GoogleFit getInstance(Context context) {
        if (googleFit == null) {
            googleFit = new GoogleFit(context);
        }
        return googleFit;
    }

    public GoogleFit(Context context) {
        this.context = context;
        //data want to access
        setFitnessOptions();
        account = GoogleSignIn.getAccountForExtension(context, fitnessOptions);
        dataWantToRead();
    }

    void setFitnessOptions() {
        fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                .build();
    }

    public FitnessOptions getFitnessOptions() {
        return fitnessOptions;
    }

    void dataWantToRead() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusYears(1);
        long endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond();
        long startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond();

        readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_CALORIES_EXPENDED)
                .setTimeRange(startSeconds, endSeconds, TimeUnit.SECONDS)
                .enableServerQueries()
                .bucketByTime(1, TimeUnit.DAYS)
                .build();
    }

    public void sendRequest() {
        Fitness.getHistoryClient(context, account)
                .readData(readRequest)
                .addOnSuccessListener(dataReadResponse -> updateData(dataReadResponse)).addOnFailureListener(e -> Log.e("error", e.getMessage()));
    }

    private void updateData(DataReadResponse dataReadResult) {
        if (!dataReadResult.getBuckets().isEmpty()) {
            updateUIWithHeartRate(dataReadResult.getBuckets().get(dataReadResult.getBuckets().size() - 1).getDataSets().get(dataReadResult.getBuckets().get(dataReadResult.getBuckets().size() - 1).getDataSets().size() - 1));
        }
    }

    void updateUIWithHeartRate(DataSet dataSet) {
        String burnedCalories = null;
        if (!dataSet.getDataPoints().isEmpty()) {
            DataPoint dataPoint = dataSet.getDataPoints().get(dataSet.getDataPoints().size() - 1);
                Field field = dataPoint.cgit remote set-url origingetDataType().getFields().get(0);
            burnedCalories = dataPoint.getValue(field).toString();
            sendDataToView(burnedCalories);
        } else {
            sendDataToView(null);
        }
    }

    void sendDataToView(String average) {
        Intent intent = new Intent("burnedCalories");
        Bundle info = new Bundle();
        if (average != null) {
            info.putString("burnedCalories", average);
        }
        intent.putExtras(info);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
