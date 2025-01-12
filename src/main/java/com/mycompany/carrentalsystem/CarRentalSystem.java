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
        int[] priceData = new int[3];
        int addCount = 0;

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
                            priceData[addCount] = carPrice[id];
                            addCount++;
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
                        System.out.println("\nNo booking made.");
                        break;
                    }
                    else{
                        displayCurrentBooking(bookingDetails, bookingPrice);
                        displayEditRental();
                        int editOptions = input.nextInt();
                        
                        switch(editOptions){
                            case 1 : //NABIL ALIF BIN AZMI (EDIT TIME)
                                int editIDX = -1;
                                
                                do{
                                    displayCurrentBooking(bookingDetails, bookingPrice);
                                    System.out.print("\n======== EDIT DURATION OF RENTAL ========\n\n");

                                    System.out.printf("Select which booking you want to edit (1 to %d) : ",addCount);
                                    editIDX = input.nextInt();

                                    if(editIDX < 1 || editIDX > (addCount)){
                                        System.out.println("\nInvalid Input! Please try again.");
                                    }

                                }while(editIDX < 1 || editIDX > (addCount));
                                editIDX--;
                                
                                System.out.print("Enter the new duration of your rental : ");
                                hours = input.nextInt();

                                bookingPrice[editIDX] = calculatePrice(priceData[editIDX], hours);
                                System.out.println("\nCalculated Successfully!\n");

                                System.out.print("New Booking Details : ");
                                displayCurrentBooking(bookingDetails, bookingPrice);

                                break;

                            case 2 :
                                int carChangeNum = 1;
                                int newid = 0;
                                displayCurrentBooking(bookingDetails, bookingPrice);
                                System.out.print("\n=======Change Car Rental======\n\n  ");
                                int idCount=CarCount(priceData);
                                if(idCount==1){
                                    System.out.println("Only one car available, skipping selection.");
                                }else{
                                System.out.printf("Choose the car you want to change(1-%d): ", idCount); 
                                carChangeNum = input.nextInt();
                                while(carChangeNum>idCount){
                                    System.out.println("Invalid number, please re-enter: ");
                                    carChangeNum = input.nextInt();
                                }
                                }
                                String oldNumPlate = bookingDetails[carChangeNum-1][2];
                                int oldId = searchArray(oldNumPlate, carPlate);
                                int oldHours = (int) (bookingPrice[carChangeNum-1]/carPrice[oldId]);
                                
                                do{
                                System.out.print("Enter the new Car Number Plate to swap: ");
                                String SwapNumPlate = input.next();
                                newid =  searchArray(SwapNumPlate, carPlate);
                                if(newid<0){
                                    System.out.println("Invalid Number Plate! Please re-enter: ");
                                }
                                }while(newid<0);
                                
                                bookingDetails[carChangeNum-1][0] = carName[newid];
                                bookingDetails[carChangeNum-1][1] = carModel[newid];
                                bookingDetails[carChangeNum-1][2] = carPlate[newid];
                                bookingDetails[carChangeNum-1][3] = carColour[newid];
                                bookingPrice[carChangeNum-1] = carPrice[newid]*oldHours;
                                
                                displayCurrentBooking(bookingDetails, bookingPrice);
                                break;
                            default : 
                                System.out.println("Returning to the main menu...");
                        }
                    }
                    break;
                case 4:
// ---------------------------------------------- AIMAN HAFIZ BIN AZHAR 2414751 ------------------------------------------------
                if (bookingDetails[0][0] == null) {
                    System.out.println("\nNo bookings found to remove.");
                    break;
                } else {
                    displayCurrentBooking(bookingDetails, bookingPrice);
                    System.out.println("\n==================== REMOVE CAR BOOKING ====================");
                    System.out.print("Select the booking number to remove (1 to " + addCount + "): ");
                    int removeIDX = input.nextInt();

                    if (removeIDX < 1 || removeIDX > addCount) {
                        System.out.println("\nInvalid input! Returning to main menu...");
                    } else {
                        removeIDX--;
                        System.out.print("Are you sure you want to remove this booking? (y/n): ");
                        String confirmRemove = input.next();

                        if (confirmRemove.equalsIgnoreCase("y")) {
                            for (int j = 0; j < bookingDetails[removeIDX].length; j++) {
                                bookingDetails[removeIDX][j] = null;
                            }
                            bookingPrice[removeIDX] = 0;

                            
                            for (int i = removeIDX; i < bookingDetails.length - 1; i++) {
                                bookingDetails[i] = bookingDetails[i + 1];
                                bookingPrice[i] = bookingPrice[i + 1];
                            }

                            
                            for (int j = 0; j < bookingDetails[bookingDetails.length - 1].length; j++) {
                                bookingDetails[bookingDetails.length - 1][j] = null;
                            }
                            bookingPrice[bookingDetails.length - 1] = 0;

                            addCount--;
                            System.out.println("Booking removed successfully!");
                        } else {
                            System.out.println("Operation canceled. Returning to the main menu...");
                        }
                    }
                }
                            
            break;
                case 5:
                    // Display Method
                    displayCurrentBooking(bookingDetails, bookingPrice);
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

    public static void BookingConfirmation(String name, String model, String plate, String colour, float price){
        System.out.println("\n\n==================== BOOKING CONFIRMATION ====================");
        System.out.println("\nCAR NAME        MODEL   NO PLATE   COLOUR     TOTAL PRICE");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-15s %-7s %-10s %-10s RM %4.2f \n",name, model, plate, colour, price);
    }

    public static void AddBooking(String name, String model, String plate, String colour, float price, String[][] booking, float[] bookingPrice){
        boolean inserted = false;
        for(int i = 0; i < booking.length; i++){
            if(booking[i][0] == null){
                booking[i][0] = name;
                booking[i][1] = model;
                booking[i][2] = plate;
                booking[i][3] = colour;
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
    // ---------------------------------------------- NABIL ALIF BIN AZMI 2415845  ------------------------------------------------
    public static void displayCurrentBooking(String[][] booking, float[] bookingPrice){
        if(booking[0][0]==null){
            System.out.println("\nThere is no booking yet...");
        }else{
            System.out.println("\n==================== BOOKING DETAILS =====================");
            System.out.println("\n   CAR NAME        MODEL   NO PLATE   COLOUR     TOTAL PRICE");
            System.out.println("--------------------------------------------------------------");
            for(int i = 0; i<3; i++){
                if(booking[i][0]==null){
                    break;
                }
                System.out.printf("%d) %-15s %-7s %-10s %-10s RM %4.2f \n", i+1, booking[i][0], booking[i][1], booking[i][2], booking[i][3], bookingPrice[i]);
                
            }
        }
    }
    public static void displayEditRental(){
        System.out.println("\n==================== EDIT CAR RENTAL ====================\n");
        System.out.println("1. Edit Time");
        System.out.println("2. Edit Car Name");
        System.out.println("(Press any key to return to mainpage)");
        System.out.print("Choose one of these options : ");
    }
    public static float calculatePrice(int $carPrice, int $hours){
        System.out.println("\nCalculating the price...");
        return $carPrice*$hours;
    }
    
    //----------------------- AHMAD ZHARFAN SHAH BIN ROHISHAM 2414683 ------------------------------------------------------------------------

    public static int CarCount(int[] PriceArray){
        int count=0;
        for(int i=0 ; i<PriceArray.length; i++){
            if(PriceArray[i]>5)
                count++;
        }
        return count;
    }


}