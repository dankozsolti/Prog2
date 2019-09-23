package bpp;

/*
 * PiBBP.java
 *
 * DIGIT 2005, Javat tanítok
 * Bátfai Norbert, nbatfai@inf.unideb.hu
 *
 */

public class Main {
    String d16PiHexaJegyek;
 
   
    public Main(int d) {
 
        double d16Pi = 0.0d;
 
        double d16S1t = d16Sj(d, 1);
        double d16S4t = d16Sj(d, 4);
        double d16S5t = d16Sj(d, 5);
        double d16S6t = d16Sj(d, 6);
 
        d16Pi = 4.0d * d16S1t - 2.0d * d16S4t - d16S5t - d16S6t;
 
        d16Pi = d16Pi - StrictMath.floor(d16Pi);
 
        StringBuffer sb = new StringBuffer();
 
        Character hexaJegyek[] = { 'A', 'B', 'C', 'D', 'E', 'F' };
 
        while (d16Pi != 0.0d) {
 
            int jegy = (int) StrictMath.floor(16.0d * d16Pi);
 
            if (jegy < 10)
                sb.append(jegy);
            else
                sb.append(hexaJegyek[jegy - 10]);
 
            d16Pi = (16.0d * d16Pi) - StrictMath.floor(16.0d * d16Pi);
        }
 
        d16PiHexaJegyek = sb.toString();
    }
 
    public double d16Sj(int d, int j) {
 
        double d16Sj = 0.0d;
 
        for (int k = 0; k <= d; ++k)
            d16Sj += (double) n16modk(d - k, 8 * k + j) / (double) (8 * k + j);
 

        return d16Sj - StrictMath.floor(d16Sj);
    }
 

    public long n16modk(int n, int k) {
 
        int t = 1;
        while (t <= n)
            t *= 2;
 
        long r = 1;
 
        while (true) {
 
            if (n >= t) {
                r = (16 * r) % k;
                n = n - t;
            }
 
            t = t / 2;
 
            if (t < 1)
                break;
 
            r = (r * r) % k;
 
        }
 
        return r;
    }
 
    public String toString() {
        return d16PiHexaJegyek;
    }
 
    public static void main(String args[]) {
        System.out.print(new Main(1000000));
    }
}
