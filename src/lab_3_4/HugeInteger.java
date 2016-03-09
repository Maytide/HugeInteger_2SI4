/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab_3_4;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author George
 */
public class HugeInteger {
    static final HugeInteger ZERO = new HugeInteger("0");
    static final HugeInteger ONE = new HugeInteger("1");
    private int integer[];
    private boolean plus;
    private int length;
    public HugeInteger(String val){
        //The integer is stored backwards...eg,
        //12345 would be stored as arr[0] = 5, arr[1] = 4, ... arr[4] = 1
        if (val.charAt(0) == '-'){
            this.plus = false;
            val = val.substring(1);
        }else{
            this.plus = true;
        }
        integer = new int[val.length()];
        
        //System.out.println(val.charAt(0) + " " + Character.getNumericValue(val.charAt(0)));
        
        for (int i = val.length() - 1; i >= 0; i--){
            integer[val.length() - i - 1] = Character.getNumericValue(val.charAt(i));
        }
        this.length = integer.length;
    }
    public HugeInteger (int n){
        
        Random r = new Random();
        integer = new int[n];
        for (int i = 0; i < n - 1; i++){
            integer[i] = r.nextInt(10);
        }
        integer[n-1] = r.nextInt(9) + 1;
        this.length = n;
        this.plus = true;
    }
//    public HugeInteger (long n, boolean parseLongIntoHugeInteger){
//        
//        plus = n > 0;
//        if (!plus) n = -n;
//        
//        long temp = n;
//        length = 0;
//        while (temp > 0){
//            temp = temp/10;
//            length++;
//        }
//        integer = new int[length];
//        for (int i = 0; i < length; i++){
//            integer[i] = (int) n % 10;
//            n = n / 10;
//        }
//    }
    public BigInteger toBigInteger(){
        return new BigInteger(this.toString());
    }
    //O(n^2) multiplication
    public HugeInteger multiply(HugeInteger h){
        return HugeInteger.multiply(this, h);
    }
    public static HugeInteger multiplyNaive(HugeInteger h1, HugeInteger h2){
        int a = h1.length();
        int b = h2.length();
        HugeInteger h = new HugeInteger("0");
        
        for (int i = 0; i < a; i++){
            for (int j = 0; j < b; j++){
                //System.out.println(h1.getStoredDigit(i));
                //System.out.println(h2.getStoredDigit(j));
                //System.out.println("digit product:" + Integer.toString(h1.getStoredDigit(i) * h2.getStoredDigit(j)));
                HugeInteger temp = new HugeInteger(Integer.toString(h1.getStoredDigit(i) * h2.getStoredDigit(j)));
                //System.out.println("temp: " + temp);
                h = h.add(temp.shift(i+j, true));
                //System.out.println("h: " + h);
            }
        }
        if (h1.getSign() != h2.getSign()) h.setSign(false);
        return h;
        
    }
    public static HugeInteger multiply(HugeInteger h1, HugeInteger h2){
        //Karatsuba algorithm
        if (h1.length() <= 11 && h2.length() <= 11) {
            //return new HugeInteger(Long.parseLong(h1.toString()) * Long.parseLong(h2.toString()), true);
            return new HugeInteger(Long.toString(Long.parseLong(h1.toString()) * Long.parseLong(h2.toString())));
            //return HugeInteger.multiplyNaive(h1, h2).abs();
        }
        //System.out.println("Recursion O_O");
        HugeInteger h;
        int thisSize = h1.length();
        int thatSize = h2.length();
        
        int half = Math.max(thisSize, thatSize);
        
        half = half/2 + half%2;
        
        //if (half > thisSize || half > thatSize) half = Math.min(thisSize, thatSize);
        
        //System.out.println("h1: " + h1 + " h2: " + h2);
        //System.out.println("half: " + half + "\nh1 size: " + thisSize + "\nh2 size: " + thatSize);
        
        HugeInteger h1t1;
        HugeInteger h2t1;
        //if (half - h1.length() >= 0) h1t1 = HugeInteger.ZERO;
        if (half - h1.length() >= 0) h1t1 = new HugeInteger("0");
        else h1t1 = h1.backwardsSubstring(half, h1.length());
        //if (half - h2.length() >= 0) h2t1 = HugeInteger.ZERO;
        if (half - h2.length() >= 0) h2t1 = new HugeInteger("0");
        else h2t1 = h2.backwardsSubstring(half, h2.length());
        int point1 = Math.min(half, thisSize);
        //int point2 = Math.min(half, h2.length());
        int point2 = Math.min(half, thatSize);
        HugeInteger h1t2 = h1.backwardsSubstring(0, point1);
        HugeInteger h2t2 = h2.backwardsSubstring(0, point2);
        //works with regular (signless), but not backwards
        //if (half < h2.length()) h2t2 = h2.backwardsSubstring(half, h2.length());
//        if (half < h2.length()) h2t2 = h2.backwardsSubstring(0, half);
//        else h2t2 = new HugeInteger("0");
//        if (half < h1.length()) h1t2 = h1.backwardsSubstring(0, half);
//        //else if (half == h1.length()) h1t2 = h1;
//        else h1t2 = new HugeInteger("0");
        
        //System.out.println("h1t1: " + h1t1.toStringWithZeroes() + " \nh1t2: " + h1t2.toStringWithZeroes() + " \nh2t1: " + h2t1.toStringWithZeroes() + " \nh2t2: " + h2t2.toStringWithZeroes());
        
        HugeInteger z2 = HugeInteger.multiply(h1t1, h2t1);
        HugeInteger z0 = HugeInteger.multiply(h1t2, h2t2);
        if (z0.getDigit(0) == 0) z0 = z0.trim();
        HugeInteger z1 = HugeInteger.multiply(h1t1.addAbs(h1t2), h2t1.addAbs(h2t2)).subtractAbs(z2).subtractAbs(z0);
        
        //System.out.println("z2: " + z2.toStringWithZeroes() + " z1: " + z1.toStringWithZeroes() + " z0: " + z0.toStringWithZeroes());
        z2 = z2.shift(2*half, true);
        z1 = z1.shift(half, true);
        //System.out.println("z2: " + z2.toStringWithZeroes() + " z1: " + z1.toStringWithZeroes() + " z0: " + z0.toStringWithZeroes());
        
        //System.out.println("z2: " + z2 + " z1: " + z1 + " z0: " + z0);
        //System.out.println(z2.add(z1).add(z0));
        
        h = z2.addAbs(z1).addAbs(z0);
        if (h1.getSign() != h2.getSign()) h.setSign(false);
        return h;
    }
//    public static void testMultiply(HugeInteger h1, HugeInteger h2){
//        HugeInteger h = new HugeInteger("0");
//        int thisSize = h1.length();
//        int thatSize = h2.length();
//        
//        int half = Math.max(thisSize, thatSize);
//        
//        half = half/2 + half%2;
//        
//        //if (half > thisSize || half > thatSize) half = Math.min(thisSize, thatSize);
//        
//        System.out.println("h1: " + h1 + " h2: " + h2);
//        System.out.println("half: " + half + "\nh1 size: " + thisSize + "\nh2 size: " + thatSize);
//        
//        HugeInteger h1t1;
//        HugeInteger h2t1;
//        if (half - h1.length() >= 0) h1t1 = new HugeInteger("0");
//        else h1t1 = h1.backwardsSubstring(half, h1.length());
//        if (half - h2.length() >= 0) h2t1 = new HugeInteger("0");
//        else h2t1 = h2.backwardsSubstring(half, h2.length());
//        HugeInteger h2t2;
//        HugeInteger h1t2;
//        //works with regular (signless), but not backwards
//        //if (half < h2.length()) h2t2 = h2.backwardsSubstring(half, h2.length());
//        if (half < h2.length()) h2t2 = h2.backwardsSubstring(0, half-1);
//        else h2t2 = new HugeInteger("0");
//        if (half < h1.length()) h1t2 = h1.backwardsSubstring(0, half-1);
//        //else if (half == h1.length()) h1t2 = h1;
//        else h1t2 = new HugeInteger("0");
//        
//        System.out.println("h1t1: " + h1t1 + " \nh1t2: " + h1t2 + " \nh2t1: " + h2t1 + " \nh2t2: " + h2t2);
//        
//        HugeInteger z2 = HugeInteger.multiplyNaive(h1t1, h2t1);
//        HugeInteger z0 = HugeInteger.multiplyNaive(h1t2, h2t2);
//        HugeInteger z1 = HugeInteger.multiplyNaive(h1t1.add(h1t2), h2t1.add(h2t2)).subtractAbs(z2).subtractAbs(z0);
//        
//        System.out.println("z2: " + z2 + " z1: " + z1 + " z0: " + z0);
//        
//        z2 = z2.shift(2*half, true);
//        z1 = z1.shift(half, true);
//        
//        System.out.println("z2: " + z2 + " z1: " + z1 + " z0: " + z0);
//        System.out.println(z2.add(z1).add(z0));
//        
//        
//        
//        
//    }
    //Left = true, right = false
    public HugeInteger shift(int n, boolean direction){
        if (n < 0) {
            direction = !direction;
        }
        HugeInteger h;
        String HugeInt = "";
        if (direction){
            for (int i = 0; i < this.length(); i++){
                HugeInt += this.getDigit(i);
            }
            for (int i = 0; i < n; i++){
                HugeInt += "0";
            }
        }
        h = new HugeInteger(HugeInt);
        if (!this.getSign()) h.setSign(false);
        return h;
    }
    public HugeInteger abs(){
        HugeInteger h = this;
        h.setSign(true);
        return h;
    }
    //Calculates |this| + |that|
    public HugeInteger addAbs(HugeInteger that){
        int len = that.length() < this.length() ? this.length() : that.length();
        int carry = 0, digit = 0;
        String s = "";
        
        for (int i = 0; i < len; i++){
            int sum = this.getStoredDigit(i) + that.getStoredDigit(i) + carry;
            digit = sum % 10;
            carry = (sum - digit)/10;
            
            //System.out.println("Sum: " +  sum + " Carry: " + carry + " Digit: " + digit);
            
            s += Integer.toString(digit);
        }
        if (carry != 0) s += carry;
        
        s = new StringBuffer(s).reverse().toString();
        
        return new HugeInteger(s);
    }
    public int compareToAbsVal(HugeInteger that){
        if (this.length() < that.length()) return -1;
        else if (this.length() > that.length()) return 1;
        //At this point we can be certain this.length() == that.length()
        for (int i = this.length() - 1; i >= 0; i--){
            if (this.getStoredDigit(i) < that.getStoredDigit(i)) return -1;
            else if (this.getStoredDigit(i) > that.getStoredDigit(i)) return 1;
        }
        return 0;
    }
    //Calculates |this| - |that|
    public HugeInteger subtractAbs(HugeInteger that){
        HugeInteger bigger;
        HugeInteger smaller;
        boolean pos = true;
        
        //When this compared to that is 1, this > that
        //System.out.println("This: " + this + " That: " + that + " comparison: " + this.compareTo(that));
        //System.out.println("This: " + this + " That: " + that + " comparison: " + this.compareToAbsVal(that));
        if (this.compareToAbsVal(that) == 1){
            bigger = this;
            smaller = that;
        }else if (this.compareToAbsVal(that) == -1){
            bigger = that;
            smaller = this;
        }else{
            return HugeInteger.ZERO;
        }
//        if (this.compareTo(that) == 1){
//            pos = false;
//        }
//        bigger = this.compareToAbsVal(that) == 1? this:that;
//        smaller = this.compareToAbsVal(that) == 1? that:this;
        
        //HugeInteger h = bigger.addAbs(smaller.complimentWRT(bigger)).addAbs(new HugeInteger("1")).trim();
        HugeInteger h = bigger.addAbs(smaller.complimentWRT(bigger)).addAbs(HugeInteger.ONE).trim();
        if (!pos) h.setSign(false);
        
        return h;
    }
    public int compareTo(HugeInteger that){
        if (this.getSign() == that.getSign()){
            return this.compareToAbsVal(that);
        }else if (this.getSign() == true && that.getSign() == false){
            return +1;
        }else if (this.getSign() == false && that.getSign() == true){
            return -1;
        }
        return 0;
    }
    public HugeInteger add(HugeInteger that){
        HugeInteger h;
        if (this.getSign() == that.getSign()){
            h = this.addAbs(that);
            h.setSign(this.getSign());
        }else if (this.getSign() == false){
            h = this.subtractAbs(that);
            if (this.compareToAbsVal(that) == 1) h.setSign(false);
        }else{ //else if (that.getSign() == false
            h = this.subtractAbs(that);
            if (that.compareToAbsVal(this) == 1) h.setSign(false);
        }
        return h;
    }
    public HugeInteger subtract(HugeInteger that){
        HugeInteger h = new HugeInteger("0");
        if (this.getSign() != that.getSign()){
            h = this.addAbs(that);
            if (this.getSign() == false){
                h.setSign(false);
            }else{
                h.setSign(true);
            }
        }else if (this.getSign() == true){
            h = this.subtractAbs(that);
            if (this.compareToAbsVal(that) == -1)h.setSign(false);
        }else if (this.getSign() == false){
            h = this.subtractAbs(that);
            if (this.compareToAbsVal(that) == 1)h.setSign(false);
        }
        return h;
    }
    //WRT = with respect to (the length of that)
    //Calculates the r's complement of that with leading zeroes to make it the same length as this.
//    public HugeInteger trimLeadingZeroes(){
//        return new HugeInteger()
//    }
    public HugeInteger complimentWRT(HugeInteger that){
        String s = "";
        int len = that.length() < this.length() ? this.length() : that.length();
        for (int i = len - 1; i >= 0; i--){
            s += Integer.toString(9 - this.getStoredDigit(i));
        }
        return new HugeInteger(s);
    }
    private HugeInteger trim(){
        //this.setDigit(this.length() - 1, 0);
        this.desize();
        return this;
    }
    //desizes the array by one digit
    private void desize(){
        int arr[] = new int[this.length() - 1];
        for (int i = 0; i < this.length() - 1; i++){
            arr[i] = this.getStoredDigit(i);
        }
        this.integer = arr;
        this.length--;
    }
    //Displays number backwards...not used
    public String toStoredString(){
        String s = "";
        for (int i = 0; i < this.length(); i++){
            s += this.getStoredDigit(i);
        }
        return s;
    }
    @Override
    public String toString(){
//        if (this.length() == 0) {
//            return "0";
//        }
        String s = "";
        if (this.length() == 1) {
            if (!this.plus) return "-" + Integer.toString(this.getStoredDigit(0));
            else return Integer.toString(this.getStoredDigit(0));
        }
        boolean zero = true;
        if (!this.plus) s += '-';
        for (int i = this.length() - 1; i >= 0; i--){
            if (this.getStoredDigit(i) != 0) zero = false;
            if (!zero) s += this.getStoredDigit(i);
        }
        if (zero) s = "0";
        return s;
    }
    public String toUnsignedString(){
        String s = "";
        if (this.length() == 1)return Integer.toString(this.getStoredDigit(0));
        boolean zero = true;
        for (int i = this.length() - 1; i >= 0; i--){
            if (this.getStoredDigit(i) != 0) zero = false;
            if (!zero) s += this.getStoredDigit(i);
        }
        
        return s;
    }
    public String toStringWithZeroes(){
        String s = "";
//        if (this.length() == 1) {
//            if (!this.plus) return "-" + Integer.toString(this.getStoredDigit(0));
//            else return Integer.toString(this.getStoredDigit(0));
//        }
        if (!this.plus) s += '-';
        for (int i = this.length() - 1; i >= 0; i--){
            s += this.getStoredDigit(i);
        }
        return s;
    }
    public String toUnsignedStringWithZeroes(){
        String s = "";
        //if (!this.plus) s += '-';
        if (this.length() == 1) {
            if (!this.plus) /*return "-" +*/ Integer.toString(this.getStoredDigit(0));
            else return Integer.toString(this.getStoredDigit(0));
        }
        for (int i = this.length() - 1; i >= 0; i--){
            s += this.getStoredDigit(i);
        }
        return s;
    }
    public HugeInteger signlessSubstring(int start, int end){
        if (this.getSign())return this.substring(start, end);
        else return new HugeInteger(this.toString().substring(start+1, end+1));
    }
    //with leading zeroes
//    public HugeInteger signlessSubstringWRT(int start, int end, HugeInteger h2){
//        String s1 = this.toUnsignedString();
//        
//        if (this.length() > h2.length()){
//            for (int i = 0; i < this.length() - h2.length(); i++)s1 = '0' + s1;
//            return new HugeInteger(s1);
//        }else{
//            return this;
//        }
//    }
    public HugeInteger substring(int start, int end){
        //System.out.println(this);
        //System.out.println(this.getSign());
        System.out.println(this);
        
        if (this.getSign())return new HugeInteger(this.toString().substring(start, end));
        else return new HugeInteger(this.toString().substring(start, end+1));
        
    }
    public HugeInteger backwardsSubstring(int start, int end){
        //String s = "0";
//        System.out.println("length: " + this.length() + " start: " + start + " end: " + end);
//        System.out.println("Backwards is: start: " + (this.length() - end) + " end: " + (this.length() - start));
//        System.out.println("Int to be reversed: " + this.toUnsignedStringWithZeroes());
//        System.out.println("Length of said int: " + this.toUnsignedStringWithZeroes().length());
        return new HugeInteger(this.toUnsignedStringWithZeroes().substring(this.length() - end, this.length() - start));
    }
    public int getDigit(int i){
        //returns zero for a digit which is greater than the number's base 10 length
        int digit = i < this.length()? integer[this.length() - i - 1]: 0;
        return digit;
        //return integer[i];
    }
    public int getStoredDigit(int i){
        //returns zero for a digit which is greater than the number's base 10 length
        int digit = i < this.length()? integer[i] : 0;
        return digit;
        //return integer[i];
    }
    //Makes HugeInteger mutable, however this mutator shouldn't been needed very often...
    public void setDigit(int i, int num){
        integer[i] = num;
    }
    public void setSign(boolean plus){
        this.plus = plus;
    }
    public boolean getSign(){
        return this.plus;
    }
    public int length(){
        return this.length;
    }
}
