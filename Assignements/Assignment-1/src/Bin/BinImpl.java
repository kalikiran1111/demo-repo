package Bin;

import java.util.*;

public class BinImpl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the number of weights : ");
        int numOfWeights = scanner.nextInt();
        if (numOfWeights == 0 || numOfWeights < 0 || numOfWeights > Integer.MAX_VALUE || numOfWeights < Integer.MIN_VALUE) {
            System.out.println("Number of weights cannot be 0, negative, larger than int max or lesser than int max");
            System.exit(0);
        }

        Double[] weights = new Double[numOfWeights];
        System.out.println("Please Enter the weights of the objects :");
        for(int i=0; i < numOfWeights; i++ ) {
            weights[i] = Double.parseDouble(String.valueOf(scanner.nextInt()));
        }
        Arrays.sort(weights, Collections.reverseOrder());
        ArrayList<Bin> containers = new ArrayList<>();

        for (int i = 0; i < weights.length; i++) {
            if (!containers.isEmpty()) {
                for (Bin container : containers) {
                    if (weights[i] != 0.0 && container.addItem(weights[i])) {
                        weights[i] = 0.0;
                    }
                }
                if (weights[i] == 0) continue;
            }
            Bin container = new Bin();
            if (container.addItem(weights[i])){
                weights[i] = 0.0;
            }
            containers.add(container);
        }

        int count = 1;
        for (Bin container : containers) {
            System.out.println("Container "+ count + " "+ container.toString());
            count++;
        }
    }
}
