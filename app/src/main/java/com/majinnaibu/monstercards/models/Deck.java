package com.majinnaibu.monstercards.models;

import androidx.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

public class Deck {
    public UUID id;
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
}
