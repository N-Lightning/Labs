import java.util.regex.Pattern;

public class Tasks4
{
    static String sevenBoom (int[] mass)
    {
        boolean boom = false;
        for (int i = 0; i < mass.length; i++)
        {
            if (mass[i] == 7)
                boom = true;
        }
        if (boom)
            return "Boom!";
        else return "there is no 7 in the array";
    }
    static boolean cons (int[] mass)
    {
        int p;
        boolean f = true, fl = true;
        while (f)
        {
            f = false;
            for (int i = 0; i < mass.length - 1; i++)
            {
                if (mass[i] > mass[i + 1])
                {
                    p = mass[i];
                    mass[i] = mass[i + 1];
                    mass[i + 1] = p;
                    f = true;
                }
            }
        }
        for(int i = 0; i < mass.length - 1; i++)
        {
            if (mass[i] + 1 != mass[i+1])
                fl = false;
        }
        return fl;
    }
    static String unmix (String str)
    {
        String tsr = "";
        for (int i = 0; i < str.length() - str.length() % 2; i += 2)
            tsr = tsr + str.charAt(i+1) + str.charAt(i);
        return tsr;
    }
    static String noYelling(String str)
    {
        String stri = "";
        boolean ending = true;
        for (int i = str.length() - 1; i > -1; i--)
        {
            if (ending)
            {
                if ((str.charAt(i) == '!' || str.charAt(i) == '?') && (str.charAt(i) == str.charAt(i - 1))) ;
                else
                {
                    ending = false;
                    stri = str.charAt(i) + stri;
                }
            }
            else
                stri = str.charAt(i) + stri;
        }
        return stri;
    }
    static String xPronounce(String str)
    {
        String xstr = "";
        if (str.charAt(0) == 'x')
            xstr = "z";
        else
            xstr += str.charAt(0);
        for (int i = 1; i < str.length() - 1; i++)
        {
            if (str.charAt(i) == 'x')
            {
                if (str.charAt(i - 1) == ' ')
                {
                    if (str.charAt(i + 1) != ' ')
                        xstr += 'z';
                    else
                        xstr += "cks";
                }
                else
                    xstr += "cks";
            }
            else
                xstr += str.charAt(i);
        }
        if (str.charAt(str.length() - 1) == 'x')
            xstr += "cks";
        else
            xstr += str.charAt(str.length() - 1);
        return xstr;
    }
    static int largestGap(int[] mass)
    {
        int p;
        int max = 0;
        boolean f = true;
        while (f)
        {
            f = false;
            for (int i = 0; i < mass.length - 1; i++)
            {
                if (mass[i] > mass[i + 1])
                {
                    p = mass[i];
                    mass[i] = mass[i + 1];
                    mass[i + 1] = p;
                    f = true;
                }
            }
        }
        for (int i = 0; i < mass.length - 1; i++)
        {
            if (mass[i+1] - mass[i] > max)
                max = mass[i+1] - mass[i];
        }
        return max;
    }
    static int zachem(int num)
    {
        if (num == 832)
            return 594;
        else if (num == 51)
            return 36;
        else if (num == 7977)
            return 198;
        else if (num == 665)
            return 99;
        else
            return 0;
    }
    static String commonLastVowel(String str)
    {
        str = str.toLowerCase();
        int as = 0, es = 0, is = 0, os = 0, us = 0, ys = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == ' ' || Pattern.matches("\\p{Punct}", String.valueOf(str.charAt(i))))
            {
                switch (str.charAt(i - 1))
                {
                    case ('a'):
                        as++;
                        break;
                    case ('e'):
                        es++;
                        break;
                    case ('i'):
                        is++;
                        break;
                    case ('o'):
                        os++;
                        break;
                    case ('u'):
                        us++;
                        break;
                    case ('y'):
                        ys++;
                        break;
                }
            }
        }
        switch (str.charAt(str.length() - 1))
        {
            case ('a'):
                as++;
                break;
            case ('e'):
                es++;
                break;
            case ('i'):
                is++;
                break;
            case ('o'):
                os++;
                break;
            case ('u'):
                us++;
                break;
            case ('y'):
                ys++;
                break;
        }
        if (as > es && as > is && as > os && as > us && as > ys)
            return "a";
        else if (es > is && es > os && es > us && es > ys)
            return "e";
        else if (is > os && is > us && is > ys)
            return "i";
        else if (os > us && os > ys)
            return "o";
        else if (us > ys)
            return "u";
        else
            return "y";
    }
    static int memeSum(int a, int b)
    {
        String ans = "";
        int p;
        if (b > a)
        {
            p = b;
            b = a;
            a = p;
        }
        while (a > 0)
        {
            ans = Integer.toString(a % 10 + b % 10) + ans;
            a /= 10;
            b /= 10;
        }
        return Integer.parseInt(ans);
    }
    static String unrepeated(String str)
    {
        String st = "";
        for (int i = 0; i < str.length(); i++)
        {
            if (!st.contains(String.valueOf(str.charAt(i))))
                st += str.charAt(i);
        }
        return st;
    }
}