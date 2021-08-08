package com.majinnaibu.monstercards.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.majinnaibu.monstercards.models.relationships.MonsterInDeck;

import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;

@Dao
public abstract class MonstersInDecksDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract Completable add(MonsterInDeck monsterInDeck);

    public Completable add(UUID deckId, UUID monsterId) {
        return add(new MonsterInDeck(deckId, monsterId));
    }

    @Delete
    public abstract Completable delete(MonsterInDeck monsterInDeck);

    public Completable delete(UUID deckId, UUID monsterId) {
        return delete(new MonsterInDeck(deckId, monsterId));
    }
}
