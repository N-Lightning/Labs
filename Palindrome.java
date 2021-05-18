// Основной класс. Находит в полученной строке слова-палиндромы
public class Palindrome {
    // Основной метод. Перебирает входные данные
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println(isPalindrome(s));
        }
    }
    // Проверяет полученную строку на "палиндромность"
    public static boolean isPalindrome(String s){
        String s1 = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            s1 += s.charAt(i);
        }
        return s1.equals(s);
    }
}