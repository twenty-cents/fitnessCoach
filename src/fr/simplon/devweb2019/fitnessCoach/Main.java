package fr.simplon.devweb2019.fitnessCoach;

import fr.simplon.devweb2019.fitnessCoach.ihm.Menu;

public class Main {

    public static void main(String[] args) {
        // Run the main menu
        if(args.length == 0){
            args = new String[1];
            String cwd = System.getProperty("user.dir");
            //System.out.println("Current working directory : " + cwd);
            args[0] = cwd + "/sets/bernard.csv";
        }
        Menu menu = new Menu(args[0]);
    }
}