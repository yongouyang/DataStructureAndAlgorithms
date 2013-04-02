package fun;

public class Fib {

    public static void main(String[] args) {
        recursively(1);
        whileLoop(1);

        long start = System.nanoTime();

        long result = recursively(40);
        long elapsed = (System.nanoTime() - start) / 1000000;
        System.out.println(String.format("Working out Fibonacci(%s)=%s recursively took %s ms", 40, result, elapsed));

        start = System.nanoTime();
        result = whileLoop(40);
        elapsed = (System.nanoTime() - start);
        System.out.println(String.format("Working out Fibonacci(%s)=%s with while loop took %s ns", 40, result, elapsed));

        start = System.nanoTime();
        result = whileLoop(50);
        elapsed = (System.nanoTime() - start);
        System.out.println(String.format("Working out Fibonacci(%s)=%s with while loop took %s ns", 50, result, elapsed));

        start = System.nanoTime();
        result = whileLoop(60);
        elapsed = (System.nanoTime() - start);
        System.out.println(String.format("Working out Fibonacci(%s)=%s with while loop took %s ns", 60, result, elapsed));
    }

    static long recursively(long i) {
        if (i == 0) return 0;
        if (i <= 2) return 1;
        else return recursively(i - 1) + recursively(i - 2);
    }

    static long whileLoop(long i) {
        long previous = 0, fib = 1, currentIndex = 1;
        while (currentIndex < i) {
            long newFib = previous + fib;
            previous = fib;
            fib = newFib;
            currentIndex++;
        }
        return fib;
    }
}
