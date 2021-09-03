package sample;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static String reading() {


 try{
        FileChooser ny = new FileChooser();
        File selecteedFile = ny.showOpenDialog(null);
        File myObj = new File(selecteedFile.getAbsolutePath());
        Scanner myReader = new Scanner(myObj);

        StringBuilder sp = new StringBuilder();

        while (myReader.hasNextLine()){
            String data = myReader.nextLine();
            sp.append(data+"\n");

        }

        
        myReader.close();
     return sp.toString();


    }
        catch (
    FileNotFoundException e){
            e.getStackTrace();
        return "An error has ocoured";

    }
}



}

