package com.majinnaibu.monstercards.data;

import androidx.annotation.NonNull;

import com.majinnaibu.monstercards.AppDatabase;
import com.majinnaibu.monstercards.helpers.StringHelper;
import com.majinnaibu.monstercards.models.Deck;
import com.majinnaibu.monstercards.models.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class MonsterRepository {
    // TODO: when saving a monster or deck ensure the id is non-null and fits the lowercase UUID format .

    private final AppDatabase m_db;

    public MonsterRepository(@NonNull AppDatabase db) {
        m_db = db;
    }

    public Flowable<List<Monster>> getMonsters() {
        return m_db.monsterDao()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Monster>> searchMonsters(String searchText) {
        return m_db.monsterDao()
                .get()
                .map(monsters -> {
                    ArrayList<Monster> filteredMonsters = new ArrayList<>();
                    for (Monster monster : monsters) {
                        if (Helpers.monsterMatchesSearch(monster, searchText)) {
                            filteredMonsters.add(monster);
                        }
                    }
                    return (List<Monster>) filteredMonsters;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Monster> getMonster(@NonNull UUID monsterId) {
        return m_db.monsterDao()
                .get(monsterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable addMonster(Monster monster) {
        Completable result = m_db.monsterDao().save(monster);
        result.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return result;
    }

    public Completable delete(Monster monster) {
        Completable result = m_db.monsterDao().delete(monster);
        result.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return result;
    }

    public Completable save(Monster monster) {
        Completable result = m_db.monsterDao().save(monster);
        result.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return result;
    }

    public Flowable<List<Deck>> getDecks() {
        return m_db.deckDao()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Deck> getDeck(UUID deckId) {
        return m_db.deckDao()
                .get(deckId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public Flowable<DeckWithMonsters> getDeckWithMonsters(UUID deckId) {
//        return m_db.deckDAO()
//                .getWithMonsters(deckId.toString())
//                .
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public void delete(Deck deck) {
        m_db.deckDao().delete(deck);
    }

    public void save(Deck deck) {
        m_db.deckDao().save(deck);
    }

    private static class Helpers {
        static boolean monsterMatchesSearch(Monster monster, String searchText) {
            if (StringHelper.isNullOrEmpty(searchText)) {
                return true;
            }

            if (StringHelper.containsCaseInsensitive(monster.name, searchText)) {
                return true;
            }

            if (StringHelper.containsCaseInsensitive(monster.size, searchText)) {
                return true;
            }

            if (StringHelper.containsCaseInsensitive(monster.type, searchText)) {
                return true;
            }

            if (StringHelper.containsCaseInsensitive(monster.subtype, searchText)) {
                return true;
            }

            if (StringHelper.containsCaseInsensitive(monster.alignment, searchText)) {
                return true;
            }

            return false;
        }
    }
}
