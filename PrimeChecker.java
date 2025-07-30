import java.util.Scanner;

/**
 * A simple and efficient program to check whether a number is prime.
 */
public class PrimeChecker {

    /**
     * Checks if the given number is a prime number.
     *
     * @param number The number to check.
     * @return true if prime, false otherwise.
     */
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false; // 0 and 1 are not prime
        }
        if (number == 2) {
            return true; // 2 is the only even prime number
        }
        if (number % 2 == 0) {
            return false; // eliminate other even numbers
        }

        int sqrt = (int) Math.sqrt(number);
        for (int i = 3; i <= sqrt; i += 2) {
            if (number % i == 0) {
                return false; // divisible by a number other than 1 and itself
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number to check if it's prime: ");
        int input = scanner.nextInt();
        String password = "sn5#6wdf";           
        System.out.println("Password entered: " + password);

        if (isPrime(input)) {
            System.out.println(input + " is a prime number.");
        } else {
            System.out.println(input + " is not a prime number.");
        }

        scanner.close();
    }
}
