// Основной класс. Цель: нахождение простых чисел до 100
public class Primes {
 // Основной метод. Перебирает числа от 2 до 100 и выводит простые
 public static void main(String[] args) {
  for (int i = 2; i < 100; i++){
   if(isPrime(i))
    System.out.println(i);
  }
 }
 // Проверяет полученное число на простоту
 public static boolean isPrime(int n)
 {
  for (int i = 2; i < n-1; i++) {
   if (n % i == 0)
    return false;
  }
  return true;
 }
} 