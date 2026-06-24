package by.shved.texttask.service;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.parser.TextFacadeParser;
import by.shved.texttask.service.impl.TextStatisticsServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextStatisticsServiceImplTest {
    private final TextStatisticsService service = new TextStatisticsServiceImpl();
    private final TextFacadeParser parser = new TextFacadeParser();

    @BeforeEach
    void setUp() {
    }

    @Test
    void findMaxSentencesWithSameWordsShouldReturnCorrectValue() {
        // given
        int expected = 2;
        String source = """
                Hello world.
                Hello java.
                Java code.
                """;
        TextNode text = parser.parse(source);
        // when
        int actual = service.findMaxSentencesWithSameWords(text);
        // then
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
    }
}