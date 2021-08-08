package com.majinnaibu.monstercards.ui.decks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.majinnaibu.monstercards.AppDatabase;
import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class DecksViewModel extends AndroidViewModel {
    private final AppDatabase m_db;
    private final MutableLiveData<List<Deck>> mDecks;
    private int mNumDecks = 0;

    public DecksViewModel(@NonNull Application application) {
        super(application);
        m_db = AppDatabase.getInstance(application);
        mDecks = new MutableLiveData<>(new ArrayList<>());
        m_db.deckDao().get().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new DisposableSubscriber<List<Deck>>() {
            @Override
            public void onNext(List<Deck> decks) {
                mDecks.setValue(decks);
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

    public Deck addNewDeck() {
        Deck deck = new Deck();
        deck.id = UUID.randomUUID();
        deck.name = "Unnamed Deck";
        m_db.deckDao()
                .save(deck)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        }
                );
        return deck;
    }

    public LiveData<List<Deck>> getDecks() {
        return mDecks;
    }

    public void removeDeck(int position) {
        Deck deck = mDecks.getValue().get(position);
        m_db.deckDao()
                .delete(deck)
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
