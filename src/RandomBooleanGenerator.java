import java.util.Random;

public class RandomBooleanGenerator {

    public static boolean generateRandomBoolean(int likelihood) {
        if (likelihood < 0 || likelihood > 100) {
            throw new IllegalArgumentException("Likelihood must be between 0 and 100 (inclusive)");
        }

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100

        return randomNumber <= likelihood;
    }
    public static int boss_selector() {
        // Create a Random object
        Random random = new Random();

        // Generate a random number between 1 and 3 (inclusive)
        int randomNumber = random.nextInt(3) + 1;

        return randomNumber;
    }

    public static void main(String[] args) {
        // Example usage
        int likelihood = 10; // Adjust the likelihood as needed
        boolean result = generateRandomBoolean(likelihood);

        System.out.println("Likelihood: " + likelihood);
        System.out.println("Result: " + result);
    }
}
