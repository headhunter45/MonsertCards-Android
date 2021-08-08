package com.majinnaibu.monstercards.models.relationships;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;

import java.util.UUID;

@Entity(
        tableName = "monstersInDecks",
        primaryKeys = {"monsterId", "deckId"},
        indices = {@Index("deckId")}
)
public class MonsterInDeck {
    @NonNull
    public UUID monsterId;

    @NonNull
    public UUID deckId;

    public MonsterInDeck() {
        monsterId = null;
        deckId = null;
    }

    @Ignore
    public MonsterInDeck(UUID deckId, UUID monsterId) {
        this.monsterId = monsterId;
        this.deckId = deckId;
    }
}
