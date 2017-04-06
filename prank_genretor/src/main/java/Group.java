import java.util.ArrayList;
import java.util.List;

/**
 * @author Julien Brêchet
 * @author Adrien Marco
 *
 */

public class Group {
    private List<Person> groupOfPersons;

    // Constructor
    public Group() {

        groupOfPersons = new ArrayList<Person>();
    }

    // Adding a receiver person to a group
    public void addReceiver(Person p) {

        groupOfPersons.add(p);
    }

    // Getter
    public List<Person> getReceivers() {
        return groupOfPersons;
    }
}