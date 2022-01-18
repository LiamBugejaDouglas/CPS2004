//Admin subclass of Person
public class Admin extends Person{

    //Constructing Admin with specified variables
    public Admin(String ID, String name, String surname){

        //super keyword used to call parent class method
        super (ID, name, surname);
    }

    //Default Constructor
    public Admin(){

    }

    //Setting the the toString()
    public String toString(){

        //super used to call parent class method.
        return super.toString();
    }
}