package com.majinnaibu.monstercards.models;

import androidx.annotation.Nullable;

import com.majinnaibu.monstercards.utils.Hasher;

import java.util.Objects;

public class Language implements Comparable<Language> {

    private String mName;
    private boolean mSpeaks;

    public Language(String name, boolean speaks) {
        mName = name;
        mSpeaks = speaks;
    }

    public String getName() {
        return mName;
    }

    public void setName(String value) {
        mName = value;
    }

    public boolean getSpeaks() {
        return mSpeaks;
    }

    public void setSpeaks(boolean value) {
        mSpeaks = value;
    }

    @Override
    public int compareTo(Language other) {
        // TODO: Make this work if other is null.
        // TODO: Make this work if either name is null.
        if (this.mSpeaks && !other.mSpeaks) {
            return -1;
        }
        if (!this.mSpeaks && other.mSpeaks) {
            return 1;
        }
        return this.mName.compareToIgnoreCase(other.mName);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Language)) {
            return false;
        }
        Language otherLanguage = (Language) obj;
        if (!Objects.equals(this.mName, otherLanguage.mName)) {
            return false;
        }
        if (this.mSpeaks != otherLanguage.mSpeaks) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        Hasher hasher = new Hasher(47, 11);
        hasher.combine(mName);
        hasher.combine(mSpeaks);
        return hasher.total();
    }
}
