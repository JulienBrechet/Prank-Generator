/**
 * @author Julien Brêchet
 * @author Adrien Marco
 *the prank is decsribed by a sender, some victims and the message
 */

public class Prank {

    private Person sender;
    private Group receivers;
    private String msg;

    // Getters
    public Person getSender() { return sender; }
    public Group getReceivers() { return receivers; }
    public String getMsg() { return msg; }

    // Setters
    public void setSender(Person sender) { this.sender = sender; }
    public void setGroup(Group receivers) { this.receivers = receivers; }
    public void setMsg(String msg) { this.msg = msg; }
}
