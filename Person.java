// Person Superclass
public class Person {

    // Instance variables
    protected String id;
    protected String name;
    protected int age;
    protected String phoneNumber;

    // Constructor
    public Person(String id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public int getAge() { 
        return age; 
    }
    public String getPhoneNumber() { 
        return phoneNumber;
    }

    // Setters
    public void setName(String name) { 
        this.name = name;
    }
    public void setAge(int age) { 
        this.age = age; 
    }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
}
