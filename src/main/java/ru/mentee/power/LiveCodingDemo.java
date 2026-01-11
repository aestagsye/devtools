package ru.mentee.power;

public class LiveCodingDemo {
  public static void main(String[] args) {
    printFizzBuzz(15);
    System.out.println();
    sumEven(new int[]{1,2,3,4,5,6});
    System.out.println();
    findMax(new int[]{1,2,3,4,5,6});
    System.out.println();
    findMax(new int[]{});
  }

  public static void printFizzBuzz(int n) {
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        System.out.println("FizzBuzz");
      } else if (i % 3 == 0) {
        System.out.println("Fizz");
      } else if (i % 5 == 0) {
        System.out.println("Buzz");
      } else {
        System.out.println(i);
      }
    }
  }
  public static void  sumEven(int[] numbers){
    int sum = 0;
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i]%2==0){
        sum+=numbers[i];
      }
    }
    System.out.println(sum);
  }
  public static void findMax(int[] numbers){
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i]>=max){
        max=numbers[i];
      }
    }
    System.out.println(max);
  }
}
