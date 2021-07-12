package com.majinnaibu.monstercards.models.relationships;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

import java.util.UUID;

@Entity(
        tableName = "monstersInDecks",
        primaryKeys = {"monsterId", "deckId"},
        indices = {@Index("deckId")}
)
public class MonstersInDecks {
    @NonNull
    public UUID monsterId;

    @NonNull
    public UUID deckId;

}
