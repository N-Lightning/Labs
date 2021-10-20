public class Tasks1 {
    int convert(int min)
    {
        return min * 60;
    }
    int points(int twos, int threes)
    {
        return twos * 2 + threes * 3;
    }
    int footballPoints(int wins, int draws, int looses)
    {
        return wins * 3 + draws * 1;
    }
    boolean divisibleByFive(int num)
    {
        return num % 5 == 0;
    }
    boolean and(boolean a, boolean b)
    {
        return a && b;
    }
    int howManyWalls(int n, int w, int h)
    {
        return n / (w * h);
    }
    public static int squared(int a) {
        return a * a;
    }
    boolean profitableGolem(double prob, int prize, int pay)
    {
        return prob * prize > pay;
    }
    int frames(int a, int b)
    {
        return a * b * 60;
    }
    int mod(int a, int b)
    {
        while (a > b)
            a -= b;
        return a;
    }
}