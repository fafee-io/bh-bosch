package hu.bosch.bomple.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReader {

    public final void fileozas() throws IOException {
        Path path = Path.of("/foo/bar").resolve("alma.txt");
        String content = "ezt kéne beleírni";
        Files.writeString(path, content);

        Files.move(Path.of("innen"), Path.of("ide"));
        File file = path.toFile();

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception ex) {
            throw new IOException("Some error happened");
        }
    }
}
