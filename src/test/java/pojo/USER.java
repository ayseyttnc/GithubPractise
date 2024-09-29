package pojo;

import utilities.MyFaker;
import utilities.ROLE;

public class USER {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public USER(ROLE role) {
        MyFaker myFaker= new MyFaker();
        this.username = myFaker.getUsername();
        this.email = myFaker.getEmail();
        this.password=myFaker.getPassword();
        this.firstName=myFaker.getFirstName();
        this.lastName=myFaker.getLastname();
        this.role = role.name().toLowerCase();
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String role() {
        return role;
    }
}
