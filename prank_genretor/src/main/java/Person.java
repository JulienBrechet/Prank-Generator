/**
 * @author Julien Brêchet
 * @author Adrien Marco
 *Here we just need to describe a person by his mail address (minimal implementation)
 */

public class Person {

    private String mail;

    //constructor of a person by his email address
    public Person(String mail){
        this.mail = mail;
    }

    // Getter
    public String getMail() { return mail; }

}