import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Level_2 {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/input.txt"));

        solve(input);
    }

    private static void solve(List<String> input) {
        for (int i = 3; i < input.size() - 1; i += 2) {
            String[] coins = input.get(i).split(" ");
            List<Integer> values = Arrays.stream(coins).mapToInt(Integer::parseInt).boxed().toList();
            System.out.println("values = " + values);

            String[] money = input.get(i + 1).split(" ");
            List<Integer> withdrawals = Arrays.stream(money).mapToInt(Integer::parseInt).boxed().toList();
            System.out.println("withdrawals = " + withdrawals);

            for (int withdrawal : withdrawals) {
                boolean breakOuter = false;
                for (int j = 0; j < values.size(); j++) {
                    for (int k = j; k < values.size(); k++) {
                        if (values.get(j) + values.get(k) == withdrawal) {
                            System.out.println("values.get(j) = " + values.get(j));
                            System.out.println("values.get(k) = " + values.get(k));
                            System.out.println("withdrawal = " + withdrawal);
                            try (BufferedWriter out = Files.newBufferedWriter(Path.of("src/output.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                                out.write(values.get(j) + " " + values.get(k) + System.lineSeparator());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            breakOuter = true;
                            break;
                        }
                    }
                    if (breakOuter) {
                        breakOuter = false;
                        break;
                    }
                }
            }
        }
    }
}
