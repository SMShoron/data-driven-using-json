import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginInPortal {

    ChromeDriver driver = new ChromeDriver();

    @BeforeTest
    void browserSetup(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
    }

    @AfterTest
    void tearDown(){
        driver.close();
    }

    @Test(dataProvider = "dp")
    void login(String data){
        String users[] = data.split(",");
        driver.get("https://automationtesting.co.uk/loginPortal.html");
        driver.findElement(By.xpath("//input[@id='login_text']")).sendKeys(users[0]);
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys(users[1]);


    }

    @DataProvider(name = "dp")
    public Object[] readJson() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("src/test/resources/TestData/LoginCreds.json");

        Object obj =  jsonParser.parse(reader);

        JSONObject userLoginObj = (JSONObject) obj;

        JSONArray userArr = (JSONArray) userLoginObj.get("loginCreds");

        System.out.println(userArr);

        String arr[] = new String[userArr.size()];

        for (int i=0; i<userArr.size();i++){

            JSONObject users = (JSONObject) userArr.get(i);
            String nameUser = (String) users.get("username");
            String userPassword = (String) users.get("password");

            arr[i] = nameUser+","+userPassword;

        }

        return arr;


    }


}
