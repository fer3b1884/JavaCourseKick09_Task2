package by.shved.texttask.parser;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.reader.TextFileReader;
import by.shved.texttask.type.TextNodeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextFacadeParserTest {
    private static final TextFacadeParser parser = new TextFacadeParser();

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseShouldCreateTextTree() {
        // given
        String source = "Hello world.";
        // when
        TextNode actual = parser.parse(source);
        // then
        assertAll(
                () -> assertEquals(TextNodeType.TEXT, actual.getType()),
                () -> assertEquals(
                        TextNodeType.PARAGRAPH,
                        actual.getChildren().getFirst().getType()
                ),
                () -> assertEquals(
                        TextNodeType.SENTENCE,
                        actual.getChildren()
                                .getFirst()
                                .getChildren()
                                .getFirst()
                                .getType()
                )
        );
    }

    @Test
    void parseShouldCreateCorrectNumberOfLexemes() {
        // given
        int expected = 2;
        // when
        TextNode text = parser.parse("Hello world.");
        TextNode sentence = text.getChildren()
                .getFirst()
                .getChildren()
                .getFirst();

        int actual =
                sentence.getChildren().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void parseShouldRestoreOriginalText() {
        // given
        String expected = "Hello world.";
        // when
        TextNode text = parser.parse(expected);
        String actual = text.restoreText();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void parseShouldCreateSeveralParagraphs() {
        // given
        int expected = 2;
        String source = """
                First paragraph.
                Second paragraph.
                """;
        // when
        TextNode text = parser.parse(source);
        int actual = text.getChildren().size();
        // then
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
    }
}