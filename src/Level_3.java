import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Level_3 {
    private static final HashMap<Integer, int[]> bestSolution = new HashMap<>();
    private static final boolean[] printed = new boolean[100];

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/input.txt"));

        solve(input);
    }

    private static void solve(List<String> input) throws IOException {
        for (int i = 2; i < input.size(); i++) {
            String[] coins = input.get(i).split(" ");
            List<Integer> values = new java.util.ArrayList<>(Arrays.stream(coins).mapToInt(Integer::parseInt).boxed().toList());
            Collections.reverse(values);

            for (int j = 1; j < 100; j++) {
                int[] history = new int[values.size()];
                coinSums(j, 0, values, history, j);
            }

        }
    }

    public static void coinSums(int amount, int position, List<Integer> coins, int[] history, int sum) throws IOException {
        if (amount == 0) {

            try (BufferedWriter out = Files.newBufferedWriter(Path.of("src/output.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                if (!printed[sum]) {
                    for (int k = 0; k < history.length; k++) {
                        if (history[k] != 0) {
                            out.write(history[k] + "x" + coins.get(k) + " ");
                        }
                    }

                    out.write(System.lineSeparator());
                    printed[sum] = true;
                }
            }

        }

        for (int i = position; i < coins.size(); i++) {
            if (amount - coins.get(i) >= 0) {
                history[i] = history[i] + 1;
                coinSums(amount - coins.get(i), i, coins, history, sum);
            }
        }

    }
}