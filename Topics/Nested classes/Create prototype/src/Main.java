
public class Main {
    public static void main(String[] args) {
        IPhone154.PrototypeIPhone154 prototypeIPhone154 = new IPhone154.PrototypeIPhone154();
        prototypeIPhone154.printMinCost();
    }
}

class IPhone154 {
    static double minCost = initCost();

    static class PrototypeIPhone154 {
        void printMinCost() {
            System.out.println("The min cost of IPhone154 should be: $" + minCost);
        }
    }

    private static double initCost() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextDouble();
    }
}