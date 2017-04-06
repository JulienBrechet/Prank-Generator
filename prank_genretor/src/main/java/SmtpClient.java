import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Julien Brêchet
 * @author Adrien Marco
 *this class represents a client smtp that send pranks
 */

public class SmtpClient implements ISmtpClient {

    public void sendMessage(Prank prank, BufferedReader br, PrintWriter pw) throws IOException {

        String dataHeader = "";//will contain subject+from+to from DATA
        String subject;
        int nbrPersons;
        String fromTo;

        //------------------------buiding the email fields---------------------------------------
        Person sender= prank.getSender();
        Group receivers = prank.getReceivers();
        String msg = prank.getMsg();
        subject = Parser.parseMessages(msg, "\n")[1];
        msg = msg.substring(subject.length() + 1);
        fromTo = "From: " + sender.getMail() + "\r\n" + "To: ";

        //nbr of victims
        nbrPersons = receivers.getReceivers().size();

        //still building the from to fields
        for(int i = 0; i < nbrPersons; ++i) {
            if(!sender.getMail().equals(receivers.getReceivers().get(i).getMail())) {
                fromTo = fromTo.concat(receivers.getReceivers().get(i).getMail());

                if (i != nbrPersons - 1)
                    fromTo = fromTo.concat(", ");
            }
        }
        dataHeader = dataHeader.concat(subject + "\r\n");
        dataHeader = dataHeader.concat(fromTo);


        //---------------------------------Communication--------------------------------------------
        //say helo to the smtp server because the mail robot is supposed to connect before
        pw.print("EHLO heig-vd.ch\r\n");
        pw.flush();

        pw.print("MAIL FROM: " + sender.getMail() + "\r\n");
        pw.flush();

        for(int i = 0; i < nbrPersons; ++i) {
            pw.print("RCPT TO: " + receivers.getReceivers().get(i).getMail()+"\r\n");
            pw.flush();
        }

        pw.print("DATA\r\n");
        pw.flush();

        pw.print(dataHeader + "\r\n");
        pw.flush();

        pw.print(("\r\n"));
        pw.flush();

        pw.print(msg + "\r\n");
        pw.flush();

        pw.print(".\r\n");
        pw.flush();

        //the disconection will be done by the mail robot

    }
}