/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.carrentalsystem;

/**
 *
 * @author Aiman Hakimi
 */

import java.util.Scanner;

public class CarRentalSystem {

    public static void main(String[] args) {

// ---------------------------------------------- ALFYAN SHAWN BIN JASNI 2417841  ---------------------------------------------- 

        displayWelcome(); 

        Scanner input = new Scanner(System.in);

        String carName[] = {"Toyota Hilux", "Toyota Hilux", "Perodua Bezza", "Perodua Bezza", "Perodua Myvi",
                     "Proton Saga", "Proton Saga", "Toyota Camry", "Perodua Alza", "Perodua Alza"};

        String carModel[] = {"2017", "2014", "2021", "2022", "2024",
                          "2016", "2023", "2023", "2018", "2020"};

        String carPlate[] = {"SAB4160", "SAB4802", "WRU2983", "JHR140", "KLT9716", 
                             "PRL3311", "MLK3945", "WJT7061", "NGR1522", "SRW7117"};

        String carColour[] = {"White", "Dark Grey", "Bronze", "Black", "Silver",
                              "Dark Grey", "Red", "Black", "Silver", "White"};

        int carPrice[] = {15, 13, 7, 8, 9, 8, 10, 12, 12, 13};

        String[][] bookingDetails = new String[3][4];
        float[] bookingPrice = new float[3];        

        System.out.println("These are the available cars at the time !\n");

        System.out.println("CAR NAME        MODEL   NO PLATE   COLOUR     RATE/HOUR");
        System.out.println("-------------------------------------------------------");

        for(int i = 0 ; i < carName.length ; i++){
            System.out.printf("%-15s %-7s %-10s %-10s RM %2d/hour \n",carName[i] ,carModel[i] ,carPlate[i] ,carColour[i], carPrice[i] );
        }

        int choice;
        do {
            System.out.println("=============================================================");
            System.out.println("\n1. Add Car Rental\n2. Search Cars\n3. Edit Car Booking\n4. Remove Car Booking\n5. Display Booking");
            System.out.print("\nChoose an action (1-4) : ");
            choice = input.nextInt();
            switch(choice){
                case 1: // AIMAN HAKIMI BIN MOHD ABD RAHMAN
                    DisplayAddRental();
                    String bookingPlate = input.next();
                    int id = searchArray(bookingPlate, carPlate);
                    System.out.print("Enter the duration of your rental (hours) : ");
                    int hours = input.nextInt();
                    if(id >= 0){
                        float totalPrice = carPrice[id] * hours;
                        BookingConfirmation(carName[id], carModel[id], carPlate[id], carColour[id], totalPrice);
                        System.out.print("\nConfirm Booking? (y/n): ");
                        String confirm = input.next();
                        if(confirm.equalsIgnoreCase("y")){
                            AddBooking(carName[id], carModel[id], carPlate[id], carColour[id], totalPrice, bookingDetails, bookingPrice);
                        }
                    }else{
                        System.out.println("Invalid plate number...\n ");
                    }
                    break;
                case 2:
                    // Search Method
                    break;
                case 3:
                    // Edit Method
                    //Check For Booking Details
                    if(bookingDetails[0][0]==null){
                        System.out.println("No booking made");
                        break;
                    }
                    else{
                        displayEditRental();
                        int editOptions = input.nextInt();
                        
                        switch(editOptions){
                            case 1 ://Edit Pickup Date and Time
                                

                                break;

                            case 2 ://Edit Car Name

                                break;
                            default : 
                                System.out.println("Returning to the main menu...");
                        }
                    }
                    break;
                case 4:
                    // Remove Method
                    break;
                case 5:
                    // Display Method
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            System.out.print("\nDo you want to continue? (y/n) : ");
            String cont = input.next();
            if(cont.equalsIgnoreCase("y")){
                choice = 0;
            }
        } while (choice < 1 || choice > 5);
    }

    public static void displayWelcome(){
        System.out.println("WELCOME TO THE CAR RENTAL MANAGEMENT SYSTEM ");
        System.out.println("===========================================");
        System.out.println("WE ARE HERE TO MAKE RENTING CARS EASIER ");
        System.out.println("===========================================\n");
    }
  

// ---------------------------------------------- AIMAN HAKIMI BIN MOHD ABD RAHMAN 2413257  ---------------------------------------------- 
    
    public static void DisplayAddRental(){
        System.out.println("\n\n==================== ADD CAR RENTAL ====================");
        System.out.print("\nPlease enter the plate number of your desired car: ");
    }

    public static int searchArray(String elem, String[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if(elem.equalsIgnoreCase(array[i])){
                index = i;
            }
        }
        return index;
    }

    public static int searchArray(int elem, int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if(elem == array[i]){
                index = i;
            }
        }
        return index;
    }

    public static void AddBooking(String name, String model, String plate, String colour, float price, String[][] booking, float[] bookingPrice){
        boolean inserted = false;
        for(int i = 0; i < booking.length; i++){
            if(booking[i][0] == null){
                booking[i][0] = name;
                booking[i][1] = model;
                booking[i][2] = plate;
                bookingPrice[i] = price;
                inserted = true;
                break;
            }
        }
        if(inserted){
            System.out.println("\nBooking added successfully");
        }else{
            System.out.println("\nBooking failed you already have the max number of bookings");
        }
        
    }

    public static void BookingConfirmation(String name, String model, String plate, String colour, float price){
        System.out.println("\n\n==================== BOOKING CONFIRMATION ====================");
        System.out.println("\nCAR NAME        MODEL   NO PLATE   COLOUR     TOTAL PRICE");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-15s %-7s %-10s %-10s RM %4.2f \n",name, model, plate, colour, price);
    }
    // ---------------------------------------------- NABIL ALIF BIN AZMI 2415845  ------------------------------------------------
    public static void displayEditRental(){
        System.out.println("\n\n==================== EDIT CAR RENTAL ====================\n");
        System.out.println("Choose one of these options");
        System.out.println("1. Edit Pickup Date and Time");
        System.out.println("2. Edit Car Name");
        System.out.println("(Press any key to return to mainpage)");
    }
    public static float calculatePrice(int $carPrice, int $hours){
         return $carPrice*$hours;
    }
    
    //----------------------- AHMAD ZHARFAN SHAH BIN ROHISHAM 2414683 ------------------------------------------------------------------------




















}