import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Level_1 {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/input.txt"));

        solve(input);
    }

    private static void solve(List<String> input) {
        input.stream().skip(3).forEach(line -> {
            String[] coins = line.split(" ");
            List<Integer> values = Arrays.stream(coins).mapToInt(Integer::parseInt).boxed().toList();


            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                if (!values.contains(i)) {
                    try (BufferedWriter out = Files.newBufferedWriter(Path.of("src/output.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                        out.write(i + System.lineSeparator());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                }
            }
        });
    }
}
