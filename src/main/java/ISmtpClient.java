import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Julien Brêchet
 * @author Adrien Marco
 *
 */

public interface ISmtpClient {
    public void sendMessage(Prank prank, BufferedReader reader, PrintWriter writer) throws IOException;
}
