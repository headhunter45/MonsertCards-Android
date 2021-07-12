package com.majinnaibu.monstercards.models.relationships;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.models.Monster;

import java.util.List;

public class DeckWithMonsters {
    @Embedded
    public Deck deck;

    @Relation(
            parentColumn = "id",
            entity = Monster.class,
            entityColumn = "id",
            associateBy = @Junction(
                    value = MonstersInDecks.class,
                    parentColumn = "deckId",
                    entityColumn = "monsterId"
            )
    )
    public List<Monster> monsters;
}
