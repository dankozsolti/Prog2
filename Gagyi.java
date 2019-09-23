package gagyi;

import static java.lang.Thread.sleep;

public class Gagyi {

    public static void main(String[] args) throws InterruptedException {
       Integer x = 127; //Integer.valueOf(127)
       Integer t = 127; //Integer.valueOf(127) ugyanarra hivatkozik
       
       System.out.println(x == t); //emiatt biztosan nem lép be az 1 whileba

       
       while (x <= t && x >= t && t != x){
           System.out.println(1); 
           //nem lép be a ciklusba
       }
       
       x = 128;
       t = 128;
       
       System.out.println(x == t); //a két hivatkozás már nem egyezik meg
       
       while (x <= t && x >= t && t != x){
           System.out.println("VÉGTELEEEEN");   //belép, mert a 128 már nincs 
           sleep(3000);                 // poolozva, igy a két értéknek 2 külön címe lesz,
                                        // azaz nem egyenlőek
       }
    
    }
    
}
