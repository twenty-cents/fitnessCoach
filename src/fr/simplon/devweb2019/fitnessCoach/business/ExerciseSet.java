package fr.simplon.devweb2019.fitnessCoach.business;

import java.util.Objects;

public class ExerciseSet {

    private int repetitions = 0;
    private float weight = 0;

    /**
     * Constructor
     * @param repetitions : the number of repetitions
     * @param weight : the weight lifted
     */
    public ExerciseSet(int repetitions, float weight){
        this.repetitions = repetitions;
        this.weight = weight;
    }

    /**
     * Check if two exercise set objects are equals
     * @param o : the exercise set to compare
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseSet)) return false;
        ExerciseSet that = (ExerciseSet) o;
        return getRepetitions() == that.getRepetitions() &&
                Float.compare(that.getWeight(), getWeight()) == 0;
    }

    /**
     * Calculate the hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRepetitions(), getWeight());
    }

    /**
     * Get the String representation of an exercise set
     * @return
     */
    @Override
    public String toString() {
        return repetitions + ";" + weight;
    }

    /**
     * Get the number of repetitions
     * @return
     */
    public int getRepetitions() {
        return repetitions;
    }

    /**
     * Set the number of repetitions
     * @param repetitions
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * Get the weight lifted
     * @return
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Set the weight lifted
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
}