package by.shved.texttask.service;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.parser.TextFacadeParser;
import by.shved.texttask.service.impl.TextTransformServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformServiceImplTest {
    private final TextTransformService service = new TextTransformServiceImpl();
    private final TextFacadeParser parser = new TextFacadeParser();

    @BeforeEach
    void setUp() {
    }

    @Test
    void swapFirstAndLastLexemeShouldModifySentence() {
        // given
        String source = "Hello beautiful world.";
        TextNode text = parser.parse(source);
        // when
        service.swapFirstAndLastLexeme(text);
        String actual = text.restoreText();
        // then
        assertNotEquals(source, actual);
    }

    @AfterEach
    void tearDown() {
    }
}