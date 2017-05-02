import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by simdnt on 02.05.17.
 */
public class Main {
    public static void main(String[] args){
        BufferedReader lIN;
        PrintStream lOUT;
        File lQuellDatei, lErgebnisDatei;
        try{
            System.out.println("In der Datei Quelle.txt müssen sich durch EINZELNE Leerzeichen getrennte Texte befinden.\nViel Glück!");

            lQuellDatei = new File("Quelle.txt");
            lQuellDatei.createNewFile();
            lIN = new BufferedReader(new InputStreamReader(new FileInputStream(lQuellDatei)));
            final ArrayList<String[]> lTabelle = new ArrayList<>();
            lIN.lines().forEach(l->lTabelle.add(l.split(" ")));
            lIN.close();

            StringBuilder lStringBuilder = new StringBuilder();
            holeConcatenationen(0, lTabelle, lStringBuilder,"");
            lErgebnisDatei = new File("Ergebnis.txt");
            lErgebnisDatei.createNewFile();
            lOUT = new PrintStream(new FileOutputStream(lErgebnisDatei));
            lOUT.print(lStringBuilder.toString());
            lOUT.close();
            System.out.println("Fertig!");
        }catch(Exception e){
            System.out.println("Fehler!\nÜberprüfe die Quelldatei!");
        }
    }

    private static void holeConcatenationen(int aIndex, ArrayList<String[]> aTabelle, StringBuilder aStringBuilder, String aBisJetzt) {
        if(aIndex==aTabelle.size()-1){
            Arrays.stream(aTabelle.get(aIndex)).forEach(s->aStringBuilder.append(aBisJetzt+s+"\n"));
        }else if (aIndex<aTabelle.size()-1){
            Arrays.stream(aTabelle.get(aIndex)).forEach(s->holeConcatenationen(aIndex+1,aTabelle,aStringBuilder,aBisJetzt+s));
        }
    }
}
