import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private String rowLetter;//Initializing variables

    private int seatNumber;
    private double price;
    private Person person;

    public Ticket(String rowLetter, int seatNumber, double price, Person person){//Adding a constructor to create the Ticket object
        this.rowLetter = rowLetter;
        this.seatNumber = seatNumber;
        this.price = price;
        this.person = person;
    }
//Getters and Setters for each variable
    public String getRowLetter(){
        return rowLetter;
    }

    public void setRowLetter(String rowLetter){
        this.rowLetter = rowLetter;
    }
    public  int getSeatNumber(){
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;
    }
    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public String printingTicketInfo(){//Method to print ticket info
        System.out.println("Ticket Information: \n ");
        System.out.println("Selected Row: " + getRowLetter());
        System.out.println("Selected Seat: " + getSeatNumber());
        System.out.println("Price of the selected seat: " + getPrice());
        System.out.println(" ");
        System.out.println("Ticket Owner Information: \n ");
        System.out.println("First Name: " + getPerson().getFName());
        System.out.println("Surname: " + getPerson().getSurname());
        System.out.println("Email Address: " + getPerson().getEmail());
        return " ";
    }

    public void save() {//Method to write details into a file
        try {
            FileWriter fileWriter = new FileWriter("C:/Users/Asus/Documents/IIT/UOW - SE/Year 1/Sem 2/SD 2 PRO/CW/CW/Seats/" + rowLetter + seatNumber + ".txt");
            fileWriter.write("Ticket Information:\n");
            fileWriter.write("Row: " + rowLetter + ", Seat: " + seatNumber + "\n");
            fileWriter.write("Price: £" + getPrice() + "\n");
            fileWriter.write("Name: " + person.getFName() + " " + person.getSurname() + "\n");
            fileWriter.write("Email: " + person.getEmail() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while saving details");
        }
    }
}