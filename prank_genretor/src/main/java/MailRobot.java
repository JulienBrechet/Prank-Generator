/**
 * Created by Adrien on 04.04.2017.
 * this main class starts the programm by reading the config files
 * then it get connected to the server and
 * generate the pranks to send them
 * @author Julien Brêchet
 * @author Adrien Marco
 *
 */



import java.io.*;
import java.util.ArrayList;
import java.net.Socket;
import java.util.Properties;

public class MailRobot {


    public int smtpServerPort;
    public String smtpServerAddress;
    public static int nbGroups;
    public static int nbPeoplePerGroup;
    public static String[] messagesParsedTab;//tab of strings so we can use the split function
    public static BufferedReader in;
    public static PrintWriter out;
    public static ArrayList<String> listOfAddresses;
    public String delimMSG;
    public String messages;

    //constructor
    public MailRobot(){
        listOfAddresses = new ArrayList<String>();
    }

    //reads the config and builds the properties of the prank
    public void start() throws IOException{


        Properties properties = new Properties();
        String dir = System.getProperty("user.dir");
        InputStream inputStream = new FileInputStream(dir + "/src/main/java/config.properties");



        //loading properties
        properties.load(inputStream);

        //getting properties values
        smtpServerAddress = properties.getProperty("smtpServerAddress");
        //System.out.println(smtpServerAddress);
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        //System.out.println(smtpServerPort);
        nbGroups = Integer.parseInt(properties.getProperty("nbGroups"));
        //System.out.println(nbGroups);
        nbPeoplePerGroup = Integer.parseInt(properties.getProperty("sizeGroup"));
        //System.out.println(nbPeoplePerGroup);
        delimMSG = properties.getProperty("delimMSG");
        //System.out.println(delimMSG);

        //getting the addresses and the messages
        Parser parser = new Parser();
        messages = Parser.readFile(dir + "/src/main/java/messages.utf8");
        messagesParsedTab = Parser.parseMessages(messages, delimMSG);
        parser.parseAddresses(dir + "/src/main/java/victims.utf8");
        listOfAddresses = parser.getList();

    }


    //conection to the server
    public void conection() throws IOException{

        Socket socketSmtp = new Socket(smtpServerAddress, smtpServerPort);
        out = new PrintWriter(new OutputStreamWriter(socketSmtp.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socketSmtp.getInputStream()));
    }

    //diconection to the server
    public  void disconection() throws IOException{

        out.print("quit\r\n");
        out.flush();
    }

    //main function for the mail robot
    public static void main(String[] args) throws IOException{

        int nbPranks;
        MailRobot mailRobot = new MailRobot();
        ArrayList<Prank> pranksList;

        //starting the programm by reading the config
        mailRobot.start();

        //buildind the pranks
        PrankGenerator prankGenerator = new PrankGenerator();
        pranksList = prankGenerator.buildPrank();
        nbPranks = pranksList.size();

        //sendind each prank to a group
        //the mail robot becomes a client smtp to do the work
        SmtpClient smtpClient = new SmtpClient();
        for(int i = 0; i < nbPranks; ++i){

            mailRobot.conection();
            smtpClient.sendMessage(pranksList.get(i), MailRobot.in, MailRobot.out);
        }

        mailRobot.disconection();
    }

}
