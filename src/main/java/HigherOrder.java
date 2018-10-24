import java.util.function.BiFunction;
import java.util.function.Function;

public class HigherOrder {

    @FunctionalInterface
    interface TriFunction<A,B,C,R> {

        R apply(A a, B b, C c);
    }

    public static void main(String[] args){
        System.out.println("sum of numbers - " + sumOfInt(1,6));
        System.out.println("sum of Square of numbers - " + sumOfSqrInt(1,6));
        System.out.println("sum of Cube of numbers - " + sumOfCubInt(1,6));
        System.out.println("sum of Factorial of numbers - " + sumOfFactInt(1,6));

        System.out.println("Functional sum of numbers - " + sum(x -> x, 1,6));
        System.out.println("Functional sum of square of numbers - " + sum(x -> x * x, 1,6));
        System.out.println("Functional sum of Cube of numbers - " + sum(x -> x * x * x, 1,6));

//        Function<Integer, Integer> factError =
//                x -> x == 0 ? 1 : x * factError.apply(x-1);

        BiFunction<BiFunction, Integer, Integer> factHelper =
                (f, x) -> (x == 0 ? 1 : x * (Integer) f.apply(f, x-1));

        Function<Integer, Integer> fact = x -> factHelper.apply(factHelper, x);

        TriFunction<TriFunction, Integer, Integer, Integer> factTailRec =
                (f, x, y) -> (x == 1 ? y :  (Integer) f.apply(f, x-1, y * x));

        Function<Integer, Integer> factTail = x -> factTailRec.apply(factTailRec, x, 1);

        System.out.println("Functional sum of Factorial of numbers - " + sum(fact, 1,6));
        System.out.println("Functional sum of Factorial of numbers tail rec - " + sum(factTail, 1,6));
    }

    private static int sumOfInt(int lb, int ub){
        if(lb > ub) return 0;
        else return lb + sumOfInt(lb + 1, ub);
    }

    private static int sumOfSqrInt(int lb, int ub){
        if(lb > ub) return 0;
        else return (lb * lb) + sumOfSqrInt(lb + 1, ub);
    }

    private static int sumOfCubInt(int lb, int ub){
        if(lb > ub) return 0;
        else return (lb * lb * lb) + sumOfCubInt(lb + 1, ub);
    }

    private static int sumOfFactInt(int lb, int ub) {
        if(lb > ub) return 0;
        else return factorial(lb) + sumOfFactInt(lb + 1, ub);
    }

    private static int factorial(int num){
        if(num == 1) return 1;
        else return num * factorial(num - 1);
    }

    /**
     * Functional way of writing the Sum method
     * @param f
     * @param lb
     * @param ub
     * @return
     */
    private static int sum(Function<Integer, Integer> f, int lb, int ub){
        if(lb > ub) return 0;
        else return f.apply(lb) + sum(f, lb + 1, ub);
    }


}
