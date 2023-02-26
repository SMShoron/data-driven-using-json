import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadFileFromJson {

    public static void main(String[] args) throws IOException, ParseException {

        //creating a json parser object

        JSONParser jsonParser = new JSONParser();

        // parsing the json content from json file

        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/test/resources/testdata/DataTest.json"));

        //read data from json file

        String Eid = (String) jsonObject.get("EmployeeID");
        String EFname = (String) jsonObject.get("FirstName");
        String ELname = (String) jsonObject.get("LastName");
        String Countryname = (String) jsonObject.get("Country");

        System.out.println(Eid);
        System.out.println(EFname);
        System.out.println(ELname);
        System.out.println(Countryname);


    }
}
