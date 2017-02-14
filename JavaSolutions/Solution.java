import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        String[] firstLine = sc.nextLine().split(" ");
        int money = Integer.parseInt(firstLine[0]);
        
        String[] coinString = sc.nextLine().split(" ");
        int[] coins = new int[coinString.length];
        for(int i = 0; i < coinString.length; i++) {
            coins[i] = Integer.parseInt(coinString[i]);
        }
        
        System.out.println(getChange(money, coins));
    }
    
    public static int getChange(int money, int[] coins) {
        if(money <= 0)
            return 0;
        
        int[] memo = new int[money];
        return getChange(money, coins, memo);
    }
    
    public static int getChange(int money, int[] coins, int[] memo) {
        if(money < 0)
            return 0;
        if(money == 0)
            return 1;
        
        if(memo[money-1] != 0)
            return memo[money-1];
        
        int totalWays = 0;
        for(int i = 0; i < coins.length; i++) {
            totalWays += getChange(money - coins[i], coins, memo);
        }
        memo[money-1] = totalWays;
        
        return totalWays;
    }
}