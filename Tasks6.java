public class Tasks6
{
    static String hiddenAnagram(String[] mass)
    {
        mass[0] = mass[0].toLowerCase();
        mass[1] = mass[1].toLowerCase();
        for (int i = 0; i < mass[0].length(); i++)
        {
            if (!Character.isAlphabetic(mass[0].charAt(i)))
            {
                mass[0] = mass[0].replace(String.valueOf(mass[0].charAt(i)), "");
                i--;
            }
        }
        mass[1] = mass[1].replaceAll(" ", "");
        String prov = mass[1], ans = "";
        boolean flag;
        for (int i = 0; i < mass[0].length() - mass[1].length() + 1; i++)
        {
            if (prov.indexOf(mass[0].charAt(i)) != -1)
            {
                int k = 0;
                prov = mass[1];
                if (ans.length() == prov.length())
                    flag = false;
                else
                {
                    flag = true;
                    ans = "";
                }
                while (k < mass[1].length() && flag)
                {
                    if (prov.indexOf(mass[0].charAt(i + k)) != -1)
                    {
                        prov = prov.replaceFirst(String.valueOf(mass[0].charAt(i + k)), "");
                        ans += mass[0].charAt(i + k);
                    }
                    else
                    {
                        flag = false;
                        ans = "";
                    }
                    k++;
                }
            }
        }
        if (ans == "")
            ans = "noutfond";
        return ans;
    }
    static String[] collect(String word, int ch)
    {
        String[] ans = new String[100];
        for (int i = 0; i < ans.length; i++)
            ans[i] = "";
        int sch = 0, sch1 = 0;
        while (word.length() >= ch || sch != 0)
        {
            ans[sch1] += word.charAt(0);
            word = word.substring(1, word.length());
            sch++;
            if (sch == ch)
            {
                sch = 0;
                sch1++;
            }
        }
        boolean flag = true, prov = false;
        int provnum = 0;
        String per;
        while (flag)
        {
            flag = false;
            int i = 0;
            while (ans[i + 1] != "")
            {
                prov = false;
                while (!prov)
                {
                    provnum = 0;
                    if (ans[i].charAt(provnum) > ans[i + 1].charAt(provnum))
                    {
                        per = ans[i];
                        ans[i] = ans[i+1];
                        ans[i+1] = per;
                        prov = true;
                        flag = true;
                    }
                    else if (ans[i].charAt(provnum) < ans[i + 1].charAt(provnum))
                        prov = true;
                    else
                        provnum++;
                    if (provnum > ch)
                        prov = true;
                }
                i++;
            }
        }
        return ans;
    }
    static String nicoCiopher(String word, String key)
    {
        Character max;
        int maxi;
        if (!key.contains("1"))
        {
            for (int i = 0; i < key.length(); i++)
            {
                max = '0';
                maxi = 0;
                for (int j = 0; j < key.length(); j++)
                {
                    if (key.charAt(j) >= max)
                    {
                        max = key.charAt(j);
                        maxi = j;
                    }
                }
                key = key.replace(String.valueOf(max), String.valueOf(key.length() - i));
                while (key.replaceFirst(String.valueOf(key.length() - i), String.valueOf(max)).contains(String.valueOf(key.length() - i)))
                    key = key.replaceFirst(String.valueOf(key.length() - i), String.valueOf(max));
            }
        }
        String ans = "";
        while (word.length() > 0)
        {
            for (int j = 1; j < key.length() + 1; j++)
            {
                if (key.indexOf(String.valueOf(j)) < word.length())
                    ans += word.charAt(key.indexOf(String.valueOf(j)));
                else
                    ans += ' ';
            }
            if (word.length() > key.length())
                word = word.substring(key.length());
            else
                word = "";
        }
        return ans;
    }
    static int[] twoProduct(int[] mass, int ch)
    {
        int[] ans = {-10000, -10000};
        for (int i = 1; i < mass.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (mass[j] * mass[i] == ch && ans[0] == -10000)
                {
                    ans[0] = mass[j];
                    ans[1] = mass[i];
                }
            }
        }
        int[] altans = {};
        if (ans[0] != -10000)
            return ans;
        else
            return altans;
    }
    static int[] isExact(int ch)
    {
        int i = 1, p = 1;
        while (i < ch)
        {
            i *= ++p;
        }
        if (i == ch)
        {
            int[] ans = {i, p};
            return ans;
        }
        else
        {
            int[] ans = {};
            return ans;
        }
    }
    static String fractions(String num)
    {
        String part = num.substring(num.indexOf('(') + 1, num.indexOf(')'));
        double num2 = Double.valueOf(num.substring(0, num.indexOf('(')));
        double num1 = Double.valueOf(num.replace("(","").replace(")", "")) * Math.pow(10, part.length()) ;
        num1 -= num2;
        String mnozh = "";
        for (int i = 0; i < part.length(); i++)
            mnozh += '9';
        while (num1 % 1 != 0)
        {
            num1 *= 10;
            mnozh += '0';
        }
        int niz = Integer.parseInt(mnozh);
        int verh = (int)num1;
        int max = 1;
        for (int i = 1; i <= verh / 2 && i <= niz / 2; i++)
        {
            if (verh % i == 0 && niz % i == 0)
                max = i;
        }
        verh /= max;
        niz /= max;
        return String.valueOf(verh) + "/" + String.valueOf(niz);
    }
    static String pilish_string(String word)
    {
        if (word == "")
            return "";
        int[] pi = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9};
        int sch = 0;
        int i = pi[sch++];
        while (sch < 15 && i < word.length())
        {
            word = word.substring(0, i) + " " + word.substring(i);
            i += pi[sch++] + 1;
        }
        if (sch == 15)
            word = word.substring(0,i);
        while (word.length() < i)
            word = word + word.charAt(word.length() - 1);
        return word;
    }
    static String generateNoconsecutive(int num)
    {
        String zeros = "0000000000000000000000";
        int ch = 0B0;
        String ans = zeros.substring(0, num - Integer.toBinaryString(ch).length()) + Integer.toBinaryString(ch);
        for (int i = 0; i < Math.pow(2, num) - 1; i++)
        {
            ch += 0B1;
            if (Integer.toBinaryString(ch).indexOf("11") == -1)
                ans += " " + zeros.substring(0, num - Integer.toBinaryString(ch).length()) + Integer.toBinaryString(ch);
        }
        return ans;
    }
    static String isValid(String str)
    {
        String charac;
        int num = 1, vop1 = 0, vop2 = 0, norm = 0, iskl = 0;

        charac = String.valueOf(str.charAt(0));
        for (int i = 1; i < str.length(); i++)
        {
            if (String.valueOf(str.charAt(i)).equals(charac))
                num++;
        }
        str = str.replaceAll(charac, "");
        vop1 = num;
        num = 1;

        charac = String.valueOf(str.charAt(0));
        for (int i = 1; i < str.length(); i++)
        {
            if (String.valueOf(str.charAt(i)).equals(charac))
                num++;
        }
        str = str.replaceAll(charac, "");
        if (num == vop1)
            norm = vop1;
        else if (Math.abs(num - vop1) != 1)
            return "NO";
        else
            vop2 = num;
        num = 1;

        charac = String.valueOf(str.charAt(0));
        for (int i = 1; i < str.length(); i++)
        {
            if (String.valueOf(str.charAt(i)).equals(charac))
            num++;
        }
        str = str.replaceAll(charac, "");
        if (num == norm);
        else if (norm != 0 && num - norm == 1)
            iskl = num;
        else if (norm != 0)
            return "NO";
        else if (num == vop1 && vop2 - vop1 == 1)
        {
            norm = vop1;
            iskl = vop2;
        }
        else if (num == vop2 && vop1 - vop2 == 1)
        {
            norm = vop2;
            iskl = vop1;
        }
        else
            return "NO";
        num = 1;

        while (str.length() > 0)
        {
            charac = String.valueOf(str.charAt(0));;
            for (int i = 1; i < str.length(); i++)
            {
                if (String.valueOf(str.charAt(i)).equals(charac))
                    num++;
            }
            str = str.replaceAll(charac, "");
            if (num == norm);
            else if (num - norm == 1 && iskl == 0)
                iskl = num;
            else
                return "NO";
            num = 1;
        }
        return "YES";
    }
    static int[][] sumsUp(int[] mass)
    {
        int[][] ans = new int[100][2];
        int ch = 0;
        for (int i = 1; i < mass.length; i++)
        {
            for (int j = 0; j < mass.length - i; j++)
            {
                if (mass[j] + mass[j + i] == 8)
                {
                    if (mass[j] > mass[j+i])
                    {
                        ans[ch][0] = mass[j + i];
                        ans[ch][1] = mass[j];
                    }
                    else
                    {
                        ans[ch][0] = mass[j];
                        ans[ch][1] = mass[j + i];
                    }
                    ch++;
                }
            }
        }
        return ans;
    }
}