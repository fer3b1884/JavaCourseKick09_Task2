package by.shved.texttask.parser;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.TextNodeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        TextNode paragraph = actual.getChildren().getFirst();
        TextNode sentence = paragraph.getChildren().getFirst();
        TextNode firstLexeme = sentence.getChildren().getFirst();
        TextNode firstWord = firstLexeme.getChildren().getFirst();
        TextNode firstSymbol = firstWord.getChildren().getFirst();
        // then
        assertAll(
                () -> assertEquals(TextNodeType.TEXT, actual.getType()),
                () -> assertEquals(TextNodeType.PARAGRAPH, paragraph.getType()),
                () -> assertEquals(TextNodeType.SENTENCE, sentence.getType()),
                () -> assertEquals(TextNodeType.LEXEME, firstLexeme.getType()),
                () -> assertEquals(TextNodeType.WORD, firstWord.getType()),
                () -> assertEquals(TextNodeType.SYMBOL, firstSymbol.getType())
        );
    }

    @Test
    void parseShouldSeparateWordAndPunctuation() {
        // given
        String source = "Hello.";
        // when
        TextNode text = parser.parse(source);
        TextNode lexeme = text.getChildren()
                .getFirst()
                .getChildren()
                .getFirst()
                .getChildren()
                .getFirst();

        List<TextNode> actual = lexeme.getChildren();
        // then
        assertAll(
                () -> assertEquals(2, actual.size()),
                () -> assertEquals(TextNodeType.WORD, actual.get(0).getType()),
                () -> assertEquals(TextNodeType.PUNCTUATION, actual.get(1).getType())
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
        int actual = sentence.getChildren().size();
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