package com.majinnaibu.monstercards.ui.decks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.majinnaibu.monstercards.AppDatabase;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.models.relationships.DeckWithMonsters;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class DeckViewModel extends AndroidViewModel {
    private final AppDatabase mDB;
    private final MutableLiveData<UUID> mDeckId;
    private final MutableLiveData<List<Monster>> mMonsters;

    public DeckViewModel(@NonNull Application application) {
        super(application);
        mDB = AppDatabase.getInstance(application);
        mMonsters = new MutableLiveData<>(new ArrayList<>());
        mDeckId = new MutableLiveData<>(null);
        Logger.logUnimplementedMethod();
    }

    public LiveData<List<Monster>> getMonsters() {
        return mMonsters;
    }

    public void removeMonster(int position) {
        Logger.logUnimplementedMethod();
    }

    public boolean moveMonster(int from, int to) {
        Logger.logUnimplementedMethod();
        return false;
    }

    public void setDeckId(UUID uuid) {
        mDeckId.setValue(uuid);
        fetchMonsters();
    }

    private void fetchMonsters() {
        UUID deckId = mDeckId.getValue();
        if (mDB != null && deckId != null) {
            mDB.deckDao().getWithMonsters(deckId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new DisposableSubscriber<DeckWithMonsters>() {
                        @Override
                        public void onNext(DeckWithMonsters deck) {
                            // TODO: consider not sorting monsters. Ideally the user would be able to drag to reorder them and we would preserve the order, but if not we should sort the list.
                            Collections.sort(deck.monsters);
                            mMonsters.setValue(deck.monsters);
                        }

                        @Override
                        public void onError(Throwable t) {
                            Logger.logError(t);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            mMonsters.setValue(new ArrayList<>());
        }
    }
}
