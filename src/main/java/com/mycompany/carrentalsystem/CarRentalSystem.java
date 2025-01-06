/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.carrentalsystem;

/**
 *
 * @author Aiman Hakimi
 */
public class CarRentalSystem {

    public static void displayWelcome(){ //ALFYAN SHAWN BIN JASNI (DISPLAY WELCOME MESSAGE)
        // Code block for displaying the welcome message
        System.out.println("WELCOME TO THE CAR RENTAL MANAGEMENT SYSTEM ");
        System.out.println("===========================================");
        System.out.println("WE ARE HERE TO MAKE RENTING CARS EASIER ");
        System.out.println("===========================================\n");
    }

    public static void carList(){

        String carName[] = {"Toyota Hilux", "Toyota Hilux", "Perodua Bezza", "Perodua Bezza", "Perodua Myvi",
                     "Proton Saga", "Proton Saga", "Toyota Camry", "Perodua Alza", "Perodua Alza"};

        int carModel[] = {2017, 2014, 2021, 2022, 2024,
                          2016, 2023, 2023, 2018, 2020};

        String carPlate[] = {"SAB 4160", "SAB 4802", "WRU 2983", "JHR 140", "KLT 9716", 
                             "PRL 3311", "MLK 3945", "WJT 7061", "NGR 1522", "SRW 7117"};

        String carColour[] = {"White", "Dark Grey", "Bronze", "Black", "Silver",
                              "Dark Grey", "Red", "Black", "Silver", "White"};

        int carPrice[] = {15, 13, 7, 8, 9,
                             8, 10, 12, 12, 13};

        System.out.println("These are the available cars at the time !\n");

        System.out.println("CAR NAME        MODEL   NO PLATE   COLOUR     RATE/HOUR");
        System.out.println("-------------------------------------------------------");

        for(int i = 0 ; i < carName.length ; i++){
            System.out.printf("%-15s %-7d %-10s %-10s RM %2d/hour \n",carName[i] ,carModel[i] ,carPlate[i] ,carColour[i], carPrice[i] );
        }

    }
    

    public static void main(String[] args) {

        displayWelcome(); //ALFYAN SHAWN BIN JASNI 2417841 

        carList(); //ALFYAN SHAWN BIN JASNI 2417841

        /* 
        System.out.println("Hello World!");
        System.out.println("New Feature");
        System.out.println("Shawn was here");
        System.out.println("Main Edit");
        System.out.println("Alternate Branch");
        */
    }

    public static void AddRentalInterface(){
        System.out.println("========== ADD CAR RENTAL ==========");
        System.out.println("Car Rental Feature");
    }

}
