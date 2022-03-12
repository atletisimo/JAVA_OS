//Да се напише Java програма која со користење на I/O стримови ќе ја прочита содржината на датотеката izvor.txt, а потоа нејзината содржина ќе ја испише
//превртена во празната датотека destinacija.txt. Читањето и запишувањето реализирајте го со стримови кои работат бајт по бајт.
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HW01_2 throws IOException {
    FileInputStream fileinput=new FileInputStream("izvori.txt");
    FileOutputStream fileoutput=new FileOutputStream ("destinacija.txt");
    StringBuilder sb=new StringBuilder();
    for(int i=fileinput.available();i>0;i--){
    sb.append((char)fileinput.read());
}
    
fileoutput.write(sb.reverse().toString().getBytes());
fileinput.close();
fileoutput.close();
}
