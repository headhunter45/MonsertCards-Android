package com.majinnaibu.monstercards.models;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.majinnaibu.monstercards.data.enums.AbilityScore;
import com.majinnaibu.monstercards.data.enums.AdvantageType;
import com.majinnaibu.monstercards.data.enums.ProficiencyType;
import com.majinnaibu.monstercards.utils.Hasher;

import java.util.Objects;

@SuppressLint("DefaultLocale")
public class Skill implements Comparable<Skill> {

    public String name;
    public AbilityScore abilityScore;
    public AdvantageType advantageType;
    public ProficiencyType proficiencyType;

    public Skill(String name, AbilityScore abilityScore) {
        this(name, abilityScore, AdvantageType.NONE, ProficiencyType.PROFICIENT);
    }

    public Skill(String name, AbilityScore abilityScore, AdvantageType advantageType) {
        this(name, abilityScore, advantageType, ProficiencyType.PROFICIENT);
    }

    public Skill(String name, AbilityScore abilityScore, AdvantageType advantageType, ProficiencyType proficiencyType) {
        this.name = name;
        this.abilityScore = abilityScore;
        this.advantageType = advantageType;
        this.proficiencyType = proficiencyType;
    }

    public int getSkillBonus(Monster monster) {
        int modifier = monster.getAbilityModifier(abilityScore);
        switch (proficiencyType) {
            case PROFICIENT:
                return modifier + monster.getProficiencyBonus();
            case EXPERTISE:
                return modifier + monster.getProficiencyBonus() * 2;
            case NONE:
            default:
                return modifier;
        }
    }

    public String getText(Monster monster) {
        int bonus = getSkillBonus(monster);

        return String.format(
                "%s%s %+d%s",
                name.charAt(0),
                name.substring(1),
                bonus,
                advantageType == AdvantageType.ADVANTAGE ? " A" : advantageType == AdvantageType.DISADVANTAGE ? " D" : ""
        );
    }

    @Override
    public int compareTo(Skill other) {
        // TODO: Make this work if other is null.
        // TODO: Make this work if either name is null.
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Skill)) {
            return false;
        }
        Skill otherSkill = (Skill) obj;
        if (!Objects.equals(this.name, otherSkill.name)) {
            return false;
        }
        if (this.abilityScore != otherSkill.abilityScore) {
            return false;
        }
        if (this.advantageType != otherSkill.advantageType) {
            return false;
        }
        if (this.proficiencyType != otherSkill.proficiencyType) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        Hasher hasher = new Hasher(41, 7);
        hasher.combine(abilityScore);
        hasher.combine(advantageType);
        hasher.combine(name);
        hasher.combine(proficiencyType);
        return hasher.total();
    }
}
