package minergame.IO;

import minergame.gui.MainDriver;

import java.io.*;
import java.util.Scanner;

public class IOUtils {
    private static Scanner scanner= new Scanner(System.in);

    public void writeGameToFile(minergame.IO.GameSave gameSave){
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("savefile.dll");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(gameSave);
            fout.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public minergame.IO.GameSave readGameSaveFromFle(){
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(new File("savefile.dll"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read object
            minergame.IO.GameSave gameSave = (minergame.IO.GameSave) oi.readObject();

            oi.close();
            fi.close();
            return gameSave;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
