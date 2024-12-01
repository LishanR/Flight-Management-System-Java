import java.util.Scanner;

public class Person {
    private String fName;  //Private because to encapsulate and protect the variables
    private String surname;
    private String email;

    public Person(String fName,String surname,String email){//Adding a Constructor to create the Person object
        this.fName = fName;
        this.surname = surname;
        this.email = email;
    }
//Getters and Setters for all variables
    public String getFName() {
        return fName;
    }

    public void setFName() {
        Scanner scanName = new Scanner(System.in);
        System.out.print("Enter your First Name: ");
        String firstName = scanName.nextLine();
        this.fName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname() {
        Scanner scanName = new Scanner(System.in);
        System.out.print("Enter your Surname: ");
        String lastName = scanName.nextLine();
        this.surname = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        boolean correctEmail = false;
        do { //Validating email
            Scanner scanName = new Scanner(System.in);
            System.out.print("Enter your Email Address: ");
            email=scanName.nextLine();

            if (email.contains("@") && email.contains(".") && email.length()>5){
                correctEmail=true;
            }else {
                System.out.println("Invalid Email Format ");
            }
        }while (!correctEmail);

    }
}
