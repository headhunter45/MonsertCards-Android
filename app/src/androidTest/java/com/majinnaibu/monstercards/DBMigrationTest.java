package com.majinnaibu.monstercards;

import android.database.Cursor;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.room.testing.MigrationTestHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.majinnaibu.monstercards.AppDatabase.MIGRATION_1_2;
import static com.majinnaibu.monstercards.AppDatabase.MIGRATION_2_3;
import static com.majinnaibu.monstercards.AppDatabase.MIGRATION_3_4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DBMigrationTest {
    private static final String TEST_DB_NAME = "migration-test-db";
    private static final Migration[] ALL_MIGRATIONS = new Migration[]{
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4
    };
    @Rule
    public final MigrationTestHelper helper;

    public DBMigrationTest() {
        helper = new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                AppDatabase.class.getCanonicalName(),
                new FrameworkSQLiteOpenHelperFactory());

    }

    @Test
    public void migrationFrom1To2_containsCorrectData() throws IOException {
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB_NAME, 1);
        db.execSQL("INSERT INTO monster (" +
                "id, name, size, type, subtype, alignment," +
                "strength_score, strength_saving_throw_advantage, strength_saving_throw_proficiency," +
                "dexterity_score, dexterity_saving_throw_advantage, dexterity_saving_throw_proficiency," +
                "constitution_score, constitution_saving_throw_advantage, constitution_saving_throw_proficiency, " +
                "intelligence_score, intelligence_saving_throw_advantage, intelligence_saving_throw_proficiency, " +
                "wisdom_score, wisdom_saving_throw_advantage, wisdom_saving_throw_proficiency, " +
                "charisma_score, charisma_saving_throw_advantage, charisma_saving_throw_proficiency, " +
                "armor_type, shield_bonus, natural_armor_bonus, other_armor_description, " +
                "hit_dice, has_custom_hit_points, custom_hit_points_description," +
                "walk_speed, burrow_speed, climb_speed, fly_speed, can_hover, swim_speed, has_custom_speed, custom_speed_description, " +
                "challenge_rating, custom_challenge_rating_description, custom_proficiency_bonus, " +
                "blindsight_range, is_blind_beyond_blindsight_range, darkvision_range, tremorsense_range, truesight_range, " +
                "telepathy_range, understands_but_description, languages, " +
                "damage_immunities, damage_resistances, damage_vulnerabilities, condition_immunities, " +
                "skills, abilities, actions, reactions, lair_actions, legendary_actions, regional_actions" +
                ") VALUES(" +
                "'00000000-0000-0000-0000-000000000001', 'goat', 'medium', 'beast', '', 'unaligned', " +
                "16, 'NONE', 'NONE', " +
                "18, 'NONE', 'NONE', " +
                "20, 'NONE', 'NONE', " +
                "4, 'NONE', 'NONE', " +
                "4, 'NONE', 'NONE', " +
                "3, 'NONE', 'NONE', " +
                "'none', 0, 0, '', " +
                "0, false, '', " +
                "5, 10, 15, 20, false, 25, false, '', " +
                "'2', '', 0, " +
                "5, false, 10, 15, 20, " +
                "25, '', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', '[]')");
        db.close();

        db = helper.runMigrationsAndValidate(TEST_DB_NAME, 2, true, MIGRATION_1_2);

        // There should be 1 monster in the table.
        Cursor c = db.query("SELECT COUNT(1) FROM monsters");
        assertNotNull(c);
        c.moveToFirst();
        assertEquals(1, c.getInt(0));

    }

    @Test
    public void migrationFrom2To3_containsCorrectData() throws IOException {
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB_NAME, 2);
        db.execSQL("INSERT INTO monsters (id, name, size, type, subtype, alignment," +
                "strength_score, strength_saving_throw_advantage, strength_saving_throw_proficiency," +
                "dexterity_score, dexterity_saving_throw_advantage, dexterity_saving_throw_proficiency," +
                "constitution_score, constitution_saving_throw_advantage, constitution_saving_throw_proficiency, " +
                "intelligence_score, intelligence_saving_throw_advantage, intelligence_saving_throw_proficiency, " +
                "wisdom_score, wisdom_saving_throw_advantage, wisdom_saving_throw_proficiency, " +
                "charisma_score, charisma_saving_throw_advantage, charisma_saving_throw_proficiency, " +

                "armor_type, shield_bonus, natural_armor_bonus, other_armor_description, " +
                "hit_dice, has_custom_hit_points, custom_hit_points_description, " +
                "walk_speed, burrow_speed, climb_speed, fly_speed, can_hover, swim_speed, has_custom_speed, custom_speed_description, " +
                "challenge_rating, custom_challenge_rating_description, custom_proficiency_bonus, " +
                "is_blind_beyond_blindsight_range, blindsight_range, " +
                "telepathy_range, understands_but_description, languages, " +
                "skills, abilities, actions, reactions, lair_actions, legendary_actions, regional_actions, " +
                "damage_immunities, damage_resistances, damage_vulnerabilities, condition_immunities " +
                ") VALUES(" +
                "'00000000-0000-0000-0000-000000000001', 'goat', 'medium', 'beast', '', 'unaligned', " +
                "16, 'none', 'none', " +
                "18, 'none', 'none', " +
                "20, 'none', 'none', " +
                "4, 'none', 'none', " +
                "4, 'none', 'none', " +
                "3, 'none', 'none', " +
                "'none', 0, 0, '', " +
                "0, false, '', " +
                "5, 10, 15, 20, false, 25, false, '', " +
                "'2', '', 0, " +
                "false, 50, " +
                "25, '', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', '[]', '[]', " +
                "'[]', '[]', '[]', '[]')");
        db.close();
        db = helper.runMigrationsAndValidate(TEST_DB_NAME, 3, true, MIGRATION_2_3);

        // There should be 1 monster in the table.
        Cursor c = db.query("SELECT COUNT(1) FROM monsters");
        assertNotNull(c);
        c.moveToFirst();
        assertEquals(1, c.getInt(0));
    }

    @Test
    public void migrationFrom3To4_containsCorrectData() throws IOException {
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB_NAME, 3);
        db.execSQL("INSERT INTO monsters (id, name, size, type, subtype, alignment," +
                "strength_score, strength_saving_throw_advantage, strength_saving_throw_proficiency," +
                "dexterity_score, dexterity_saving_throw_advantage, dexterity_saving_throw_proficiency," +
                "constitution_score, constitution_saving_throw_advantage, constitution_saving_throw_proficiency, " +
                "intelligence_score, intelligence_saving_throw_advantage, intelligence_saving_throw_proficiency, " +
                "wisdom_score, wisdom_saving_throw_advantage, wisdom_saving_throw_proficiency, " +
                "charisma_score, charisma_saving_throw_advantage, charisma_saving_throw_proficiency, " +
                "armor_type, shield_bonus, natural_armor_bonus, other_armor_description, hit_dice, " +
                "has_custom_hit_points, custom_hit_points_description, walk_speed, burrow_speed, " +
                "climb_speed, fly_speed, can_hover, swim_speed, has_custom_speed, custom_speed_description, " +
                "challenge_rating, custom_challenge_rating_description, custom_proficiency_bonus, telepathy_range, " +
                "understands_but_description, senses, skills, damage_immunities, damage_resistances, " +
                "damage_vulnerabilities, condition_immunities, languages, abilities, actions, reactions, " +
                "lair_actions, legendary_actions, regional_actions" +
                ") VALUES('00000000-0000-0000-0000-000000000001', 'goat', 'medium', 'beast', '', 'unaligned', " +
                "16, 'none', 'none', " +
                "18, 'none', 'none', " +
                "20, 'none', 'none', " +
                "4, 'none', 'none', " +
                "4, 'none', 'none', " +
                "3, 'none', 'none', " +
                "'none', 0, 0, '', 0, " +
                "false, '', 5, 10, " +
                "15, 20, false, 25, false, '', " +
                "'2', '', 0, 5, " +
                "'', '[]', '[]', '[]', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', '[]', '[]', '[]', '[]')");
        db.close();

        db = helper.runMigrationsAndValidate(TEST_DB_NAME, 4, true, MIGRATION_3_4);
        Cursor c = db.query("SELECT COUNT (1) FROM monsters");
        assertNotNull(c);
        c.moveToFirst();
        assertEquals(1, c.getInt(0));
        c = db.query("SELECT COUNT (1) FROM decks");
        assertNotNull(c);
        c.moveToFirst();
        assertEquals(0, c.getInt(0));
        c = db.query("SELECT COUNT (1) FROM monstersInDecks");
        assertNotNull(c);
        c.moveToFirst();
        assertEquals(0, c.getInt(0));
        c = db.query("SELECT strength_saving_throw_advantage, strength_saving_throw_proficiency, dexterity_saving_throw_advantage, dexterity_saving_throw_proficiency, constitution_saving_throw_advantage, constitution_saving_throw_proficiency, intelligence_saving_throw_advantage, intelligence_saving_throw_proficiency, wisdom_saving_throw_advantage, wisdom_saving_throw_proficiency, charisma_saving_throw_advantage, charisma_saving_throw_proficiency FROM monsters");
        assertNotNull(c);
        c.moveToFirst();
        assertEquals("NONE", c.getString(0));
        assertEquals("NONE", c.getString(1));
        assertEquals("NONE", c.getString(2));
        assertEquals("NONE", c.getString(3));
        assertEquals("NONE", c.getString(4));
        assertEquals("NONE", c.getString(5));
        assertEquals("NONE", c.getString(6));
        assertEquals("NONE", c.getString(7));
        assertEquals("NONE", c.getString(8));
        assertEquals("NONE", c.getString(9));
        assertEquals("NONE", c.getString(10));
        assertEquals("NONE", c.getString(11));
        db.execSQL("INSERT INTO monsters (id, name, size, type, subtype, alignment," +
                "strength_score, strength_saving_throw_advantage, strength_saving_throw_proficiency," +
                "dexterity_score, dexterity_saving_throw_advantage, dexterity_saving_throw_proficiency," +
                "constitution_score, constitution_saving_throw_advantage, constitution_saving_throw_proficiency, " +
                "intelligence_score, intelligence_saving_throw_advantage, intelligence_saving_throw_proficiency, " +
                "wisdom_score, wisdom_saving_throw_advantage, wisdom_saving_throw_proficiency, " +
                "charisma_score, charisma_saving_throw_advantage, charisma_saving_throw_proficiency, " +
                "armor_type, shield_bonus, natural_armor_bonus, other_armor_description, hit_dice, " +
                "has_custom_hit_points, custom_hit_points_description, walk_speed, burrow_speed, " +
                "climb_speed, fly_speed, can_hover, swim_speed, has_custom_speed, custom_speed_description, " +
                "challenge_rating, custom_challenge_rating_description, custom_proficiency_bonus, telepathy_range, " +
                "understands_but_description, senses, skills, damage_immunities, damage_resistances, " +
                "damage_vulnerabilities, condition_immunities, languages, abilities, actions, reactions, " +
                "lair_actions, legendary_actions, regional_actions" +
                ") VALUES('00000000-0000-0000-0000-000000000002', 'goat', 'medium', 'beast', '', 'unaligned', " +
                "16, 'none', 'none', " +
                "18, 'none', 'none', " +
                "20, 'none', 'none', " +
                "4, 'none', 'none', " +
                "4, 'none', 'none', " +
                "3, 'none', 'none', " +
                "'none', 0, 0, '', 0, " +
                "false, '', 5, 10, " +
                "15, 20, false, 25, false, '', " +
                "'2', '', 0, 5, " +
                "'', '[]', '[]', '[]', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', '[]', '[]', '[]', '[]')");
    }

    @Test
    public void migrationFrom1To4_containsCorrectData() throws IOException {
        // Create the earliest version of the database
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB_NAME, 1);
        db.execSQL("INSERT INTO monster (" +
                "id, name, size, type, subtype, alignment," +
                "strength_score, strength_saving_throw_advantage, strength_saving_throw_proficiency," +
                "dexterity_score, dexterity_saving_throw_advantage, dexterity_saving_throw_proficiency," +
                "constitution_score, constitution_saving_throw_advantage, constitution_saving_throw_proficiency, " +
                "intelligence_score, intelligence_saving_throw_advantage, intelligence_saving_throw_proficiency, " +
                "wisdom_score, wisdom_saving_throw_advantage, wisdom_saving_throw_proficiency, " +
                "charisma_score, charisma_saving_throw_advantage, charisma_saving_throw_proficiency, " +
                "armor_type, shield_bonus, natural_armor_bonus, other_armor_description, " +
                "hit_dice, has_custom_hit_points, custom_hit_points_description," +
                "walk_speed, burrow_speed, climb_speed, fly_speed, can_hover, swim_speed, has_custom_speed, custom_speed_description, " +
                "challenge_rating, custom_challenge_rating_description, custom_proficiency_bonus, " +
                "blindsight_range, is_blind_beyond_blindsight_range, darkvision_range, tremorsense_range, truesight_range, " +
                "telepathy_range, understands_but_description, languages, " +
                "damage_immunities, damage_resistances, damage_vulnerabilities, condition_immunities, " +
                "skills, abilities, actions, reactions, lair_actions, legendary_actions, regional_actions" +
                ") VALUES(" +
                "'00000000-0000-0000-0000-000000000001', 'goat', 'medium', 'beast', '', 'unaligned', " +
                "16, 'NONE', 'NONE', " +
                "18, 'NONE', 'NONE', " +
                "20, 'NONE', 'NONE', " +
                "4, 'NONE', 'NONE', " +
                "4, 'NONE', 'NONE', " +
                "3, 'NONE', 'NONE', " +
                "'none', 0, 0, '', " +
                "0, false, '', " +
                "5, 10, 15, 20, false, 25, false, '', " +
                "'2', '', 0, " +
                "5, false, 10, 15, 20, " +
                "25, '', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', " +
                "'[]', '[]', '[]', '[]', '[]', '[]')");
        db.close();

        // Open the latest version of the database. Room will validate the schema once all migrations execute.
        AppDatabase appDb = Room.databaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                AppDatabase.class,
                TEST_DB_NAME)
                .addMigrations(ALL_MIGRATIONS).build();
        appDb.getOpenHelper().getWritableDatabase();
        appDb.close();
    }
}
