package fr.simplon.devweb2019.fitnessCoach.ihm;

import fr.simplon.devweb2019.fitnessCoach.business.Exercise;
import fr.simplon.devweb2019.fitnessCoach.business.ExercisePerformanceCalculator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final String applicationTitle = "Fitness Coach";
    private final String inputMessage = "Entrez votre choix : ";
    private final String errorMessage = "Saisie incorrecte, veuillez ôter vos mouffles...";
    private final String errorMessageNumericPositive = "Le nombre saisi doit être supérieur à zéro.";
    private final String switchOptionErrorMessage = "Cas non prévu, veuillez contacter le support informatique";

    private final String averageWeightLifted = "Poids moyen levé : ";
    private final String medianWeightLifted = "Poids médian levé : ";
    private final String maximumWeightLifted = "Poids maximum levé : ";

    private final String averageRepetitions = "Nombre moyen de répétitions : ";
    private final String medianRepetitions = "Nombre médian de répétitions : ";
    private final String maximumRepetitions = "Nombre maximum de répétitions : ";

    private final String weightUnit = " kg";

    private final String addSetExercise = "Nom de l'exercice : ";
    private final String addSetRepetitions = "Nombre de répétition(s) : ";
    private final String addSetWeight = "Poids levé : ";

    private static ArrayList<String> mainMenu;

    static {
        mainMenu = new ArrayList<String>();
        mainMenu.add("Ajouter un set");
        mainMenu.add("Afficher les performances sur un exercice");
        mainMenu.add("Quitter le programme");
    }

    private static ArrayList<String> performanceMenu;
    static {
        performanceMenu = new ArrayList<>();
        performanceMenu.add("Stats de poids (/ répétitions)");
        performanceMenu.add("Stats de nombre de répétitions");
        performanceMenu.add("Stats de poids (/ set)");
    }

    static private Scanner sc;
    static {
        sc = new Scanner(System.in);
    }

    static private ExercisePerformanceCalculator performanceCalculator;
    static {
        performanceCalculator = new ExercisePerformanceCalculator();
    }

    private String pathExercises;

    /**
     * Constructor
     */
    public Menu(String pathExercises) {
        this.pathExercises = pathExercises;
        executeMenuMain();
    }

    /**
     * Run the main menu
     */
    private void executeMenuMain() {
        boolean oneMore = true;

        while (oneMore) {
            // Display the main menu + input an option
            int option = displayMenu(applicationTitle, mainMenu);

            switch (option) {
                case 1:
                    addSet();
                    break;
                case 2:
                    executeMenuPerformance();
                    break;
                case 3:
                    oneMore = false;
                    break;
                default:
                    System.out.println(switchOptionErrorMessage);
            }
        }

        // Exit application
        System.out.println("Au revoir!");
        System.exit(0);
    }

    /**
     * Add a new set
     */
    private void addSet(){
        String ex = inputString(addSetExercise);
        int repetitions = inputInteger(addSetRepetitions);
        float weight = inputFloat(addSetWeight);

        try {
            performanceCalculator.addSet(pathExercises,
                    ex,
                    repetitions,
                    weight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the performance menu
     */
    private void executeMenuPerformance(){
        // Select an exercise
        Exercise exercise = executeMenuSelectExercise();

        // display performance menu & select an performance option to calculate
        int option = displayMenu(applicationTitle, performanceMenu);

        switch(option){
            case 1:
                executeOptionStatisticsWeightByRepetition(exercise);
                break;
            case 2:
                executeOptionStatisticsCountByRepetitions(exercise);
                break;
            case 3:
                executeOptionStatisticsWeightBySet(exercise);
                break;
            default:
                System.out.println(switchOptionErrorMessage);
        }

    }

    /**
     * Select an exercise
     * @return
     */
    private Exercise executeMenuSelectExercise(){
        ArrayList<Exercise> exercises = null;

        // Load the exercises file
        try {
            exercises = performanceCalculator.loadSets(pathExercises);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Extract exercises name to build the performance menu
        ArrayList<String> options = new ArrayList<>();
        for(Exercise ex : exercises){
            options.add(ex.getName());
        }

        // Display the menu & select an option
        int option = displayMenu(applicationTitle, options);

        return exercises.get(--option);
    }


    /**
     * Display weight statistics by repetitions
     * @param exercise : the exercise to analyze
     */
    private void executeOptionStatisticsWeightByRepetition(Exercise exercise){
        System.out.println(averageWeightLifted + performanceCalculator.getAverageWeightByRepetitions(exercise) + weightUnit);
        System.out.println(medianWeightLifted + performanceCalculator.getMedianWeightByRepetitions(exercise) + weightUnit);
        System.out.println(maximumWeightLifted + performanceCalculator.getMaxWeight(exercise) + weightUnit);
        System.out.println();
    }

    /**
     * Display repetitions statistics
     * @param exercise : the exercise to analyze
     */
    private void executeOptionStatisticsCountByRepetitions(Exercise exercise){
        System.out.println(averageRepetitions + performanceCalculator.getAverageRepetitions(exercise));
        System.out.println(medianRepetitions + performanceCalculator.getMedianRepetitions(exercise));
        System.out.println(maximumRepetitions + performanceCalculator.getMaxRepetitions(exercise));
        System.out.println();
    }

    /**
     * Display weight statistics by set
     * @param exercise : the exercise to analyze
     */
    private void executeOptionStatisticsWeightBySet(Exercise exercise){
        System.out.println(averageWeightLifted + performanceCalculator.getAverageWeightBySet(exercise) + weightUnit);
        System.out.println(medianWeightLifted + performanceCalculator.getMedianWeightBySet(exercise) + weightUnit);
        System.out.println(maximumWeightLifted + performanceCalculator.getMaxWeightBySet(exercise) + weightUnit);
        System.out.println();
    }

    /**
     * Display a menu
     *
     * @param titleMenu : menu title
     * @param menu      : menu options
     */
    private int displayMenu(String titleMenu, ArrayList<String> menu) {
        int selectedOption = -1;

        // Add the title
        addTitleOption(titleMenu);

        // Add the options
        for (int i = 0; i < menu.size(); i++) {
            addOption(i + 1, menu.get(i));
        }

        // Add a separator
        addTitleOption("#############");

        // Select an option
        selectedOption = selectOption(1, menu.size(), "");

        return selectedOption;
    }

    /**
     * Select an option
     *
     * @param min : min option number
     * @param max : max option number
     * @param msg : message to display
     * @return : the number of the selected option
     */
    private int selectOption(int min, int max, String msg) {
        int option = -1;

        // Default message
        if (msg.compareTo("") == 0)
            msg = inputMessage;

        do {
            try {
                System.out.print(msg);
                option = Integer.parseInt(sc.nextLine());
                if (option < min || option > max) {
                    throw new Exception("");
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
                option = -1;
            }
        } while (option == -1);

        return option;
    }

    /**
     * Input a text
     * @param inputMessage : input message to display
     * @return
     */
    private String inputString(String inputMessage){
        String text = "";

        do {
            try {
                System.out.print(inputMessage);
                text = sc.nextLine();
                if (text.compareToIgnoreCase("") <= 0) {
                    throw new Exception("");
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
                text = "";
            }
        } while (text == "");

        return text;
    }

    /**
     * Input an integer
     * @param inputMessage : input message to display
     * @return
     */
    private int inputInteger(String inputMessage){
        int res = -1;

        do {
            try {
                System.out.print(inputMessage);
                res = Integer.parseInt(sc.nextLine());
                if (res <= 0) {
                    throw new Exception("");
                }
            } catch (Exception e) {
                System.out.println(errorMessageNumericPositive);
                res = -1;
            }
        } while (res == -1);

        return res;
    }

    /**
     * Input a float
     * @param inputMessage : input message to display
     * @return
     */
    private float inputFloat(String inputMessage){
        float res = -1;

        do {
            try {
                System.out.print(inputMessage);
                res = Float.parseFloat(sc.nextLine());
                if (res <= 0) {
                    throw new Exception("");
                }
            } catch (Exception e) {
                System.out.println(errorMessageNumericPositive);
                res = -1;
            }
        } while (res == -1);

        return res;
    }

    /**
     * Display a menu title
     *
     * @param title : title to display
     */
    private void addTitleOption(String title) {
        System.out.println("-------------------- " + title + " -------------------");
    }

    /**
     * Display an option
     *
     * @param optNumber : the number of the option
     * @param optLabel  : the label of the option
     */
    private void addOption(int optNumber, String optLabel) {
        System.out.println(optNumber + ". --> " + optLabel);
    }

}