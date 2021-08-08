package com.majinnaibu.monstercards.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.majinnaibu.monstercards.models.Monster;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MonsterDAO {
    @Query("SELECT * FROM monsters")
    Flowable<List<Monster>> get();

    @Query("SELECT * FROM monsters WHERE id = :monsterId")
    Flowable<Monster> get(UUID monsterId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable save(Monster monster);

    @Delete
    Completable delete(Monster monster);
}
