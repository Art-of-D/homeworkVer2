package mod10.DirJson;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GsonMain {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/art_of_d/Java/homeworkVer2/src/main/java/mod10/DirJson/file.txt");
        if (!(file.exists())){
            file.createNewFile();
        }
        ArrayList<Character> chars = new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            int i = -1;
            while ((i = reader.read()) != -1) {
                chars.add((char) i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        //Splitting the chars into the words or the integer
        ArrayList<String> strArr = new ArrayList<>();
        String string = "";
        String integer = "";
        for (Character aChar : chars) {
            if (Objects.equals(aChar, null) || Objects.equals(aChar, ' ') || Objects.equals(aChar, '\n')) {
                if (string.length() >= 1) {
                    if (string.equals("name") || string.equals("age")) {
                        string = "";
                    } else {
                        strArr.add(string);
                    }
                    string = "";
                } else if (integer.length() >= 1) {
                    strArr.add(integer);
                    integer = "";
                }
            } else if (Character.isAlphabetic(aChar)) {
                string += aChar;
            } else if (Character.isDigit(aChar)) {
                integer += aChar;
            }
        }
        // if text do not end with space or /n, this part will give last string
        if (integer != "") {
            strArr.add(integer);
            integer = "";
        }


        //Create the list of User
        ArrayList<User> user = new ArrayList<>();
        for (int i = 0; i < strArr.size() - 1; i++) {
            if (isInteger(strArr.get(i + 1))) {
                User newU = new User(strArr.get(i), Integer.parseInt(strArr.get(i + 1)));
                user.add(newU);
            }
        }

        //write json info into file
        Gson gson = new Gson();
        String gsonArr = gson.toJson(user);

        File fileJS = new File("/Users/art_of_d/Java/homeworkVer2/src/main/java/mod10/DirJson/user.json");
        if (!fileJS.exists()){
            fileJS.createNewFile();
        }
        try(FileWriter fileWriter = new FileWriter("/Users/art_of_d/Java/homeworkVer2/src/main/java/mod10/DirJson/user.json")){
            fileWriter.write(gsonArr);
            fileWriter.close();
        }

    }
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}