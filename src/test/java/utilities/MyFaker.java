package utilities;

import com.github.javafaker.Faker;
import lombok.Getter;

@Getter
public class MyFaker {
    String username;
    String email;
    String password;
    String firstName;
    String lastname;

    public MyFaker (){
        Faker faker= new Faker();
         username= faker.name().username();
         email=faker.internet().emailAddress();
         password=faker.internet().password(8,10,true,true,true);
         firstName=faker.name().firstName();
         lastname=faker.name().lastName();
    }
}
