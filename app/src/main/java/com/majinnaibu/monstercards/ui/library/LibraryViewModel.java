package com.majinnaibu.monstercards.ui.library;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.majinnaibu.monstercards.AppDatabase;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class LibraryViewModel extends AndroidViewModel {
    private final AppDatabase mDB;
    private final MutableLiveData<List<Monster>> mMonsters;


    public LibraryViewModel(Application application) {
        super(application);
        mDB = AppDatabase.getInstance(application);
        mMonsters = new MutableLiveData<>(new ArrayList<>());
        mDB.monsterDao()
                .get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSubscriber<List<Monster>>() {
                    @Override
                    public void onNext(List<Monster> monsters) {
                        Collections.sort(monsters);
                        mMonsters.setValue(monsters);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.logError(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Monster addNewMonster() {
        Monster monster = new Monster();
        monster.id = UUID.randomUUID();
        monster.name = "Unnamed Monster";
        mDB.monsterDao()
                .save(monster)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Logger.logError(e);
                    }
                });
        return monster;
    }

    public LiveData<List<Monster>> getMonsters() {
        return mMonsters;
    }

    public void removeMonster(int position) {
        List<Monster> monsters = mMonsters.getValue();
        if (monsters != null) {
            Monster monster = monsters.get(position);
            if (monster != null) {
                mDB.monsterDao()
                        .delete(monster)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Logger.logError(e);
                            }
                        });
            }
        }
    }
}
