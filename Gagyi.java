package gagyi;

import java.lang.Thread;

public class Gagyi {

    public static void main(String[] args) throws InterruptedException {
       Integer x = 127;
       Integer t = 127;
       
       System.out.println(x == t);

       
       while (x <= t && x >= t && t != x){
           System.out.println(1); 
       }
       
       x = 128;
       t = 128;
       
       System.out.println(x == t);
       
       while (x <= t && x >= t && t != x){
           System.out.println("Végtelen ciklus");
           Thread.sleep(3000);
       }
    
    }
    
}
