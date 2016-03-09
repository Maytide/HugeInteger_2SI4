/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab_3_4;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author George
 */
public class Lab_3_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        HugeInteger htest1 = new HugeInteger(150);
//        HugeInteger htest2 = new HugeInteger(150);
//        BigInteger btest1 = htest1.toBigInteger();
//        BigInteger btest2 = htest2.toBigInteger();
//        
//        HugeInteger h = HugeInteger.multiply(htest1, htest2);
//        BigInteger b = btest1.multiply(btest2);
//        
//        System.out.println(h);
//        System.out.println(b);
//        HugeInteger huge1 = new HugeInteger("400001");
//        HugeInteger huge2 = new HugeInteger("100004");
//        
//        System.out.println(huge1.compareTo(huge2));
        //System.out.println(new HugeInteger(-230000, true));
        //compare();
        //addition();
        testMultiply();
        //test();
        //test2();
        //addition();
        //subtraction();
        //multiplication();
        //naiveMultiplication();
        
    }
    public static void testMultiply(){
        for (int i = 0; i < 300; i++){
            HugeInteger h = new HugeInteger(100);
            BigInteger b = h.toBigInteger();
            System.out.println("Huge: " + HugeInteger.multiply(h, h));
            System.out.println("Big:  " + b.multiply(b) + "\n");
            
        }
    }
    public static void test(){
        HugeInteger huge1 = new HugeInteger("400001");
        HugeInteger huge2 = new HugeInteger("400000");
        HugeInteger huge3 = new HugeInteger("-400001");
        HugeInteger huge4 = new HugeInteger("-823409238409287234903242352");
        HugeInteger huge5 = new HugeInteger("-74981379123782197389123324234234");
        HugeInteger huge6 = new HugeInteger(100);
        BigInteger big1 = huge1.toBigInteger();
        BigInteger big2 = huge2.toBigInteger();
        BigInteger big3 = huge3.toBigInteger();
        BigInteger big4 = huge4.toBigInteger();
        BigInteger big5 = huge5.toBigInteger();
        
        System.out.println("Random HugeInt of 100 digits: " + huge6);
        
        //Compare positive
        System.out.println("Huge: " + huge1.compareTo(huge2));
        System.out.println("Big: " + big1.compareTo(big2));
        //Compare positive with negative, abs(neg) > abs(pos)
        System.out.println("Huge:" + huge3.compareTo(huge2));
        System.out.println("Big: " + big3.compareTo(big2));
        
        //Add zero
        System.out.println("Huge:" + huge3.add(HugeInteger.ZERO));
        System.out.println("Big: " + big3.add(BigInteger.ZERO));
        //Generic addition tests
        System.out.println("Huge:" + huge3.add(huge1));
        System.out.println("Big: " + big3.add(big1));
        System.out.println("Huge:" + huge3.add(huge3));
        System.out.println("Big: " + big3.add(big3));
        
        //subtract zero
        System.out.println("Huge:" + huge3.subtract(HugeInteger.ZERO));
        System.out.println("Big: " + big3.subtract(BigInteger.ZERO));
        //Generic subtraction tests
        System.out.println("Huge:" + huge3.subtract(huge1));
        System.out.println("Big: " + big3.subtract(big1));
        System.out.println("Huge:" + huge3.subtract(huge3));
        System.out.println("Big: " + big3.subtract(big3));
        
        //Multiplication
        System.out.println("Huge:" + huge3.multiply(huge1));
        System.out.println("Big: " + big3.multiply(big1));
        System.out.println("Huge:" + huge4.multiply(huge5));
        System.out.println("Big: " + big4.multiply(big5));
        //Zero and one
        System.out.println("Huge:" + huge3.multiply(HugeInteger.ZERO));
        System.out.println("Big: " + big3.multiply(BigInteger.ZERO));
        System.out.println("Huge:" + huge3.multiply(HugeInteger.ONE));
        System.out.println("Big: " + big3.multiply(BigInteger.ONE));
        
        
    }
    public static void compare(){
        final int n = 10000;
        final int MAXNUMINTS = 100;
        final int MAXRUN = 10000000;
        HugeInteger huge1, huge2;
        BigInteger big1, big2, big3;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n); //creates a random integer of n digits
            huge2 = new HugeInteger(n); //creates a random integer of n digits
            big1 = huge1.toBigInteger();
            big2 = huge2.toBigInteger();
            startTime = System.currentTimeMillis();
//            for (int numRun=0; numRun < MAXRUN; numRun++){ 
//                huge1.compareTo(huge2);
//            }
            for (int numRun=0; numRun < MAXRUN; numRun++){ 
                big1.compareTo(big2);
            }            
            endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        runTime = runTime/((double)MAXNUMINTS);
        
        System.out.println("For " + MAXNUMINTS + " integers of " + n + " digits, the runtime for comparison is about " + runTime + " ms/HugeInteger.");
    }
    public static void addition(){
//        for (int i = 0; i < 100; i++){
//            HugeInteger h = new HugeInteger(50);
//            //System.out.println(h);
//            System.out.println(h);
//        }
        final int n = 10000;
        final int MAXNUMINTS = 100;
        final int MAXRUN = 500000;
        HugeInteger huge1, huge2, huge3;
        BigInteger big1, big2, big3;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n); //creates a random integer of n digits
            huge2 = new HugeInteger(n); //creates a random integer of n digits
            big1 = huge1.toBigInteger();
            big2 = huge2.toBigInteger();
            startTime = System.currentTimeMillis();
//            for (int numRun=0; numRun < MAXRUN; numRun++){ 
//                huge3 = huge1.add(huge2);
//                //big3 = big1.add(big2);
//            //System.out.println("Big: " + big3 + " Huge: " + huge3);
//            }
            for (int numRun=0; numRun < MAXRUN; numRun++){ 
                big1.add(big2);
            }
            endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        runTime = runTime/((double)MAXNUMINTS);
        
        System.out.println("For " + MAXNUMINTS + " integers of " + n + " digits, the runtime for addition is about " + runTime + " ms/HugeInteger.");
    }
    public static void subtraction(){
//        for (int i = 0; i < 100; i++){
//            HugeInteger h = new HugeInteger(50);
//            //System.out.println(h);
//            System.out.println(h);
//        }
        final int n = 10000;
        final int MAXNUMINTS = 100;
        final int MAXRUN = Integer.MAX_VALUE/6000;
        HugeInteger huge1, huge2, huge3 = HugeInteger.ZERO;
        BigInteger big1, big2, big3 = BigInteger.ZERO;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n); //creates a random integer of n digits
            huge2 = new HugeInteger(n); //creates a random integer of n digits
            big1 = huge1.toBigInteger();
            big2 = huge2.toBigInteger();
            startTime = System.currentTimeMillis();
//            for (int numRun=0; numRun < MAXRUN; numRun++){ 
//                huge3 = huge1.subtract(huge2);
//                //big3 = big1.subtract(big2);
//            
//            }
            for (int numRun=0; numRun < MAXRUN; numRun++){ 
                big1.subtract(big2);
            }
            //System.out.println("Big: " + big3 + " Huge: " + huge3);
            //System.out.println(huge3);
            endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        runTime = runTime/((double)MAXNUMINTS);
        
        System.out.println("For " + MAXNUMINTS + " integers of " + n + " digits, the runtime for subtraction is about " + runTime + " ms/HugeInteger.");
    }
    public static void multiplication(){
        final int n = 10000;
        final int MAXNUMINTS = 10;
        final int MAXRUN = 1;
        HugeInteger huge1, huge2, huge3 = HugeInteger.ZERO;
        BigInteger big1, big2, big3 = BigInteger.ZERO;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n); //creates a random integer of n digits
            huge2 = new HugeInteger(n); //creates a random integer of n digits
            big1 = huge1.toBigInteger();
            big2 = huge2.toBigInteger();
            startTime = System.currentTimeMillis();
            for (int numRun=0; numRun < MAXRUN; numRun++){ 
                huge3 = HugeInteger.multiply(huge1, huge2);
                //big3 = big1.subtract(big2);
            
            }
//            for (int numRun=0; numRun < MAXRUN; numRun++){ 
//                big1.multiply(big2);
//            }
            //System.out.println("Big: " + big3 + " Huge: " + huge3);
            //System.out.println(huge3);
            endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        runTime = runTime/((double)MAXNUMINTS);
        
        System.out.println("For " + MAXNUMINTS + " integers of " + n + " digits, the runtime for multiplication is about " + runTime + " ms/HugeInteger.");
    }
    public static void naiveMultiplication(){
        final int MAXNUMINTS = 100;
        final int n = 50;
        final int MAXRUN = 20;
        HugeInteger huge1, huge2, huge3 = HugeInteger.ZERO;
        BigInteger big1, big2, big3 = BigInteger.ZERO;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
            huge1 = new HugeInteger(n); //creates a random integer of n digits
            huge2 = new HugeInteger(n); //creates a random integer of n digits
            big1 = huge1.toBigInteger();
            big2 = huge2.toBigInteger();
            startTime = System.currentTimeMillis();
            for (int numRun=0; numRun < MAXRUN; numRun++){ 
                huge3 = HugeInteger.multiplyNaive(huge1, huge2);
                //big3 = big1.subtract(big2);
            
            }
            //System.out.println("Big: " + big3 + " Huge: " + huge3);
            System.out.println(huge3);
            endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
        }
        runTime = runTime/((double)MAXNUMINTS);
        
        System.out.println("For " + MAXNUMINTS + " integers of " + n + " digits, the runtime for multiplication is about " + runTime + " ms/HugeInteger.");
    }
    public static void test3(){
        int num = 250;
        ArrayList<Integer> il = new ArrayList<>();
        ArrayList<HugeInteger> hil = new ArrayList<>();
        for (int i = 0; i < num; i++){
            il.add(i);
        }
        il.stream().forEach((il1) -> {
            hil.add(new HugeInteger(Integer.toString(il1)));
        });
        
        //HugeInteger m = HugeInteger.multiply(new HugeInteger("17"), new HugeInteger("149"));
        //System.out.println(m);
        
        //HugeInteger n = HugeInteger.multiply(new HugeInteger("17"), new HugeInteger("151"));
        //System.out.println(n);
        
        System.out.println(HugeInteger.multiplyNaive(new HugeInteger("7"), new HugeInteger("0")));
        
        boolean show = true;
        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
                String m1 = HugeInteger.multiplyNaive(hil.get(i), hil.get(j)).toString();
                String m2 = HugeInteger.multiply(hil.get(i), hil.get(j)).toString();
                if (show){
                    System.out.println("Regular multiplication:   " + m1);
                    System.out.println("Karatsuba multiplication: " + m2);
                    System.out.println("Numbers: " + hil.get(i) + ", " + hil.get(j));
                    System.out.println("Lengths: " + hil.get(i).length() + ", " + hil.get(j).length());
                }
                
                
                if (m1.compareTo(m2) != 0) {
                    System.out.println("Multiplicands: " + hil.get(i) + ", " + hil.get(j));
                    System.out.println("Inconsistent; correct product: " + m1 + ", incorrect product generated by code: " + m2);
                }
                
            }
        }
    }
    public static void test(int start, int end){
        String s = "bobsled";
        
        System.out.println("TEST: " +  s.substring(s.length() - end, s.length() - start));
    }
    public static void test2(){
        ArrayList<Integer> il = new ArrayList<>();
        ArrayList<HugeInteger> hil = new ArrayList<>();
        il.add(-1234);  //0
        il.add(1000);   //1
        il.add(-500);   //2
        il.add(12);     //3
        il.add(-12);    //4
        
        for (Integer il1 : il) {
            hil.add(new HugeInteger(Integer.toString(il1)));
        }
        hil.add(new HugeInteger("-92389048209302342234"));  //5
        hil.add(new HugeInteger("4238900113289"));   //6
        hil.add(new HugeInteger("-500000000000000000"));
        hil.add(new HugeInteger("500000000000000001"));
        hil.add(new HugeInteger("3312123214"));
        hil.add(new HugeInteger("-3312123214"));
        hil.add(new HugeInteger("-865"));
        hil.add(new HugeInteger("1245678910111213"));
        hil.add(new HugeInteger("1000000000000000000000000000000000000001")); 
        hil.add(new HugeInteger("1245678910111213")); //14
        hil.add(new HugeInteger("51616515615616515")); //15
        hil.add(new HugeInteger("824554390508345099")); //16
        hil.add(new HugeInteger("-8348000219839182997888888888888888888888888888888888888")); //17
//        System.out.println("HugeInt Sum: " + hil.get(0).add(hil.get(2)) + " Sum: " + (il.get(0) + il.get(2)));
//        System.out.println("HugeInt Sum: " + hil.get(1).add(hil.get(2)) + " Sum: " + (il.get(1) + il.get(2)));
//        System.out.println("HugeInt Sum: " + hil.get(2).add(hil.get(3)) + " Sum: " + (il.get(2) + il.get(3)));
//        System.out.println("HugeInt Sum: " + hil.get(3).add(hil.get(2)) + " Sum: " + (il.get(3) + il.get(2)));
//        System.out.println("HugeInt Sum: " + hil.get(3).add(hil.get(4)) + " Sum: " + (il.get(3) + il.get(4)));
//        System.out.println("HugeInt Sum: " + hil.get(6).add(hil.get(5)));
//        System.out.println("HugeInt Sum: " + hil.get(5).subtract(hil.get(6)));
//        System.out.println("HugeInt Sum: " + hil.get(6).subtract(hil.get(5)));
//        System.out.println("HugeInt Sum: " + hil.get(7).subtract(hil.get(8)));
//        System.out.println("HugeInt Sum: " + hil.get(3).subtract(hil.get(4)));
//        System.out.println(hil.get(0).shift(0, true));
//        System.out.println("HugeInt product: " + HugeInteger.multiplyNaive(hil.get(9), hil.get(10)));
//        System.out.println("HugeInt substring: " + "(" + 5 + "," + 8 + ") " + hil.get(9).substring(0, hil.get(9).length()));
//        System.out.println("HugeInt substring: " + "(" + 5 + "," + 8 + ") " + hil.get(10).substring(0, hil.get(10).length()));
//        System.out.println(hil.get(11).length());
//        System.out.println(hil.get(11).substring(0, hil.get(11).length()));
        test(0, 7);
//        System.out.println("backstring: " + hil.get(12).backwardsSubstring(1, 12));
//        
//        HugeInteger.testMultiply(hil.get(12), hil.get(13));
//        HugeInteger.testMultiply(hil.get(12), hil.get(13));
        //System.out.println("Karatsuba: " + HugeInteger.multiply(hil.get(6), hil.get(3)));
//        System.out.println("Regular multiplication:   " + HugeInteger.multiplyNaive(hil.get(6), hil.get(3)));
//        System.out.println("Karatsuba multiplication: " + HugeInteger.multiply(hil.get(6), hil.get(3)));
//        System.out.println("Regular multiplication:   " + HugeInteger.multiplyNaive(hil.get(3), hil.get(6)));
//        System.out.println("Karatsuba multiplication: " + HugeInteger.multiply(hil.get(3), hil.get(6)));
//        System.out.println("Numbers: " + hil.get(6) + ", " + hil.get(3));
//        System.out.println("Lengths: " + hil.get(6).length() + ", " + hil.get(3).length());
//        for (int i = 0; i <= 6; i++){
//            for (int j = 0; j <= 6; j++){
//                System.out.println("Regular multiplication:   " + HugeInteger.multiplyNaive(hil.get(i), hil.get(j)));
//                System.out.println("Karatsuba multiplication: " + HugeInteger.multiply(hil.get(i), hil.get(j)));
//                System.out.println("Numbers: " + hil.get(i) + ", " + hil.get(j));
//                System.out.println("Lengths: " + hil.get(i).length() + ", " + hil.get(j).length());
//            }
//        }
        for (int i = 14; i <= 17; i++){
            for (int j = 14; j <= 17; j++){
                System.out.println("Regular multiplication:   " + HugeInteger.multiplyNaive(hil.get(i), hil.get(j)));
                System.out.println("Karatsuba multiplication: " + HugeInteger.multiply(hil.get(i), hil.get(j)));
                System.out.println("Numbers: " + hil.get(i) + ", " + hil.get(j));
                System.out.println("Lengths: " + hil.get(i).length() + ", " + hil.get(j).length());
            }
        }
//        for (int i = 0; i < 7; i++){
//            System.out.println(hil.get(i).length());
//        }
    }
    public static void test1(){
        HugeInteger h = new HugeInteger("12345");
        HugeInteger h1 = new HugeInteger("34289");
        HugeInteger h2 = h.addAbs(h1);
        System.out.println(h2.toString());
        
        int i1 = 1562431;
        int i2 = 12312;
        int i3 = 400123;
        int i4 = 400122;
        String s1 = toString(i1);
        String s2 = toString(i2);
        String s3 = toString(i3);
        String s4 = toString(i4);
        HugeInteger h3 = new HugeInteger(s1);
        HugeInteger h4 = new HugeInteger(s2);
        HugeInteger h5 = h3.addAbs(h4);
        HugeInteger j3 = new HugeInteger(s3);
        HugeInteger j4 = new HugeInteger(s4);
        HugeInteger j5 = j3.subtractAbs(j4);
        System.out.println(h5.toString() + " Sum: " + (i1 + i2));
        
        HugeInteger h6 = h3.subtractAbs(h4);
        HugeInteger h7 = h6.complimentWRT(h6);
        HugeInteger h8 = h4.subtractAbs(h3);
        HugeInteger h9 = j3.subtractAbs(h3);
        System.out.println("h6: " + h6 + " h7: " + h7);
        System.out.println("Comparison: " + j3.compareToAbsVal(j4));
        System.out.println(h3.complimentWRT(h4).toString());
        System.out.println(h4.complimentWRT(h3).toString());
        System.out.println(h6.toString() + " Sum: " + (i1 - i2));
        System.out.println(j5.toString() + " Sum: " + (i3 - i4));
        System.out.println(h8.toString() + " Sum: " + (i2 - i1));
        System.out.println(h9 + " Sum: " + (i3 - i1));
    }
    public static String toString(int i){
        return Integer.toString(i);
    }
}
