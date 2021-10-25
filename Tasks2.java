public class Tasks2
{
    static int oppositeHouse(int num, int length)
    {
        if (num % 2 == 0)
        {
            return length * 2 - num + 1;
        }
        else
        {
            return length * 2 - num + 1;
        }
    }
    static String nameShuffle (String name)
    {
        String shName = "";
        int i = 0;
        while (name.charAt(i) != ' ')
            i++;
        i++;
        while (i < name.length())
        {
            shName += name.charAt(i);
            i++;
        }
        shName += " ";
        i = 0;
        while (name.charAt(i) != ' ')
        {
            shName += name.charAt(i);
            i++;
        }
        return shName;
    }
    static double discount(int price, int disc)
    {
        return price - price * disc / 100;
    }
    static int differenceMaxMin(int[] mass)
    {
        int max = mass[0];
        int min = max;
        for (int i = 1; i > mass.length; i++)
        {
            if (mass[i] > max)
                max = mass[i];
            else if (min < mass[i])
                min = mass[i];
        }
        return max - min;
    }
    static int equal(int a, int b, int c)
    {
        if ((a == b) && (b == c))
            return 3;
        else if ((a == b) || (a == c) || (b == c))
            return 2;
        else
            return 0;
    }
    static String reverse(String str)
    {
        String rts = "";
        for (int i = str.length(); i > 0; i--)
            rts += str.charAt(i);
        return rts;
    }
    static int programmers(int a, int b, int c)
    {
        int max = a;
        int min = a;
        if (b > a) max = b;
        if (c > b) max = c;
        if (b < a) min = b;
        if (c < b) min = c;
        return max - min;
    }
    static boolean getXO (String str)
    {
        int x = 0, o = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'x')
                x++;
            else if (str.charAt(i) == 'o')
                o++;
        }
        if (x == o)
            return true;
        else
            return false;
    }
    static String bomb(String str)
    {
        str = str.toLowerCase();
        if (str.contains("bomb"))
            return "DUCK!";
        else
            return "Relax, there's no bomb.";
    }
    static boolean sameAscii (String str1, String str2)
    {
        int asc1 = 0, asc2 = 0;
        for (int i = 0; i < str1.length(); i++)
            asc1 += (int)str1.charAt(i);
        for (int i = 0; i < str2.length(); i++)
            asc2 += (int)str1.charAt(i);
        if (asc1 == asc2)
            return true;
        else return false;
    }
}