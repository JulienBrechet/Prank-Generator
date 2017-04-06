/**
 * Created by Adrien on 04.04.2017.
 * we use this class to read the config files and put it into a data structure
 * @author Julien Brêchet
 * @author Adrien Marco
 *
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

public class Parser {

    //we will put the list of addresses of the victims inside
    private final ArrayList<String> listLines = new ArrayList<String>();

    //to parseAddresses
    public void parseAddresses(String file_name) throws IOException{

        FileReader fr = new FileReader(file_name);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //clean if there was full
        if(!listLines.isEmpty()){
            listLines.clear();
        }

        //fill
        while((line = br.readLine())!= null){
            listLines.add(line);
        }

    }

    //getter
    public ArrayList<String> getList(){
        return listLines;
    }

    //reads a file and store the content into a string
    public static  String readFile(String file_name)throws IOException {

        FileReader fr = new FileReader(file_name);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        StringBuilder sb = new StringBuilder();

        //reads until it is finished
        while (line != null) {

            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }

        return sb.toString();

    }


    //splits a string into arrays of strings depending the delimiter
    public static String[] parseMessages(String str, String delimiter){

        return str.split(delimiter);
    }




}
