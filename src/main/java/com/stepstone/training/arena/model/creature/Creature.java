package com.stepstone.training.arena.model.creature;

import com.stepstone.training.arena.model.AttackResult;
import com.stepstone.training.arena.model.BodyPart;
import com.stepstone.training.arena.model.Fightable;
import com.stepstone.training.arena.model.ProtectionItem;
import com.stepstone.training.arena.util.CreaturesRandomizer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Creature implements Fightable {

    private String name;
    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
    private Integer lifePoints;
    private Integer initialLifePoints;
    private CreatureType type;
    private Map<ProtectionItem, Integer> mapProtection;

    Creature() {
    }

    public String getName() {
        return name;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public Integer getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public Integer getNumberOfDodges() {
        return numberOfDodges;
    }

    public Integer getLifePoints() {
        return lifePoints;
    }

    public Integer getInitialLifePoints() {
        return initialLifePoints;
    }

    public CreatureType getType() {
        return type;
    }

    public Map<ProtectionItem, Integer> getMapProtection() {
        return mapProtection;
    }

    public Integer getSumPoints(){
        return  strength + dexterity + initiative + endurance;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name=" + name +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", initiative=" + initiative +
                ", velocity=" + velocity +
                ", endurance=" + endurance +
                ", numberOfAttacks=" + numberOfAttacks +
                ", numberOfDodges=" + numberOfDodges +
                ", initialLifePoints=" + initialLifePoints +
                ", lifePoints=" + lifePoints +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public AttackResult attack() {

        boolean attackSuccess = false;
        int potentialDamage = 0;
        BodyPart hitPart = hit().orElse(BodyPart.MISSED);
        if (hitPart != BodyPart.MISSED){
            attackSuccess = true;
        }
        else{
            System.out.println("Missed!");
        }

        if (!attackSuccess){

            int shield = CreaturesRandomizer.randomCreatureValue(1, 10);
            System.out.println("Shield: " + shield);

            if (this.getDexterity() > shield){
                attackSuccess = true;
                potentialDamage = this.getStrength() + CreaturesRandomizer.randomCreatureValue(1, 3);
            }
        }
        else {
            potentialDamage = this.getStrength() + CreaturesRandomizer.randomCreatureValue(1, 3) + hitPart.getBonus();
        }

        if (attackSuccess) {
            System.out.println("Attack ended succesfully");
            System.out.println("Potential Damage: " + potentialDamage);
        }
        else {
            System.out.println("Attack failed");
        }

        return new AttackResult(hitPart, 0, potentialDamage);
    }

    public AttackResult dodge(AttackResult attackResult) {

        boolean dodgeSuccess = false;
        int effectiveDamage = 0;
        int protection = calculateProtection(attackResult);

        int threat = CreaturesRandomizer.randomCreatureValue(1, 10);
        System.out.println("Threat: " + threat);
        if (this.getInitiative() > threat){
            dodgeSuccess = true;
        }
        else {
            effectiveDamage = attackResult.getPotentialDamage() - protection - this.getEndurance();
        }

        if (effectiveDamage > 0) {
            attackResult.setEffectiveDamage(effectiveDamage);
            this.lifePoints = this.lifePoints - effectiveDamage;
        }

        if (dodgeSuccess) {
                System.out.println("Dodge ended succesfully");
        }
        else {
            System.out.println("Remaining life points: " + this.getLifePoints());
        }

        if (this.getLifePoints() <= 0) {
            System.out.println(this.getType() + " is dead");
        }

        return attackResult;

    }

    public boolean isAlive() {
        return lifePoints > 0;
    }

    public Optional<BodyPart> hit() {

        int result = 0;
        BodyPart bodyPartHit = null;

        Map<Integer, BodyPart> tableBodyParts = new HashMap<Integer, BodyPart>();

        for (int i = 1; i <= 100; i++){
            tableBodyParts.put(i, null);
        }

        int j = 1;
        for (BodyPart bodyPart:BodyPart.values()){
            for (int k = 1; k <= bodyPart.getProbability(); k++) {
                tableBodyParts.put(j, bodyPart);
                j++;
            }
        }

        result = CreaturesRandomizer.randomCreatureValue(1, 100);

        bodyPartHit = tableBodyParts.get(result);

        if (bodyPartHit == null){
            return Optional.empty();
        }
        else {
            return Optional.of(bodyPartHit);
        }
    }

    public int calculateProtection(AttackResult attackResult){
        int protection = 0;
        BodyPart bodyPart = attackResult.getHitBodyPart();

        if (bodyPart != BodyPart.MISSED) {
            ProtectionItem protectionItem = bodyPart.getProtectionItem();
            System.out.println("protectionItem: " + protectionItem);
            if (getMapProtection().get(protectionItem) != null && getMapProtection().get(protectionItem) > 0){
                mapProtection.put(attackResult.getHitBodyPart().getProtectionItem(), getMapProtection().get(attackResult.getHitBodyPart().getProtectionItem()) - 1);
                protection = ProtectionItem.valueOf(attackResult.getHitBodyPart().getProtectionItem().toString()).getMaximum();
            }
        }

        return protection;

    }

    public static CreatureBuilder builder(){
        return new CreatureBuilder();
    }

    public static class CreatureBuilder {
        private Creature creature = new Creature();

        public CreatureBuilder setName(String name) {
            creature.name = name;
            return this;
        }

        public CreatureBuilder setStrength(Integer strength) {
            creature.strength = strength;
            return this;
        }

        public CreatureBuilder setDexterity(Integer dexterity) {
            creature.dexterity = dexterity;
            return this;
        }

        public CreatureBuilder setInitiative(Integer initiative) {
            creature.initiative = initiative;
            return this;
        }

        public CreatureBuilder setVelocity(Integer velocity) {
            creature.velocity = velocity;
            return this;
        }

        public CreatureBuilder setEndurance(Integer endurance) {
            creature.endurance = endurance;
            return this;
        }

        public CreatureBuilder setNumberOfAttacks(Integer numberOfAttacks) {
            creature.numberOfAttacks = numberOfAttacks;
            return this;
        }

        public CreatureBuilder setNumberOfDodges(Integer numberOfDodges) {
            creature.numberOfDodges = numberOfDodges;
            return this;
        }

        public CreatureBuilder setLifePoints(Integer lifePoints) {
            creature.lifePoints = lifePoints;
            return this;
        }

        public CreatureBuilder setInitialLifePoints(Integer initialLifePoints) {
            creature.initialLifePoints = initialLifePoints;
            return this;
        }

        public CreatureBuilder setType(CreatureType type) {
            creature.type = type;
            return this;
        }

        public CreatureBuilder setMapProtection(Map<ProtectionItem, Integer> mapProtection) {
            creature.mapProtection = mapProtection;
            return this;
        }

        public Creature build() {
            return creature;
        }

    }

    public Creature copy() {
        CreatureBuilder creatureBuilder = builder();
        creatureBuilder.setName(this.name);
        creatureBuilder.setStrength(this.strength);
        creatureBuilder.setDexterity(this.dexterity);
        creatureBuilder.setInitiative(this.initiative);
        creatureBuilder.setVelocity(this.velocity);
        creatureBuilder.setEndurance(this.endurance);
        creatureBuilder.setInitialLifePoints(this.initialLifePoints);
        creatureBuilder.setLifePoints(this.initialLifePoints);
        creatureBuilder.setMapProtection(this.mapProtection);
        creatureBuilder.setType(this.type);
        return creatureBuilder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creature)) return false;

        Creature creature = (Creature) o;

        return getName().equals(creature.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
