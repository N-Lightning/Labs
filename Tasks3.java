import java.util.Arrays;

public class Tasks3
{
    static Object[][] millionsRounding(Object[][] array)
    {
        for(int i = 0; i < array.length; i++)
            array[i][1] = ((int)array[i][1] + 500000) / 1000000 * 1000000;
        return array;
    }
    static double[] otherSides(int f)
    {
        double[] ff = {f * 2, (double) Math.round(Math.sqrt(3) * f * 100) / 100};
        return ff;
    }
    static String rps(String p1, String p2)
    {
        if (p1 == p2)
            return "TIE";
        else if ((p1 == "rock") && (p2 == "scissors") || (p1 == "paper") && (p2 == "rock") || (p1 == "scissors") && (p2 == "paper"))
            return "Player 1 wins";
        else
            return "Player 2 wins";
    }
    static int warOfNumbers(int[] war)
    {
        int odd = 0, nod = 0;
        for (int i = 0; i < war.length; i++)
        {
            if (war[i] % 2 == 0)
                odd += war[i];
            else
                nod += war[i];
        }
        odd = Math.abs(odd - nod);
        return odd;
    }
    static String reverseCase(String str)
    {
        String STR = "";
        Character character;
        for (int i = 0; i < str.length(); i++)
        {
            character = str.charAt(i);
            if (Character.isUpperCase(character))
                STR += Character.toLowerCase(character);
            else
                STR += Character.toUpperCase(character);
        }
        return STR;
    }
    static String inatorInator(String notInator)
    {
        Character last = notInator.charAt(notInator.length() - 1);
        if (last == 'a' || last == 'e' || last == 'i' || last == 'o' || last == 'u' || last == 'y')
            return notInator + "-inator " + notInator.length() + "000";
        else
            return notInator + "inator " + notInator.length() + "000";
    }
    static boolean doesBrickFit (int a, int b, int c, int w, int h)
    {
        if (a <= w && (b <= h || c <= h))
            return true;
        else if (b <= w && (a <= h || c <= h))
            return true;
        else if (c <= w && (a <= h || b <= h))
            return true;
        else
            return false;
    }
    static double totalDistance (double l, double r, int p, boolean k)
    {
        if (k)
            return l / (r + r * p * 0.05) / 1.1 * 100;
        else
            return l / (r + r * p * 0.05) * 100;
    }
    static double mean (int[] mass)
    {
        return Arrays.stream(mass).average().orElse(Double.NaN);
    }
    static boolean parityAnalysis(int num)
    {
        int numm = num, sum = 0;
        while (numm > 0)
        {
            sum += numm % 10;
            numm /= 10;
        }
        if (sum % 2 == num % 2)
            return true;
        else
            return false;
    }
}