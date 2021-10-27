public class Tasks5
{
    static boolean sameLetterPattern(String str1, String str2)
    {
        String characters = "qwertyuiopasdfghjklzxcvbnm", a = "", b = "";
        int mest = 0;
        if (str1.length() != str2.length())
            return false;
        else
        {
            for (int i = 0; i < str1.length(); i++)
            {
                if (str1.indexOf(str1.charAt(i)) < i)
                    a += a.charAt(str1.indexOf(str1.charAt(i)));
                else
                    a += characters.charAt(mest++);
            }
            mest = 0;
            for (int i = 0; i < str2.length(); i++)
            {
                if (str2.indexOf(str2.charAt(i)) < i)
                    b += b.charAt(str2.indexOf(str2.charAt(i)));
                else
                    b += characters.charAt(mest++);
            }
            if (a.equals(b))
                return true;
            else
                return false;
        }
    }
    static String spiderVsFly(String pos1, String pos2)
    {
        String ans = pos1 + "-";
        Character pos1x = pos1.charAt(0), pos2x = pos2.charAt(0);
        int pos1y = Character.getNumericValue(pos1.charAt(1)), pos2y = Character.getNumericValue(pos2.charAt(1));
        if (Math.abs(pos1x - pos2x) <= 2 || pos1x == 'G' && pos2x == 'A' || pos1x == 'H' && (pos2x == 'A' || pos2x == 'B') || pos1x == 'A' && (pos2x == 'G' || pos2x == 'H') || pos1x == 'B' && pos2x == 'H')
        {
            while (pos1y > pos2y)
                ans += Character.toString(pos1x) + --pos1y + "-";
            while (pos2y > pos1y)
                ans += Character.toString(pos1x) + ++pos1y + "-";
        }
        while (pos1x != pos2x && !(pos1x == 'A' && pos1y == 0) && !(pos2x == 'A' && pos2y == 0))
        {
            if (pos1x - pos2x < 3 && pos1x - pos2x > 0)
                ans += Character.toString(--pos1x) + pos1y + "-";
            else if (pos1x - pos2x > -3 && pos1x - pos2x < 0)
                ans += Character.toString(++pos1x) + pos1y + "-";
            else if (pos1x == 'G' && pos2x == 'A')
                ans += ++pos1x + pos1y + "-";
            else if (pos1x == 'H' && (pos2x == 'A' || pos2x == 'B'))
            {
                pos1x = 'A';
                ans += Character.toString(pos1x) + pos1y + '-';
            }
            else if (pos1x == 'A' && (pos2x == 'G' || pos2x == 'H'))
            {
                pos1x = 'H';
                ans += Character.toString(pos1x) + pos1y + "-";
            }
            else if (pos1x == 'B' && pos2x == 'H')
                ans += Character.toString(--pos1x) + pos1y + "-";
            else if (--pos1y == 0)
            {
                pos1x = 'A';
                ans += Character.toString(pos1x) + pos1y + "-";
            }
            else
                ans += Character.toString(pos1x) + pos1y + "-";
        }
        while (pos1y > pos2y)
            ans += Character.toString(pos2x) + --pos1y + "-";
        while (pos2y > pos1y)
            ans += Character.toString(pos2x) + ++pos1y + "-";
        ans = ans.substring(0, ans.length() - 1);
        return ans;
    }
    static int digitsCount(long ch)
    {
        int sch = 0;
        do
        {
            ch /= 10;
            sch++;
        } while (ch > 0);
        return sch;
    }
    static int totalPoints(String[] words, String word)
    {
        int sch = 0, mest;
        String altWord;
        boolean flag;
        for (int i = 0; i < words.length; i++)
        {
            flag = true;
            altWord = word;
            for (int k = 0; k < words[i].length(); k++)
            {
                if (altWord.contains(String.valueOf(words[i].charAt(k))))
                {
                    mest = altWord.indexOf(words[i].charAt(k));
                    if (mest == 0)
                        altWord = altWord.substring(1, altWord.length());
                    else if (mest == altWord.length())
                        altWord = altWord.substring(0, altWord.length() - 1);
                    else
                        altWord = altWord.substring(0, mest) + altWord.substring(mest + 1, altWord.length());
                }
                else
                    flag = false;
            }
            if (flag)
                sch += words[i].length() - 2;
            if (flag && words[i].length() == 6)
                sch += 50;
        }
        return sch;
    }
    static int longestRun(int[] mass)
    {
        int sch = 1, sch1 = 1, sos = 0;
        for (int i = 1; i < mass.length; i++)
        {
            if (sos == 0)
            {
                if (mass[i] - mass[i - 1] == 1)
                {
                    sch1++;
                    sos = 1;
                }
                else if (mass[i] - mass[i - 1] == -1)
                {
                    sch1++;
                    sos = -1;
                }
            }
            else if (sos == 1)
            {
                if (mass[i] - mass[i - 1] == 1)
                    sch1++;
                else if (mass[i] - mass[i - 1] == -1)
                {
                    if (sch < sch1)
                        sch = sch1;
                    sch1 = 2;
                    sos = -1;
                }
                else
                {
                    if (sch < sch1)
                        sch = sch1;
                    sch1 = 1;
                    sos = 0;
                }
            }
            else
            {
                if (mass[i] - mass[i - 1] == 1)
                {
                    if (sch < sch1)
                        sch = sch1;
                    sch1 = 2;
                    sos = 1;
                }
                else if (mass[i] - mass[i - 1] == -1)
                    sch1++;
                else
                {
                    if (sch < sch1)
                        sch = sch1;
                    sch1 = 1;
                    sos = 0;
                }
            }
        }
        if (sch1 > sch)
            sch = sch1;
        return sch;
    }
    static String takeDownAverage(String[] mass)
    {
        int obsh = 0;
        for (int i = 0; i < mass.length; i++)
            obsh += Integer.parseInt(mass[i].substring(0, 2));
        return String.valueOf((mass.length + 1) * (obsh / mass.length - 5) - obsh) + "%";
    }
    static String rearrange(String exp)
    {
        exp = " " + exp + " ";
        String ans = "";
        int i = 1, pol;
        while (exp.contains(String.valueOf(i)))
        {
            pol = exp.indexOf(String.valueOf(i));
            while (exp.charAt(pol - 1) != ' ')
                pol--;
            while (!Character.isDigit(exp.charAt(pol)))
            {
                ans += exp.charAt(pol);
                pol++;
            }
            pol++;
            while (exp.charAt(pol) != ' ')
            {
                ans += exp.charAt(pol);
                pol++;
            }
            ans += ' ';
            i++;
        }
        if (ans.length() > 0)
            ans = ans.substring(0, ans.length() - 1);
        return ans;
    }
    static int maxPossible(int a, int b)
    {
        String as = Integer.toString(a);
        int[] bs = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int sch = 0, p;
        boolean flag = true;
        while (b > 0)
        {
            bs[sch] = b % 10;
            b /= 10;
            sch++;
        }
        while (flag)
        {
            flag = false;
            for (int i = 0; i < bs.length - 1; i++)
            {
                if (bs[i] < bs[i+1])
                {
                    p = bs[i];
                    bs[i] = bs[i+1];
                    bs[i+1] = p;
                    flag = true;
                }
            }
        }
        for (int i = 0; i < as.length(); i++)
        {
            for (int k = 0; k < bs.length; k++)
            {
                if (bs[k] > Character.getNumericValue(as.charAt(i)))
                {

                    if (i == 0)
                        as = String.valueOf(bs[k]) + as.substring(1);
                    else if (i == as.length())
                        as = as.substring(0, as.length() - 1) + String.valueOf(bs[k]);
                    else
                        as = as.substring(0, i) + String.valueOf(bs[k]) + as.substring(i + 1, as.length());
                    bs[k] = 0;
                }
            }
        }
        return Integer.parseInt(as);
    }
    static String timeDifference(String firstCity, String date, String secondCity)
    {
        int month;
        if (date.contains("January"))
            month = 1;
        else if (date.contains("February"))
            month = 2;
        else if (date.contains("March"))
            month = 3;
        else if (date.contains("April"))
            month = 4;
        else if (date.contains("May"))
            month = 5;
        else if (date.contains("June"))
            month = 6;
        else if (date.contains("July"))
            month = 7;
        else if (date.contains("August"))
            month = 8;
        else if (date.contains("September"))
            month = 9;
        else if (date.contains("October"))
            month = 10;
        else if (date.contains("November"))
            month = 11;
        else
            month = 12;
        int year = Integer.parseInt(date.substring(date.indexOf(", ") + 2, date.indexOf(", ") + 6));
        int day = Integer.parseInt(date.substring(date.indexOf(" ") + 1, date.indexOf(",")));
        int hour = Integer.parseInt(date.substring(date.indexOf(":") - 2, date.indexOf(":")));
        int minute = Integer.parseInt(date.substring(date.indexOf(":") + 1, date.indexOf(":") + 3));
        int firstCityH = 0, firstCityM = 0, secondCityH = 0, secondCityM = 0;
        switch (firstCity)
        {
            case "Los Angeles":
                firstCityH = -8;
                break;
            case "New York":
                firstCityH = -5;
                break;
            case "Caracas":
                firstCityH = -4;
                firstCityM = -30;
                break;
            case "Buenos Aires":
                firstCityH = -3;
                break;
            case "London":
                firstCityH = 0;
                break;
            case "Rome":
                firstCityH = 1;
                break;
            case "Moscow":
                firstCityH = 3;
                break;
            case "Tehran":
                firstCityH = 3;
                firstCityM = 30;
                break;
            case "New Delhi":
                firstCityH = 5;
                firstCityM = 30;
                break;
            case "Beijing":
                firstCityH = 8;
                break;
            case "Canberra":
                firstCityH = 10;
                break;
        }
        switch (secondCity)
        {
            case "Los Angeles":
                secondCityH = -8;
                break;
            case "New York":
                secondCityH = -5;
                break;
            case "Caracas":
                secondCityH = -4;
                secondCityM = -30;
                break;
            case "Buenos Aires":
                secondCityH = -3;
                break;
            case "London":
                secondCityH = 0;
                break;
            case "Rome":
                secondCityH = 1;
                break;
            case "Moscow":
                secondCityH = 3;
                break;
            case "Tehran":
                secondCityH = 3;
                secondCityM = 30;
                break;
            case "New Delhi":
                secondCityH = 5;
                secondCityM = 30;
                break;
            case "Beijing":
                secondCityH = 8;
                break;
            case "Canberra":
                secondCityH = 10;
                break;
        }
        minute += -firstCityM + secondCityM;
        hour += -firstCityH + secondCityH;
        if (minute >= 60)
        {
            minute -= 60;
            hour++;
        }
        if (hour >= 24)
        {
            hour -= 24;
            day++;
        }
        if (day > 31)
        {
            day -= 31;
            month++;
        }
        else if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))
        {
            day -= 30;
            month++;
        }
        else if (day > 29 && month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
        {
            day -= 29;
            month++;
        }
        else if (day > 28 && month == 2 && !(year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
        {
            day -= 28;
            month++;
        }
        if (month > 12)
        {
            month -= 12;
            year++;
        }
        String hourS = String.format("%02d", hour);
        String minuteS = String.format("%02d", minute);
        return String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day) + " " + hourS + ":" + minuteS;
    }
    static boolean isNew(int ch)
    {
        if (ch < 10)
            return true;
        else
        {
            int min = 10;
            while (ch > 0)
            {
                if (ch % 10 != 0 && ch % 10 < min)
                    min = ch % 10;
                else if (ch % 10 > min)
                    return false;
                ch /= 10;
            }
            return true;
        }
    }
}