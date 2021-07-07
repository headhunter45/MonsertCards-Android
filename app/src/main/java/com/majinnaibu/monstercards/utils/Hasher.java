package com.majinnaibu.monstercards.utils;

public class Hasher {
    private final int mBase;
    private final int mMultiplier;
    private int mHash;

    public Hasher() {
        this(97, 23);
    }

    public Hasher(int base, int multiplier) {
        mBase = base;
        mMultiplier = multiplier;
        mHash = base;
    }

    public int total() {
        return mHash;
    }

    public void combine(Object obj) {
        if (obj != null) {
            mHash = mHash * mMultiplier + obj.hashCode();
        } else {
            mHash = mHash * mMultiplier;
        }
    }

    public void combine(int value) {
        mHash = mHash * mMultiplier + value;
    }

    public void combine(boolean value) {
        mHash = mHash * mMultiplier + (value ? 1 : 0);
    }

    public void combine(float value) {
        mHash = mHash * mMultiplier + Float.valueOf(value).hashCode();
    }

    public void combine(byte value) {
        mHash = mHash * mMultiplier + value;
    }

    public void combine(char value) {
        mHash = mHash * mMultiplier + value;
    }

    public void combine(double value) {
        mHash = mHash * mMultiplier + Double.valueOf(value).hashCode();
    }

    public void combine(long value) {
        mHash = mHash * mMultiplier + Long.valueOf(value).hashCode();
    }

    public void combine(short value) {
        mHash = mHash * mMultiplier + value;
    }
}
