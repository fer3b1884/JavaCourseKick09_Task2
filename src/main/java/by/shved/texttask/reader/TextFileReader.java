package by.shved.texttask.reader;

import by.shved.texttask.exception.TextTaskException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TextFileReader {
    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    public String readFileData(String filePathString) throws TextTaskException {
        if (filePathString == null || filePathString.isBlank()) {
            logger.error("File path is null or blank");
            throw new TextTaskException("File path cannot be null or blank");
        }
        try {
            Path path = Paths.get(filePathString);
            if (!Files.exists(path)) {
                logger.error("File does not exist: {}", filePathString);
                throw new TextTaskException("Target file does not exist: " + filePathString);
            }
            String text = Files.readString(path);
            logger.info("Successfully read text from file {}", filePathString);
            return text;
        } catch (IOException | InvalidPathException e) {
            logger.error("Error during reading file {}", filePathString, e);
            throw new TextTaskException("Error during reading file process", e);
        }
    }
}
