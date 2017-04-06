import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * Created by Adrien on 05.04.2017.
 * this class will generate a prank for a group of victims
 * @author Julien Brêchet
 * @author Adrien Marco
 *
 */


public class PrankGenerator {


    int nbAddresses;
    int nbMessages;
    int nbGroups;
    int nbPeoplePerGroup;
    ArrayList<String> listOfAllAddresses;
    ArrayList<String> listOfAllMessages;
    ArrayList<Group> listOfGroups;
    ArrayList<String> listOfChosenMessages;
    ArrayList<String> listOfChosenReceivers;
    ArrayList<Prank> listOfPranks;
    ArrayList<Person> listOfPersons;
    int index;


    //constructor
    public PrankGenerator(){

        listOfAllAddresses = new ArrayList<String>();
        listOfAllAddresses = MailRobot.listOfAddresses;
        //(conversion array arrayList)
        listOfAllMessages = new ArrayList<String>(Arrays.asList(MailRobot.messagesParsedTab));
        listOfGroups = new ArrayList<Group>();
        listOfChosenMessages = new ArrayList<String>();
        listOfChosenReceivers = new ArrayList<String>();
        listOfPranks = new ArrayList<Prank>();
        listOfPersons = new ArrayList<Person>();

        nbGroups = MailRobot.nbGroups;
        nbAddresses = listOfAllAddresses.size();
        nbMessages = listOfAllMessages.size();
        nbPeoplePerGroup = MailRobot.nbPeoplePerGroup;
    }


    //to build a prank by choosing random people and messages
    public ArrayList<Prank> buildPrank(){
        int nbrPersons;
        String msg;
        Random rand = new Random();

        for(int i = 0; i < nbGroups; ++i) {

            nbrPersons = nbPeoplePerGroup;
            listOfGroups.add(i, new Group());

            while(nbrPersons > 0) {

                index = rand.nextInt(nbAddresses);

                //if the we dont have yet the mail address
                if(!listOfChosenReceivers.contains(listOfAllAddresses.get(index))) {

                    listOfChosenReceivers.add(listOfAllAddresses.get(index));
                    listOfGroups.get(i).addReceiver(new Person(listOfAllAddresses.get(index)));
                    --nbrPersons;
                }
            }

            msg = buildMessage();

            listOfChosenMessages.add(i, msg);

        }

        for(int i = 0; i < nbGroups; ++i) {

            listOfPranks.add(new Prank());
            listOfPranks.get(i).setGroup(listOfGroups.get(i));
            listOfPranks.get(i).setMsg(listOfChosenMessages.get(i));
            index = rand.nextInt(nbPeoplePerGroup);
            listOfPranks.get(i).setSender(new Person(listOfPranks.get(i).getReceivers().getReceivers().get(index).getMail()));
        }

        return listOfPranks;
    }


    //to build a message
    public String buildMessage(){

        //we choose a message we still dont have
        //and we search untill we dont find it
        while(true){
            Random rand = new Random();
            index = rand.nextInt((nbMessages-1))+1;
            String message = listOfAllMessages.get(index);
            //we add the msg if we did not have it
            if(!listOfChosenMessages.contains(message)){
                listOfChosenMessages.add(message);
                break;
            }
        }

        return listOfAllMessages.get(index);
    }
}
