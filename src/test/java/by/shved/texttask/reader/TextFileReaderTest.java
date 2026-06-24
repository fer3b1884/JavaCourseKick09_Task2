package by.shved.texttask.reader;

import by.shved.texttask.exception.TextTaskException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFileReaderTest {
    private static final String VALID_PATH = "data/text.txt";
    private static final String INVALID_PATH = "data/text_not_exists.txt";
    private static final TextFileReader reader = new TextFileReader();

    @BeforeEach
    void setUp() {
    }

    @Test
    void readFileDataShouldReturnAllLinesFromExistingFile() throws TextTaskException {
        // when
        String actual = reader.readFileData(VALID_PATH);
        // then
        assertFalse(actual.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            INVALID_PATH
    })
    void readFileDataShouldThrowExceptionForInvalidPath(String path) {
        // when + then
        assertThrows(TextTaskException.class, () -> reader.readFileData(path));
    }

    @AfterEach
    void tearDown() {
    }
}