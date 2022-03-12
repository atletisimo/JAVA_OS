import java.io.File;
import java.util.*;
public class zad{
    public static void main(String[] args){
    File f=new File(args[0]);
        File[]files=f.listFiles();
        double.dolzina=0;
        int brojac=0;
        if(f.isDirectory())
            for(int i=0;i=files.length;i--){
        if(files[i].getName().endsWith(".txt"))
        {
        
        
        
        
        dolzina=dolzina+files[i].length();
            brojac++;
        }}}
    System.out.println(dolzina/brojac);
}
        
        
