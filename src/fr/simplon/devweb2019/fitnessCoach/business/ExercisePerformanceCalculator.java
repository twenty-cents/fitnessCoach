package fr.simplon.devweb2019.fitnessCoach.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ExercisePerformanceCalculator {


    public ExercisePerformanceCalculator() {
    }

    /**
     * Load exercise sets
     * @param path : path to load
     * @return : list of exercises loaded from the input filename
     * @throws FileNotFoundException
     */
    public ArrayList<Exercise> loadSets(String path) throws FileNotFoundException {
        ArrayList<Exercise> exercises = new ArrayList<>();

        // Read exercise sets file
        try (Scanner sc = new Scanner(new File(path))){
            while (sc.hasNextLine()) {
                String[] l = sc.nextLine().split(";");
                int repetitions = 0;
                float weight = 0;

                // First of all, we make sure that the line ridden is valid, of type :
                // String ; Integer ; Float
                // At least three items in the table?
                if(l.length < 3){
                    continue;
                }
                // The items 2 and 3 must be int and float
                try{
                    repetitions = Integer.parseInt(l[1]);
                    weight = Float.parseFloat(l[2]);
                }
                catch (Exception e){
                    continue;
                }

                // Ok, the line is a set
                Exercise exercise = new Exercise(l[0]);

                // is the exercise already exist in the list?
                if(exercises.contains(exercise)){
                    // Yes ->
                    // Get the existing exercise in the list
                    exercise = exercises.get(exercises.indexOf(exercise));
                    // Add a new set in the existing exercise
                    exercise.addSet(repetitions, weight);
                    // Replace the existing exercise in the list
                    exercises.set(exercises.indexOf(exercise), exercise);
                } else {
                    // No ->
                    // Add a new set in the new exercise
                    exercise.addSet(repetitions, weight);
                    // Add the new exercise in the list
                    exercises.add(exercise);
                }
            }
        }

        // Sort exercises (alphabetical)
        Collections.sort(exercises);

        return exercises;
    }

    /**
     * Calculate the average weight by set on an exercise
     * @param exercise : the exercise to analyze
     * @return : the average weight by set
     */
    public float getAverageWeightBySet(Exercise exercise){
        float averageWeight = 0;

        for(ExerciseSet set : exercise.getSets()){
            averageWeight += set.getWeight() * set.getRepetitions();
        }

        if(exercise.getSets().size() > 0){
            averageWeight = averageWeight / exercise.getSets().size();
        }

        return averageWeight;
    }

    /**
     * Calculate the median weight by set on an exercise
     * @param exercise : the exercise to analyze
     * @return : the median weight by set
     */
    public float getMedianWeightBySet(Exercise exercise){
        float medianWeight = 0;

        // get weights from all sets
        ArrayList<Float> weights = new ArrayList<>();
        for(ExerciseSet set : exercise.getSets()){
            weights.add(set.getWeight() * set.getRepetitions());
        }

        // Sort weights ascending
        Collections.sort(weights);

        // Calculate median weight
        if(weights.size() > 0){
            if(weights.size() % 2 == 0){
                // The number of values is even
                // -> the median is the half-sum of the two middle values
                medianWeight = ((weights.get((weights.size() / 2) -1)) + (weights.get(weights.size() / 2))) / 2;
            } else {
                // If the number of values is odd
                // -> the median is the middle value
                medianWeight = weights.get(weights.size() / 2);
            }
        }

        return medianWeight;
    }

    /**
     * Calculate the max weight lifted by set on an exercise
     * @param exercise : the exercise to analyze
     * @return : the max weight by set
     */
    public float getMaxWeightBySet(Exercise exercise){
        float maxWeight = 0;

        for(ExerciseSet set : exercise.getSets()){
            if(set.getWeight() > maxWeight){
                maxWeight = set.getWeight() * set.getRepetitions();
            }
        }

        return maxWeight;
    }

    /**
     * Calculate the max weight lifted on an exercise
     * @param exercise : the exercise to analyze
     * @return : the max weight lifted on an exercise
     */
    public float getMaxWeight(Exercise exercise){
        float maxWeight = 0;

        for(ExerciseSet set : exercise.getSets()){
            if(set.getWeight() > maxWeight){
                maxWeight = set.getWeight();
            }
        }

        return maxWeight;
    }

    /**
     * Calculate the average weight lifted by repetition on an exercise
     * @param exercise : the exercise to analyze
     * @return : the average weight lifted by repetition on an exercise
     */
    public float getAverageWeightByRepetitions(Exercise exercise){
        float averageWeight = 0;
        int repetitions = 0;

        for(ExerciseSet set : exercise.getSets()){
            averageWeight += set.getWeight() * set.getRepetitions();
            repetitions += set.getRepetitions();
        }

        if(repetitions > 0){
            averageWeight = averageWeight / repetitions;
        }

        return averageWeight;
    }

    /**
     * Calculate the median weight lifted vy repetition on an exercise
     * @param exercise : the exercise to analyze
     * @return : the median weight lifted vy repetition on an exercise
     */
    public float getMedianWeightByRepetitions(Exercise exercise){
        float medianWeight = 0;

        // Extract weights from all sets
        ArrayList<Float> weights = new ArrayList<>();
        for(ExerciseSet set : exercise.getSets()){
            weights.add(set.getWeight());
        }

        // Sort weights ascending
        Collections.sort(weights);

        // Calculate the median weight
        if(weights.size() > 0){
            if(weights.size() % 2 == 0){
                // Si le nombre de valeurs est pair
                // -> la médiane est la demi-somme des deux valeurs du milieu
                medianWeight = ((weights.get((weights.size() / 2) -1)) + (weights.get(weights.size() / 2)) /  2);
            } else {
                // Si le nombre de valeurs est impair
                // -> la médiane est la valeur du milieu
                medianWeight = weights.get(weights.size() / 2);
            }
        }

        return medianWeight;
    }

    /**
     * Calculate the average number of repetitions on an exercise
     * @param exercise : the exercise to analyze
     * @return : the average number of repetitions on an exercise
     */
    public float getAverageRepetitions(Exercise exercise){
        float averageRepetitions = 0;

        for(ExerciseSet set : exercise.getSets()){
            averageRepetitions += set.getRepetitions();
        }

        if(exercise.getSets().size() > 0){
            averageRepetitions = averageRepetitions / exercise.getSets().size();
        }

        return averageRepetitions;
    }

    /**
     * Calculate the median number of repetitions on an exercise
     * @param exercise : the exercise to analyze
     * @return : the median number of repetitions on an exercise
     */
    public float getMedianRepetitions(Exercise exercise){
        float medianRepetitions = 0;

        // Extract the number of repetitions from all sets
        ArrayList<Integer> repetitions = new ArrayList<>();
        for(ExerciseSet set : exercise.getSets()){
            repetitions.add(set.getRepetitions());
        }

        // Sort repetitions ascending
        Collections.sort(repetitions);

        // Calculate the median number of repetitions
        if(repetitions.size() > 0){
            if(repetitions.size() % 2 == 0){
                // Si le nombre de valeurs est pair
                // -> la médiane est la demi-somme des deux valeurs du milieu
                medianRepetitions = Float.intBitsToFloat((repetitions.get((repetitions.size() / 2) -1)) + (repetitions.get(repetitions.size() / 2))) / 2;
            } else {
                // Si le nombre de valeurs est impair
                // -> la médiane est la valeur du milieu
                medianRepetitions = repetitions.get(repetitions.size() / 2);
            }
        }

        return medianRepetitions;
    }

    /**
     * Calculate the maximum number of repetitions on an exercise
     * @param exercise : the exercise to analyze
     * @return : the maximum number of repetitions on an exercise
     */
    public float getMaxRepetitions(Exercise exercise){
        float maxRepetitions = 0;

        for(ExerciseSet set : exercise.getSets()){
            if(set.getRepetitions() > maxRepetitions){
                maxRepetitions = set.getRepetitions();
            }
        }

        return maxRepetitions;
    }

}