package hu.bosch.bomple.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClasspathReader {
    protected final String getContent(String path, Charset charset) {
        InputStream inputStream = ClasspathReader.class.getClassLoader().getResourceAsStream(path);

        if ( inputStream == null ) {
            throw new IllegalArgumentException("Can't find file with path: '" + path + "'.");
        }

        try {
            return new String(inputStream.readAllBytes(), charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected final String getContent(String path) {
        return getContent(path, StandardCharsets.UTF_8);
    }
}
