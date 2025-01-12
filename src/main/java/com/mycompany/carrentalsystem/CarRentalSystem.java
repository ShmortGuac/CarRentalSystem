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
        
        // Store every car's data 
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

        // Define arrays for users own booking inventory
        String[][] bookingDetails = new String[3][4]; 
        float[] bookingPrice = new float[3];    
        
        // Display car details
        System.out.println("These are the available cars at the time !\n");
        System.out.println("CAR NAME        MODEL   NO PLATE   COLOUR     RATE/HOUR");
        System.out.println("-------------------------------------------------------");
        for(int i = 0 ; i < carName.length ; i++){
            System.out.printf("%-15s %-7s %-10s %-10s RM %2d/hour \n",carName[i] ,carModel[i] ,carPlate[i] ,carColour[i], carPrice[i] );
        }

        int choice;
        do {
            // Display Main Menu
            System.out.println("=============================================================");
            System.out.println("\n1. Add Car Rental\n2. Search Cars\n3. Edit Car Booking\n4. Remove Car Booking\n5. Display Booking");
            // Prompt the user to choose an action
            System.out.print("\nChoose an action (1-5) : ");
            choice = input.nextInt();

            // Switch statement for every action
            switch(choice){
                case 1: // AIMAN HAKIMI BIN MOHD ABD RAHMAN

                    // Prompt the user to input the car's number plate for booking
                    DisplayAddRental();
                    String bookingPlate = input.next();

                    // Search for the car plate, id = index position
                    int id = searchArray(bookingPlate, carPlate);
                    
                    // Prompt the user to input the rental duration in hours
                    System.out.print("Enter the duration of your rental (hours) : ");
                    int hours = input.nextInt();
                    
                    // Check if the car plate number exists in the system
                    if(id >= 0){
                        float totalPrice = carPrice[id] * hours; // Calculate the total rental price
                        
                        // Prompt user to confirm booking
                        BookingConfirmation(carName[id], carModel[id], carPlate[id], carColour[id], totalPrice);
                        System.out.print("\nConfirm Booking? (y/n): ");
                        String confirm = input.next();
                        if(confirm.equalsIgnoreCase("y")){
                            // Add the booking details to the system
                            AddBooking(carName[id], carModel[id], carPlate[id], carColour[id], totalPrice, bookingDetails, bookingPrice);
                            priceData[addCount] = carPrice[id]; // Store the car's price for edit, remove and search purpose
                            addCount++; // Add to total booking count
                        }
                    }else{
                        System.out.println("Invalid plate number...\n ");
                    }

                    break;
                case 2:
                    searchCar(carName, carModel, carPlate, carColour);
                    break;
                case 3:
                    // Edit Method
                    // Check for booking details 
                    if(bookingDetails[0][0]==null){
                        System.out.println("\nNo booking made.");
                        break;
                    }
                    else{
                        // Display current bookings to the user 
                        displayCurrentBooking(bookingDetails, bookingPrice);

                        // Prompt user for edit options
                        displayEditRental();
                        int editOptions = input.nextInt();
                        
                        switch(editOptions){
                            case 1 : // NABIL ALIF BIN AZMI (EDIT DURATION OF RENTAL)
                                // Declare a variable to store the index of the booking to be edited
                                int editIDX = -1;
                                
                                // Loop to ensure a valid booking index is selected
                                do{
                                    // Display the current bookings and their details
                                    displayCurrentBooking(bookingDetails, bookingPrice);

                                    // Prompt the user to select which booking they want to edit
                                    System.out.print("\n======== EDIT DURATION OF RENTAL ========\n\n");
                                    System.out.printf("Select which booking you want to edit (1 to %d) : ", addCount);
                                    editIDX = input.nextInt();

                                    // Validate the input; if invalid, display an error message
                                    if(editIDX < 1 || editIDX > addCount){
                                        System.out.println("\nInvalid Input! Please try again.");
                                    }

                                }while(editIDX < 1 || editIDX > addCount); // Repeat until a valid index is entered
                                editIDX--; // Adjust the index to match zero-based array indexing
                                
                                // Prompt the user to enter the new rental duration
                                System.out.print("Enter the new duration of your rental : ");
                                hours = input.nextInt();
                                
                                // Recalculate the booking price based on the new duration
                                bookingPrice[editIDX] = calculatePrice(priceData[editIDX], hours);
                                System.out.println("\nCalculated Successfully!\n");

                                // Display the updated booking details
                                System.out.print("New Booking Details : ");
                                displayCurrentBooking(bookingDetails, bookingPrice);

                                break;

                            case 2 : // AHMAD ZHARFAN SHAH (Change Car)
                                // Initialize variables for car selection and new car ID
                                int carChangeNum = 1;
                                int newid = 0;

                                displayCurrentBooking(bookingDetails, bookingPrice);
                                System.out.print("\n======= Change Car Rental ======\n\n");
                                int idCount = CarCount(priceData);

                                // Check if only one car is available
                                if (idCount == 1) {
                                    System.out.println("Only one car available, skipping selection.");
                                } else {
                                    // Prompt the user to select the car they want to change
                                    System.out.printf("Choose the car you want to change(1-%d): ", idCount); 
                                    carChangeNum = input.nextInt();

                                    // Validate the user's input; re-prompt if the selection is invalid
                                    while (carChangeNum > idCount) {
                                        System.out.println("Invalid number, please re-enter: ");
                                        carChangeNum = input.nextInt();
                                    }
                                }

                                // Retrieve details of the old car using the selected booking index
                                String oldNumPlate = bookingDetails[carChangeNum - 1][2]; // Get the old car's number plate
                                int oldId = searchArray(oldNumPlate, carPlate);           // Get the ID of the old car
                                int oldHours = (int) (bookingPrice[carChangeNum - 1] / carPrice[oldId]); // Calculate rental hours for the old car
                                
                                // Loop to ensure the user enters a valid new car number plate
                                do {
                                    System.out.print("Enter the new Car Number Plate to swap: ");
                                    String SwapNumPlate = input.next();          // Input the new car's number plate
                                    newid = searchArray(SwapNumPlate, carPlate); // Search for the new car's ID
                                    
                                    // Validate the new number plate; re-prompt if invalid
                                    if(newid<0){
                                        System.out.println("Invalid Number Plate! Please re-enter: ");
                                    }
                                } while (newid < 0);
                                
                                // Update the booking details with the new car's information
                                bookingDetails[carChangeNum - 1][0] = carName[newid];    // Update car name
                                bookingDetails[carChangeNum - 1][1] = carModel[newid];   // Update car model
                                bookingDetails[carChangeNum - 1][2] = carPlate[newid];   // Update car number plate
                                bookingDetails[carChangeNum - 1][3] = carColour[newid];  // Update car color

                                // Recalculate the booking price based on the new car's hourly price and old rental hours
                                bookingPrice[carChangeNum - 1] = carPrice[newid] * oldHours;
                                
                                displayCurrentBooking(bookingDetails, bookingPrice);
                                System.out.println("Car changed...");

                                break;
                            default : 
                                // Return to the main menu if user enter any key
                                System.out.println("Returning to the main menu...");
                        }
                    }

                    break;

                case 4:
// ---------------------------------------------- AIMAN HAFIZ BIN AZHAR 2414751 ------------------------------------------------
                
                // Check if there are no bookings to remove
                if (bookingDetails[0][0] == null) {
                    System.out.println("\nNo bookings found to remove.");
                    break;

                } else {
                    displayCurrentBooking(bookingDetails, bookingPrice);
                    System.out.println("\n==================== REMOVE CAR BOOKING ====================");
                    
                    // Prompt the user to select the booking number to remove
                    System.out.print("Select the booking number to remove (1 to " + addCount + "): ");
                    int removeIDX = input.nextInt();

                    // Validate the user's input for booking selection
                    if (removeIDX < 1 || removeIDX > addCount) {
                        System.out.println("\nInvalid input! Returning to main menu...");
                    } else {
                        removeIDX--; // Adjust the index to match zero-based array indexing
                        
                        // Confirm the removal with the user
                        System.out.print("Are you sure you want to remove this booking? (y/n): ");
                        String confirmRemove = input.next();
                        
                        if (confirmRemove.equalsIgnoreCase("y")) {
                            // Remove the selected booking 
                            for (int j = 0; j < bookingDetails[removeIDX].length; j++) {
                                bookingDetails[removeIDX][j] = null;
                            }
                            bookingPrice[removeIDX] = 0;

                            // Shift all subsequent bookings up by one position
                            for (int i = removeIDX; i < bookingDetails.length - 1; i++) {
                                bookingDetails[i] = bookingDetails[i + 1];
                                bookingPrice[i] = bookingPrice[i + 1];
                            }

                            // Clear the last slot in the array after shifting
                            for (int j = 0; j < bookingDetails[bookingDetails.length - 1].length; j++) {
                                bookingDetails[bookingDetails.length - 1][j] = null;
                            }
                            bookingPrice[bookingDetails.length - 1] = 0;

                            addCount--; // Decrease the total booking count
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
            // Prompt the user to decide whether to continue
            System.out.print("\nDo you want to continue? (y/n) : ");
            String cont = input.next();

            if(cont.equalsIgnoreCase("y")){ 
                choice = 0; // If user want to continue choice = 0;
            }

        } while (choice < 1 || choice > 5); // Loop will continue if choice is outside the range (1 - 5)
    }

    // Display Welcome Message
    public static void displayWelcome(){
        System.out.println("WELCOME TO THE CAR RENTAL MANAGEMENT SYSTEM ");
        System.out.println("===========================================");
        System.out.println("WE ARE HERE TO MAKE RENTING CARS EASIER ");
        System.out.println("===========================================\n");
    }
  

// ---------------------------------------------- AIMAN HAKIMI BIN MOHD ABD RAHMAN 2413257  ---------------------------------------------- 
    
    // Display the "Add Car Rental" screen
    public static void DisplayAddRental(){
        System.out.println("\n\n==================== ADD CAR RENTAL ====================");
        System.out.print("\nPlease enter the plate number of your desired car: ");
    }

    // Searches for a string element in a string array
    public static int searchArray(String elem, String[] array) { // Parameters: elem - the string to search for, array - the array to search within
        int index = -1; // Initialize the index as -1 (not found)

        for (int i = 0; i < array.length; i++) { // Iterate through the array
            if(elem.equalsIgnoreCase(array[i])){
                index = i; 
            }
        }

        return index; // Return the index of the element
    }

    // Searches for an integer element in an integer array
    public static int searchArray(int elem, int[] array) { // Parameters: elem - the integer to search for, array - the array to search within
        int index = -1; // Initialize the index as -1 (not found)

        for (int i = 0; i < array.length; i++) { // Iterate through the array
            if(elem == array[i]) { 
                index = i; 
            }
        }

        return index; // Return the index of the element
    }

    // Displays the booking confirmation details
    public static void BookingConfirmation(String name, String model, String plate, String colour, float price){
        System.out.println("\n\n==================== BOOKING CONFIRMATION ====================");
        System.out.println("\nCAR NAME        MODEL   NO PLATE   COLOUR     TOTAL PRICE");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-15s %-7s %-10s %-10s RM %4.2f \n",name, model, plate, colour, price);
    }

    // Adds a booking to the booking list
    public static void AddBooking(String name, String model, String plate, String colour, float price, String[][] booking, float[] bookingPrice){
        boolean inserted = false; // Flag to check if the booking was inserted

        for(int i = 0; i < booking.length; i++){ // Check if the current slot is empty
            // Add booking details to the array
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
    // Function to display current booking details
    public static void displayCurrentBooking(String[][] booking, float[] bookingPrice){
        // Check if there are no bookings
        if(booking[0][0]==null){
            System.out.println("\nThere is no booking yet...");
        }else{
            // Display header for booking details
            System.out.println("\n==================== BOOKING DETAILS =====================");
            System.out.println("\n   CAR NAME        MODEL   NO PLATE   COLOUR     TOTAL PRICE");
            System.out.println("--------------------------------------------------------------");
            
            // Loop through the booking array to display each booking's details
            for(int i = 0; i<3; i++){
                // Stop looping if a booking slot is empty
                if(booking[i][0]==null){
                    break;
                }
                // Print the booking details 
                System.out.printf("%d) %-15s %-7s %-10s %-10s RM %4.2f \n",
                i + 1,                // Booking number
                booking[i][0],        // Car name
                booking[i][1],        // Model
                booking[i][2],        // No plate
                booking[i][3],        // Colour
                bookingPrice[i]);     // Total price
            }
        }
    }

    // Function to display the rental editing menu
    public static void displayEditRental(){
        // Print the menu options for editing rental details
        System.out.println("\n==================== EDIT CAR RENTAL ====================\n");
        System.out.println("1. Edit Duration of Rental");
        System.out.println("2. Edit Car Name");
        System.out.println("(Press any key to return to mainpage)");
        System.out.print("Choose one of these options : ");
    }

    // Function to calculate the total price of a rental
    public static float calculatePrice(int $carPrice, int $hours){
        System.out.println("\nCalculating the price...");

        // Return the calculated price (car price/hour * hours entered)
        return $carPrice*$hours;
    }
    
    // ----------------------- AHMAD ZHARFAN SHAH BIN ROHISHAM 2414683 ------------------------------------------------------------------------

    // Function to count the number of cars based on their price
    public static int CarCount(int[] PriceArray) {
        
        int count = 0;

        // Loop through the array of car prices
        for(int i=0 ; i < PriceArray.length; i++){
            // Check if the car price is greater than 5
            if (PriceArray[i] > 5)
                count++;
        }

        // Return the total count of valid cars
        return count;
    }

    //-------------------------------- ATILLA AKHUN 2312569 -------------------------------------------------------

    public static void searchCar(String[] carName, String[] carModel, String[] carPlate, String[] carColour) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter the name of the car you want to search for: ");
        String searchQuery = input.nextLine();

        System.out.println("\n==================== SEARCH RESULTS ====================\n");
        boolean carFound = false;

        for (int i = 0; i < carName.length; i++) {
            if (carName[i].equalsIgnoreCase(searchQuery)) {
                carFound = true;
                System.out.printf("Car Name: %-15s Model: %-7s Plate: %-10s Colour: %-10s\n",
                        carName[i], carModel[i], carPlate[i], carColour[i]);
            }
        }

        if (!carFound) {
            System.out.println("No cars found matching the name \"" + searchQuery + "\".");
        }

        System.out.println("========================================================\n");
    }



}