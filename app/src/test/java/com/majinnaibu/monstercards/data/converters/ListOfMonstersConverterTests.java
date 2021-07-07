package com.majinnaibu.monstercards.data.converters;

import com.majinnaibu.monstercards.data.enums.AbilityScore;
import com.majinnaibu.monstercards.data.enums.AdvantageType;
import com.majinnaibu.monstercards.data.enums.ArmorType;
import com.majinnaibu.monstercards.data.enums.ChallengeRating;
import com.majinnaibu.monstercards.data.enums.ProficiencyType;
import com.majinnaibu.monstercards.models.Language;
import com.majinnaibu.monstercards.models.Monster;
import com.majinnaibu.monstercards.models.Skill;
import com.majinnaibu.monstercards.models.Trait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ListOfMonstersConverterTests {

    @Test
    public void emptyList_serializes() {
        List<Monster> monsters = new ArrayList<>();
        String expectedJson = "[]";
        String actualJson = ListOfMonstersConverter.fromListOfMonsters(monsters);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void emptyList_deserializes() {
        String json = "[]";
        List<Monster> monsters = null;
        monsters = ListOfMonstersConverter.listOfMonstersFromString(json);
        assertNotNull(monsters);
        assertEquals(0, monsters.size());
    }

    @Test
    public void listOfOneMonster_serializes() {
        Monster monster = getTestMonster();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        String expectedJson = "[{\"id\":\"00000000-0000-0000-0000-000000000001\",\"name\":\"a name\",\"size\":\"large\",\"type\":\"lazy\",\"subtype\":\"sub\",\"alignment\":\"align\",\"strengthScore\":8,\"strengthSavingThrowAdvantage\":\"ADVANTAGE\",\"strengthSavingThrowProficiency\":\"PROFICIENT\",\"dexterityScore\":12,\"dexteritySavingThrowAdvantage\":\"DISADVANTAGE\",\"dexteritySavingThrowProficiency\":\"EXPERTISE\",\"constitutionScore\":14,\"constitutionSavingThrowAdvantage\":\"NONE\",\"constitutionSavingThrowProficiency\":\"NONE\",\"intelligenceScore\":16,\"intelligenceSavingThrowAdvantage\":\"NONE\",\"intelligenceSavingThrowProficiency\":\"EXPERTISE\",\"wisdomScore\":18,\"wisdomSavingThrowAdvantage\":\"ADVANTAGE\",\"wisdomSavingThrowProficiency\":\"PROFICIENT\",\"charismaScore\":6,\"charismaSavingThrowAdvantage\":\"DISADVANTAGE\",\"charismaSavingThrowProficiency\":\"NONE\",\"armorType\":\"NATURAL_ARMOR\",\"shieldBonus\":3,\"naturalArmorBonus\":4,\"otherArmorDescription\":\"other\",\"hitDice\":4,\"hasCustomHP\":false,\"customHPDescription\":\"11 hp\",\"walkSpeed\":5,\"burrowSpeed\":10,\"climbSpeed\":15,\"flySpeed\":20,\"canHover\":false,\"swimSpeed\":25,\"hasCustomSpeed\":false,\"customSpeedDescription\":\"speed\",\"challengeRating\":\"EIGHTEEN\",\"customChallengeRatingDescription\":\"3/8 (300 XP)\",\"customProficiencyBonus\":2,\"telepathyRange\":30,\"understandsButDescription\":\"none\",\"senses\":[\"vision 60 ft.\"],\"skills\":[{\"name\":\"athletics\",\"abilityScore\":\"STRENGTH\",\"advantageType\":\"ADVANTAGE\",\"proficiencyType\":\"EXPERTISE\"}],\"damageImmunities\":[\"fire\"],\"damageResistances\":[\"thunder\"],\"damageVulnerabilities\":[\"ice\"],\"conditionImmunities\":[\"dumbfounded\"],\"languages\":[{\"mName\":\"English\",\"mSpeaks\":true}],\"abilities\":[{\"name\":\"ability name\",\"description\":\"ability description\"}],\"actions\":[{\"name\":\"action name\",\"description\":\"action description\"}],\"reactions\":[{\"name\":\"reaction name\",\"description\":\"reaction description\"}],\"lairActions\":[{\"name\":\"lair action name\",\"description\":\"lair action description\"}],\"legendaryActions\":[{\"name\":\"legendary action name\",\"description\":\"legendary action description\"}],\"regionalActions\":[{\"name\":\"regional action name\",\"description\":\"regional action name\"}]}]";
        String actualJson = ListOfMonstersConverter.fromListOfMonsters(monsters);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void listOfOneMonster_deserializes() {
        Monster expectedMonster = getTestMonster();
        String json = "[{\"id\":\"00000000-0000-0000-0000-000000000001\",\"name\":\"a name\",\"size\":\"large\",\"type\":\"lazy\",\"subtype\":\"sub\",\"alignment\":\"align\",\"strengthScore\":8,\"strengthSavingThrowAdvantage\":\"ADVANTAGE\",\"strengthSavingThrowProficiency\":\"PROFICIENT\",\"dexterityScore\":12,\"dexteritySavingThrowAdvantage\":\"DISADVANTAGE\",\"dexteritySavingThrowProficiency\":\"EXPERTISE\",\"constitutionScore\":14,\"constitutionSavingThrowAdvantage\":\"NONE\",\"constitutionSavingThrowProficiency\":\"NONE\",\"intelligenceScore\":16,\"intelligenceSavingThrowAdvantage\":\"NONE\",\"intelligenceSavingThrowProficiency\":\"EXPERTISE\",\"wisdomScore\":18,\"wisdomSavingThrowAdvantage\":\"ADVANTAGE\",\"wisdomSavingThrowProficiency\":\"PROFICIENT\",\"charismaScore\":6,\"charismaSavingThrowAdvantage\":\"DISADVANTAGE\",\"charismaSavingThrowProficiency\":\"NONE\",\"armorType\":\"NATURAL_ARMOR\",\"shieldBonus\":3,\"naturalArmorBonus\":4,\"otherArmorDescription\":\"other\",\"hitDice\":4,\"hasCustomHP\":false,\"customHPDescription\":\"11 hp\",\"walkSpeed\":5,\"burrowSpeed\":10,\"climbSpeed\":15,\"flySpeed\":20,\"canHover\":false,\"swimSpeed\":25,\"hasCustomSpeed\":false,\"customSpeedDescription\":\"speed\",\"challengeRating\":\"EIGHTEEN\",\"customChallengeRatingDescription\":\"3/8 (300 XP)\",\"customProficiencyBonus\":2,\"telepathyRange\":30,\"understandsButDescription\":\"none\",\"senses\":[\"vision 60 ft.\"],\"skills\":[{\"name\":\"athletics\",\"abilityScore\":\"STRENGTH\",\"advantageType\":\"ADVANTAGE\",\"proficiencyType\":\"EXPERTISE\"}],\"damageImmunities\":[\"fire\"],\"damageResistances\":[\"thunder\"],\"damageVulnerabilities\":[\"ice\"],\"conditionImmunities\":[\"dumbfounded\"],\"languages\":[{\"mName\":\"English\",\"mSpeaks\":true}],\"abilities\":[{\"name\":\"ability name\",\"description\":\"ability description\"}],\"actions\":[{\"name\":\"action name\",\"description\":\"action description\"}],\"reactions\":[{\"name\":\"reaction name\",\"description\":\"reaction description\"}],\"lairActions\":[{\"name\":\"lair action name\",\"description\":\"lair action description\"}],\"legendaryActions\":[{\"name\":\"legendary action name\",\"description\":\"legendary action description\"}],\"regionalActions\":[{\"name\":\"regional action name\",\"description\":\"regional action name\"}]}]";
        List<Monster> monsters = null;
        monsters = ListOfMonstersConverter.listOfMonstersFromString(json);
        assertNotNull(monsters);
        assertEquals(1, monsters.size());
        Monster actualMonster = monsters.get(0);
        //assertEquals(expectedMonster, actualMonster);
        assertTrue(expectedMonster.equals(actualMonster));
    }

    private Monster getTestMonster() {
        Monster monster = new Monster();
        // TODO: set monster fields
        monster.id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        monster.name = "a name";
        monster.size = "large";
        monster.type = "lazy";
        monster.subtype = "sub";
        monster.alignment = "align";
        monster.strengthScore = 8;
        monster.strengthSavingThrowAdvantage = AdvantageType.ADVANTAGE;
        monster.strengthSavingThrowProficiency = ProficiencyType.PROFICIENT;
        monster.dexterityScore = 12;
        monster.dexteritySavingThrowAdvantage = AdvantageType.DISADVANTAGE;
        monster.dexteritySavingThrowProficiency = ProficiencyType.EXPERTISE;
        monster.constitutionScore = 14;
        monster.constitutionSavingThrowAdvantage = AdvantageType.NONE;
        monster.constitutionSavingThrowProficiency = ProficiencyType.NONE;
        monster.intelligenceScore = 16;
        monster.intelligenceSavingThrowAdvantage = AdvantageType.NONE;
        monster.intelligenceSavingThrowProficiency = ProficiencyType.EXPERTISE;
        monster.wisdomScore = 18;
        monster.wisdomSavingThrowAdvantage = AdvantageType.ADVANTAGE;
        monster.wisdomSavingThrowProficiency = ProficiencyType.PROFICIENT;
        monster.charismaScore = 6;
        monster.charismaSavingThrowAdvantage = AdvantageType.DISADVANTAGE;
        monster.charismaSavingThrowProficiency = ProficiencyType.NONE;
        monster.armorType = ArmorType.NATURAL_ARMOR;
        monster.shieldBonus = 3;
        monster.naturalArmorBonus = 4;
        monster.otherArmorDescription = "other";
        monster.hitDice = 4;
        monster.hasCustomHP = false;
        monster.customHPDescription = "11 hp";
        monster.walkSpeed = 5;
        monster.burrowSpeed = 10;
        monster.climbSpeed = 15;
        monster.flySpeed = 20;
        monster.canHover = false;
        monster.swimSpeed = 25;
        monster.hasCustomSpeed = false;
        monster.customSpeedDescription = "speed";
        monster.challengeRating = ChallengeRating.EIGHTEEN;
        monster.customChallengeRatingDescription = "3/8 (300 XP)";
        monster.customProficiencyBonus = 2;
        monster.telepathyRange = 30;
        monster.understandsButDescription = "none";
        monster.senses.add("vision 60 ft.");
        monster.skills.add(new Skill("athletics", AbilityScore.STRENGTH, AdvantageType.ADVANTAGE, ProficiencyType.EXPERTISE));
        monster.damageImmunities.add("fire");
        monster.damageResistances.add("thunder");
        monster.damageVulnerabilities.add("ice");
        monster.conditionImmunities.add("dumbfounded");
        monster.languages.add(new Language("English", true));
        monster.abilities.add(new Trait("ability name", "ability description"));
        monster.actions.add(new Trait("action name", "action description"));
        monster.reactions.add(new Trait("reaction name", "reaction description"));
        monster.lairActions.add(new Trait("lair action name", "lair action description"));
        monster.legendaryActions.add(new Trait("legendary action name", "legendary action description"));
        monster.regionalActions.add(new Trait("regional action name", "regional action name"));

        return monster;
    }
}
