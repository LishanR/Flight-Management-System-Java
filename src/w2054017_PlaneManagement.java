import java.util.*;

public class w2054017_PlaneManagement {//Main Class

    static int[] A = new int[14];//Initializing Arrays to replicate the number of seats
    static int[] B = new int[12];
    static int[] C = new int[12];
    static int[] D = new int[14];

    static Ticket[][] tickets = new Ticket[4][];// 2D array to store the relevant tickets

    public static void ticketArray() {
        tickets[0] = new Ticket[14];
        tickets[1] = new Ticket[12];
        tickets[2] = new Ticket[12];
        tickets[3] = new Ticket[14];
    }

    static String[] validRows = {"A", "B", "C", "D"}; //Storing the names of rows for validation purposes

    static double totalPrice = 0;

    public static void main(String[] args) {//Main Method

        System.out.println("""
                -----------------------------------------------------
                      Welcome to the Plane Management Application
                -----------------------------------------------------
                """);

        ticketArray();
        getOption();
    }


    public static void getOption() { // A method to prompt user for the desired option
        int option = -1;
        do {
            System.out.println("""

                    *****************************************************
                    *                    Menu Options                   *
                    *****************************************************
                        1) Buy a seat
                        2) Cancel a seat
                        3) Find first available seat
                        4) Show seating plan
                        5) Print tickets information and total sales
                        6) Search ticket
                        0) Quit
                    ****************************************************
                    """);//Menu options
            Scanner optionScan = new Scanner(System.in);
            boolean isValidInput = true;
            while (isValidInput) {
                try {//Validating options
                    System.out.print("Please Select an option: ");
                    option = optionScan.nextInt();

                    if (option >= 0 && option <= 6) {
                        isValidInput = false;
                    } else {
                        System.out.println("Invalid option! Please try again with the correct options.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter the options that are listed");
                    optionScan.nextLine();
                }
            }
            switch (option) {
                case 1:
                    System.out.println("Buying a seat..."); //User selecting Buy seat option
                    buySeat();
                    break;
                case 2:
                    System.out.println("Cancelling a seat..."); //User selecting Cancel seat option
                    cancelSeat();
                    break;
                case 3:
                    System.out.println("Finding first available seat..."); //User selecting Find first available seat option
                    findFirstAvailableSeat();
                    break;
                case 4:
                    System.out.println("Displaying seat plan..."); //User selecting Display Seat Plan option
                    showSeatingPlan();
                    break;
                case 5:
                    System.out.println("Ticket Info and Total...\n"); //User selecting Total and Ticket Info option
                    displayTicketsInfo();
                    break;
                case 6:
                    System.out.println("Searching a ticket..."); //User selecting Ticket Search option
                    searchTicket();
                    break;
                case 0:
                    System.out.println("Thank you for using our services!"); //User selecting to quit
                    break;
                default:
                    System.out.println("Invalid Option! Use Correct options"); //Warn user that the entered option is invalid
                    break;
            }
        }while(option != 0);
    }

    public static void buySeat() { //Method to carry out buy seat option

        System.out.println("\nAvailable Seat Plan... \n");
        showSeatingPlan();
        System.out.println(" ");

        String rowLetter = validateRow();
        int seatNumber = validateSeat(rowLetter);

        switch (rowLetter) { // Checking whether the selected seat is booked or not. If not booked, booking the seat for the user
            case "A":
                if (A[seatNumber - 1] == 0) {
                    A[seatNumber - 1] = 1;
                } else {
                    System.out.println("Selected seat is already booked");
                    return;
                }
                break;
            case "B":
                if (B[seatNumber - 1] == 0) {
                    B[seatNumber - 1] = 1;
                } else {
                    System.out.println("Selected seat is already booked");
                    return;
                }
                break;
            case "C":
                if (C[seatNumber - 1] == 0) {
                    C[seatNumber - 1] = 1;
                } else {
                    System.out.println("Selected seat is already booked");
                    return;
                }
                break;
            case "D":
                if (D[seatNumber - 1] == 0) {
                    D[seatNumber - 1] = 1;
                } else {
                    System.out.println("Selected seat is already booked");
                    return;
                }
                break;

        }
        Person person = new Person("firstName", "surname", "email");//Person object
        //Setting values to relevant variables in the Person Class
        person.setFName();
        person.setSurname();
        person.setEmail();

        System.out.println("\nSeat " + rowLetter + seatNumber + " has been booked under " + person.getFName() + " " + person.getSurname() + " with the Email Address " + person.getEmail());


        Ticket ticket = new Ticket(rowLetter,seatNumber,pricePerSeat(seatNumber),person);//Ticket Object
        //Setting values to relevant variables in the Ticket Class
        ticket.setRowLetter(rowLetter);
        ticket.setSeatNumber(seatNumber);
        ticket.setPrice(pricePerSeat(seatNumber));
        ticket.setPerson(person);
        ticket.save();

        totalPrice += pricePerSeat(seatNumber);//Adding the price of the booked seat

        switch (rowLetter){//Assigning tickets of seats from rows to the ticket object
            case "A":
                tickets[0][seatNumber - 1] = ticket;
                break;
            case "B":
                tickets[1][seatNumber - 1] = ticket;
                break;
            case "C":
                tickets[2][seatNumber - 1] = ticket;
                break;
            case "D":
                tickets[3][seatNumber - 1] = ticket;
                break;
        }
    }

    public static void cancelSeat() {//Method to cancel a seat
        //Prompting for the row and seat number to cancel using pre-defined methods
        String rowLetter = validateRow();
        int seatNumber = validateSeat(rowLetter);

        switch (rowLetter) {//Cancelling the seat
            case "A":
                if (A[seatNumber - 1] == 1) {
                    A[seatNumber - 1] = 0;
                    totalPrice -= pricePerSeat(seatNumber);
                    System.out.println("Seat " + rowLetter + seatNumber + " has been cancelled");
                } else {
                    System.out.println("Selected seat is already vacant");
                }
                break;
            case "B":
                if (B[seatNumber - 1] == 1) {
                    B[seatNumber - 1] = 0;
                    totalPrice -= pricePerSeat(seatNumber);
                    System.out.println("Seat " + rowLetter + seatNumber + " has been cancelled");
                } else {
                    System.out.println("Selected seat is already vacant");
                }
                break;
            case "C":
                if (C[seatNumber - 1] == 1) {
                    C[seatNumber - 1] = 0;
                    totalPrice -= pricePerSeat(seatNumber);
                    System.out.println("Seat " + rowLetter + seatNumber + " has been cancelled");
                } else {
                    System.out.println("Selected seat is already vacant");
                }
                break;
            case "D":
                if (D[seatNumber - 1] == 1) {
                    D[seatNumber - 1] = 0;
                    totalPrice -= pricePerSeat(seatNumber);
                    System.out.println("Seat " + rowLetter + seatNumber + " has been cancelled");
                } else {
                    System.out.println("Selected seat is already vacant");
                }
                break;
        }

        switch (rowLetter){//Removing tickets(Vacating the relevant ticket) from the ticket object
            case "A":
                tickets[0][seatNumber - 1] = null;
                break;
            case "B":
                tickets[1][seatNumber - 1] = null;
                break;
            case "C":
                tickets[2][seatNumber - 1] = null;
                break;
            case "D":
                tickets[3][seatNumber - 1] = null;
                break;
        }
    }

    public static void findFirstAvailableSeat() {//Method to find the first available seat
        char[] arrayRows = {'A', 'B', 'C', 'D'};
        //Linear search to find the first available seat
        int searchValue = 0;
        for (char row : arrayRows) {//Iterating through tha arrayRows to determine the relevant Row Letter
            int[] currentRow = switch (row) {//Stating that the values in 'arrayRows' are the relevant letters of the relevant rows
                case 'A' -> A;
                case 'B' -> B;
                case 'C' -> C;
                case 'D' -> D;
                default -> null;
            };
            int seat = 0;
            while (seat < currentRow.length) {
                if (currentRow[seat] == searchValue) {
                    System.out.println("The first available seat is seat number " + (seat + 1) + " in row " + row);
                    return;
                }
                seat++;
            }
        }
        System.out.println("No available seats. All seats are booked");
    }

    public static void showSeatingPlan() {//Method to display the seating plan
        //Printing the seats for each row
        System.out.print("A: ");
        for (int i : A) {
            if (i == 0) {
                System.out.print("O" + " ");
            } else {
                System.out.print("X" + " ");
            }
        }
        System.out.println(" ");

        System.out.print("B: ");
        for (int i : B) {
            if (i == 0) {
                System.out.print("O" + " ");
            } else {
                System.out.print("X" + " ");
            }
        }
        System.out.println();

        System.out.print("\nC: ");
        for (int i : C) {
            if (i == 0) {
                System.out.print("O" + " ");
            } else {
                System.out.print("X" + " ");
            }
        }
        System.out.println(" ");

        System.out.print("D: ");
        for (int i : D) {
            if (i == 0) {
                System.out.print("O" + " ");
            } else {
                System.out.print("X" + " ");
            }
        }
        System.out.println(" ");
    }

    public static String validateRow() {//Method to validate the user input for the row
        Scanner scanSeatRow = new Scanner(System.in);

        boolean isRow = false;
        String rowLetter = null;

        while (!isRow) {
            System.out.println("Available rows: A/B/C/D");
            System.out.print("Enter the letter of selected row: ");
            rowLetter = scanSeatRow.nextLine().toUpperCase();

            if (rowLetter.length() != 1) {
                System.out.println("Invalid Row!");
            } else {
                boolean found = false;
                for (String i : validRows) {
                    if (rowLetter.equals(i)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    isRow = true;
                } else {
                    System.out.println("Invalid Row!");
                }
            }
        }
        return rowLetter;
    }


    public static int validateSeat(String rowLetter) {//Method to validate the user input for the seat
        Scanner scanSeatNum = new Scanner(System.in);
        boolean isNum = false; // "isNum" means that checking whether the data type that user entered as seat number is an integer

        int seatNumber = 0; //Mimicking that this is the variable prompting for the seat number
        while (!isNum) { // !isNum is making the boolean true to continue with the program(Not isNum)
            System.out.print("Enter the number of selected seat: ");
            seatNumber = scanSeatNum.nextInt();

            if (rowLetter.equals("A") || rowLetter.equals("D")) {
                if (seatNumber >= 1 && seatNumber <= A.length) {
                    isNum = true;
                } else {
                    System.out.println("Invalid Seat Number!");
                }
            } else {
                if (seatNumber >= 1 && seatNumber <= B.length) {
                    isNum = true;
                } else {
                    System.out.println("Invalid Seat Number!");
                }
            }

        }
        return seatNumber;
    }

    public static double pricePerSeat(int seatNum) {//Method to determine the price for each range of seats
        if (seatNum >= 1 && seatNum <= 5) {
            return 200.00;
        } else if (seatNum >= 6 && seatNum <= 9) {
            return 150.00;
        } else if (seatNum >= 10 && seatNum <= 14) {
            return 180.00;
        }
        return 0;
    }

    public static void displayTicketsInfo(){//Method to display the Ticket info and Sales
        for (int i = 0; i < tickets.length; i++) {
            String rowLetter = validRows[i];
            Ticket[] rowTickets = tickets[i];
            for (int j = 0; j < rowTickets.length; j++) {
                Ticket ticket = rowTickets[j];
                if (ticket != null) {
                    System.out.println("Row "+ rowLetter +" Seat " + (j + 1) + "\nPrice for the seat: £" + ticket.getPrice() + "\nBooked by: " + ticket.getPerson().getFName() + " " + ticket.getPerson().getSurname() + "\nEmail Address: " + ticket.getPerson().getEmail() + "\n");
                }
            }
        }
        System.out.println("Total is: £" + totalPrice);
    }

    public static void searchTicket(){//Method to search the booked ticket
        showSeatingPlan();
        String rowLetter = validateRow();
        int seatNumber = validateSeat(rowLetter);

        int[] selectedRow;
        int num;
        switch (rowLetter) {
            case "A":
                selectedRow = A;
                num = 0;
                break;
            case "B":
                selectedRow = B;
                num = 1;
                break;
            case "C":
                selectedRow = C;
                num = 2;
                break;
            case "D":
                selectedRow = D;
                num = 3;
                break;
            default:
                System.out.println("Invalid row");
                return;
        }
        if (selectedRow != null && seatNumber >= 1 && seatNumber <= selectedRow.length) {//Validating entered data on row and seat
            if (selectedRow[seatNumber - 1] == 1) {//Checking whether the seat is booked
                System.out.println(rowLetter + seatNumber + " is booked by: \n");
                Ticket bookedTicket = tickets[num][seatNumber - 1];
                System.out.println(bookedTicket.printingTicketInfo());//Displaying ticket Information
            } else {
                System.out.println("Seat " + rowLetter + seatNumber + " is available at the moment.");
            }
        } else {
            System.out.println("Invalid row or seat number.");
        }
    }
}

/*
References:
Lecture Notes/Tutorials from UOW/IIT (From week 1 to week 10)
String capitalization - Stack overflow
https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
*/