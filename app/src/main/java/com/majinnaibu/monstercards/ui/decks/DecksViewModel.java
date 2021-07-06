package com.majinnaibu.monstercards.ui.decks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DecksViewModel extends ViewModel {
    private final MutableLiveData<List<Deck>> mDecks;
    private int mNumDecks = 0;

    public DecksViewModel() {
        mDecks = new MutableLiveData<>(new ArrayList<>());
    }


    public Deck addNewDeck() {
        Logger.logUnimplementedMethod();
        mNumDecks++;
        Deck deck = new Deck();
        deck.id = UUID.randomUUID();
        deck.name = String.format(Locale.getDefault(), "Deck %d", mNumDecks);
        List<Deck> decks;
        List<Deck> oldDecks = mDecks.getValue();
        if (oldDecks != null) {
            decks = new ArrayList<>(oldDecks);
        } else {
            decks = new ArrayList<>();
        }
        decks.add(deck);
        mDecks.setValue(decks);
        return deck;
    }

    public LiveData<List<Deck>> getDecks() {
        return mDecks;
    }

    public void removeDeck(int position) {
        List<Deck> oldDecks = mDecks.getValue();
        if (oldDecks == null || position >= oldDecks.size()) {
            mDecks.setValue(new ArrayList<>());
            return;
        }
        List<Deck> decks = new ArrayList<>(oldDecks);
        decks.remove(position);
        mDecks.setValue(decks);
    }
}
