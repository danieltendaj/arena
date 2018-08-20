package com.stepstone.training.arena;

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
                ", endurance=" + endurance +
                ", numberOfAttacks=" + numberOfAttacks +
                ", numberOfDodges=" + numberOfDodges +
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
            //System.out.println("Missed!");
        }

        if (!attackSuccess){

            int shield = CreaturesRandomizer.randomCreatureValue(1, 10);
            //System.out.println("Shield: " + shield);

            if (this.getDexterity() > shield){
                attackSuccess = true;
                potentialDamage = this.getStrength() + CreaturesRandomizer.randomCreatureValue(1, 3);
            }
        }
        else {
            potentialDamage = this.getStrength() + CreaturesRandomizer.randomCreatureValue(1, 3) + hitPart.getBonus();
        }

        if (attackSuccess) {
            //System.out.println("Attack ended succesfully");
            //System.out.println("Potential Damage: " + potentialDamage);
        }
        else {
            //System.out.println("Attack failed");
        }

        return new AttackResult(hitPart, 0, potentialDamage);
    }

    public AttackResult dodge(AttackResult attackResult) {

        boolean dodgeSuccess = false;
        int effectiveDamage = 0;
        int protection = calculateProtection(attackResult);

        int threat = CreaturesRandomizer.randomCreatureValue(1, 10);
        //System.out.println("Threat: " + threat);
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
                //System.out.println("Dodge ended succesfully");
        }
        else {
            //System.out.println("Remaining life points: " + this.getLifePoints());
        }

        if (this.getLifePoints() <= 0) {
            //System.out.println(this.getType() + " is dead");
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
            if (getMapProtection().get(bodyPart.getProtectionItem()) > 0){
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
            creature.initialLifePoints = lifePoints;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creature)) return false;

        Creature creature = (Creature) o;

        if (getName() != null ? !getName().equals(creature.getName()) : creature.getName() != null) return false;
        if (getStrength() != null ? !getStrength().equals(creature.getStrength()) : creature.getStrength() != null)
            return false;
        if (getDexterity() != null ? !getDexterity().equals(creature.getDexterity()) : creature.getDexterity() != null)
            return false;
        if (getInitiative() != null ? !getInitiative().equals(creature.getInitiative()) : creature.getInitiative() != null)
            return false;
        if (getVelocity() != null ? !getVelocity().equals(creature.getVelocity()) : creature.getVelocity() != null)
            return false;
        if (getEndurance() != null ? !getEndurance().equals(creature.getEndurance()) : creature.getEndurance() != null)
            return false;
        if (getNumberOfAttacks() != null ? !getNumberOfAttacks().equals(creature.getNumberOfAttacks()) : creature.getNumberOfAttacks() != null)
            return false;
        if (getNumberOfDodges() != null ? !getNumberOfDodges().equals(creature.getNumberOfDodges()) : creature.getNumberOfDodges() != null)
            return false;
        if (getInitialLifePoints() != null ? !getInitialLifePoints().equals(creature.getInitialLifePoints()) : creature.getInitialLifePoints() != null)
            return false;
        if (getType() != creature.getType()) return false;
        return getMapProtection() != null ? getMapProtection().equals(creature.getMapProtection()) : creature.getMapProtection() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getStrength() != null ? getStrength().hashCode() : 0);
        result = 31 * result + (getDexterity() != null ? getDexterity().hashCode() : 0);
        result = 31 * result + (getInitiative() != null ? getInitiative().hashCode() : 0);
        result = 31 * result + (getVelocity() != null ? getVelocity().hashCode() : 0);
        result = 31 * result + (getEndurance() != null ? getEndurance().hashCode() : 0);
        result = 31 * result + (getNumberOfAttacks() != null ? getNumberOfAttacks().hashCode() : 0);
        result = 31 * result + (getNumberOfDodges() != null ? getNumberOfDodges().hashCode() : 0);
        result = 31 * result + (getInitialLifePoints() != null ? getInitialLifePoints().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getMapProtection() != null ? getMapProtection().hashCode() : 0);
        return result;
    }
}
