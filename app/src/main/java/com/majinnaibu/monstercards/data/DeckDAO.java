package com.majinnaibu.monstercards.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.models.relationships.DeckWithMonsters;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DeckDAO {
    @Query("SELECT * FROM decks")
    Flowable<List<Deck>> get();

    @Query("SELECT * FROM decks WHERE id = :deckId")
    Flowable<Deck> get(UUID deckId);

    @Query("SELECT * FROM decks WHERE id = :deckId")
    @Transaction
    Flowable<DeckWithMonsters> getWithMonsters(UUID deckId);

    @Delete
    Completable delete(Deck deck);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable save(Deck deck);
}
