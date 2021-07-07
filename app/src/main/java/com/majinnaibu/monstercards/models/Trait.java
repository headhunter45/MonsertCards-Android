package com.majinnaibu.monstercards.models;

import androidx.annotation.Nullable;

import com.majinnaibu.monstercards.utils.Hasher;

import java.util.Objects;

public class Trait implements Comparable<Trait> {

    public String name;
    public String description;

    public Trait(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public int compareTo(Trait other) {
        // TODO: Make this work if other is null.
        // TODO: Make this work if either name is null.
        // TODO: Make this work if either description is null.
        int compareResult = name.compareToIgnoreCase(other.name);
        if (compareResult != 0) {
            return compareResult;
        }
        return description.compareToIgnoreCase(other.description);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Trait)) {
            return false;
        }
        Trait otherTrait = (Trait) obj;
        if (!Objects.equals(this.name, otherTrait.name)) {
            return false;
        }
        if (!Objects.equals(this.description, otherTrait.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        Hasher hasher = new Hasher(17, 23);
        hasher.combine(name);
        hasher.combine(description);
        return hasher.total();
    }
}
