package com.stepstone.training.arena;
public abstract class Creature implements Fightable {

    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
    private Integer lifePoints;
    private CreatureType type;

    public Creature(Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints, CreatureType type) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.initiative = initiative;
        this.velocity = velocity;
        this.endurance = endurance;
        this.numberOfAttacks = numberOfAttacks;
        this.numberOfDodges = numberOfDodges;
        this.lifePoints = lifePoints;
        this.type = type;
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

    public int attack(Creature creature){

        int potentialInjury = 0;
        if (this.getDexterity() > CreaturesFactory.randomCreatureValue(1, 10)){
            potentialInjury = this.getStrength() + CreaturesFactory.randomCreatureValue(1, 3);
        }
        if (potentialInjury > 0) {
            System.out.println("Attack ended succesfully");
            System.out.println("Potential Injury: " + potentialInjury);
        }
        else {
            System.out.println("Attack failed");
        }

        return potentialInjury;
    }

    public void dodge(int injury, Creature creature) {

        boolean dodgeSuccess = false;
        int realInjury = 0;
        //if (creature.getInitiative() > CreaturesFactory.randomCreatureValue(1, 10)){
        if (false){
            dodgeSuccess = true;
        }
        else {
            realInjury = attack(creature) - this.getEndurance();
        }

        this.lifePoints = this.lifePoints - realInjury;

        if (dodgeSuccess) {
                System.out.println("Dodge ended succesfully");
        }
        else {
            System.out.println("Remaining life points: " + this.getLifePoints());
        }

        if (this.getLifePoints() <= 0) {
            System.out.println(this.getType() + " is dead");
        }

    }

}
