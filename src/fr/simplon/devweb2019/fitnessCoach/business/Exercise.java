package fr.simplon.devweb2019.fitnessCoach.business;

import java.util.ArrayList;
import java.util.Objects;

public class Exercise implements Comparable<Exercise> {

    private String name;
    private ArrayList<ExerciseSet> sets;

    /**
     * Constructor
     * @param name : the name of the exercise
     */
    public Exercise(String name){
        this.name = name;
        sets = new ArrayList<>();
    }

    /**
     * Add a set
     * @param repetitions : number of repetitions
     * @param weight : weight lifted
     */
    public void addSet(int repetitions, float weight)
    {
        sets.add(new ExerciseSet(repetitions, weight));
    }

    /**
     * Remove a set
     * @param set : set to remove
     */
    public void removeSet(ExerciseSet set){
        sets.remove(set);
    }

    /**
     * check if two Exercise objects are equals
     * Two exercises objects are equals if their names are equals
     * @param o : Exercise object to compare
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise1 = (Exercise) o;
        return Objects.equals(getName(), exercise1.getName());
    }

    /**
     * Calculate the hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    /**
     * Get the String representation of an exercise
     * @return
     */
    @Override
    public String toString() {
        return "Exercise{" +
                "exercise='" + name + '\'' +
                ", sets=" + sets +
                "}\n";
    }

    /**
     * Get the name of an exercise
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of an exercise
     * @return
     */
    public ArrayList<ExerciseSet> getSets() {
        return sets;
    }

    /**
     * Compare two exercises
     * @param exercise : the exercise to compare
     * @return
     */
    @Override
    public int compareTo(Exercise exercise) {
        return this.name.compareTo(exercise.getName());
    }
}