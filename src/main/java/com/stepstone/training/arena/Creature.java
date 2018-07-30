package com.stepstone.training.arena;

import java.util.HashMap;
import java.util.Map;

public class Creature implements Fightable {

    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
    private Integer lifePoints;
    private CreatureType type;
    private Map<Armour, Integer> mapArmour;

    Creature() {
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

    public CreatureType getType() {
        return type;
    }

    public Map<Armour, Integer> getMapArmour() {
        return mapArmour;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", initiative=" + initiative +
                ", velocity=" + velocity +
                ", endurance=" + endurance +
                ", numberOfAttacks=" + numberOfAttacks +
                ", numberOfDodges=" + numberOfDodges +
                ", lifePoints=" + lifePoints +
                ", type='" + type + '\'' +
                '}';
    }

    public int attack(Creature assaulted){

        boolean attackSuccess = false;
        int potentialInjury = 0;
        BodyPart hitPart = null;
        try {
            hitPart = hit();
            attackSuccess = true;
        }
        catch (Exception e) {
            System.out.println(e);
        }

        if (!attackSuccess){

            int shield = CreaturesRandomizer.randomCreatureValue(1, 10);
            System.out.println("Shield: " + shield);

            if (this.getDexterity() > shield){
                attackSuccess = true;
                potentialInjury = this.getStrength() + CreaturesRandomizer.randomCreatureValue(1, 3);
            }
        }
        else {
            potentialInjury = this.getStrength() + CreaturesRandomizer.randomCreatureValue(1, 3) + hitPart.getBonus();
        }

        if (attackSuccess) {
            System.out.println("Attack ended succesfully");
            System.out.println("Potential Injury: " + potentialInjury);
        }
        else {
            System.out.println("Attack failed");
        }

        return potentialInjury;
    }

    public boolean dodge(int potentialInjury, Creature aggressor) {

        boolean dodgeSuccess = false;
        int realInjury = 0;
        int threat = CreaturesRandomizer.randomCreatureValue(1, 10);
        System.out.println("Threat: " + threat);
        if (this.getInitiative() > threat){
            dodgeSuccess = true;
        }
        else {
            realInjury = potentialInjury - this.getEndurance();
        }

        if (realInjury > 0) {
            this.lifePoints = this.lifePoints - realInjury;
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

        return (this.getLifePoints() > 0);

    }

    public BodyPart hit() throws Exception {

        boolean doHit = false;
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
            throw new Exception("Missed");
        }
        else {
            return bodyPartHit;
        }
    }

    public static CreatureBuilder builder(){
        return new CreatureBuilder();
    }

    public static class CreatureBuilder {
        private Creature creature = new Creature();

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

        public CreatureBuilder setType(CreatureType type) {
            creature.type = type;
            return this;
        }

        public CreatureBuilder setMapArmour(Map<Armour, Integer> mapArmour) {
            creature.mapArmour = mapArmour;
            return this;
        }

        public Creature build() {
            return creature;
        }

    }



}
