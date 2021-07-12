package com.majinnaibu.monstercards.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.majinnaibu.monstercards.utils.Hasher;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "decks")
public class Deck implements Comparable<Deck> {
    @PrimaryKey
    @NonNull
    public UUID id;

    @NonNull
    @ColumnInfo(defaultValue = "")
    public String name;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Deck)) {
            return false;
        }
        Deck other = (Deck) obj;

        if (!Objects.equals(name, other.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        Hasher hasher = new Hasher(0, 0);
        hasher.combine(id);
        hasher.combine(name);
        return hasher.total();
    }

    @Override
    public int compareTo(Deck other) {
        // TODO: Make this work if other is null.
        // TODO Make this work if either name is null.
        return this.name.compareToIgnoreCase(other.name);
    }
}
