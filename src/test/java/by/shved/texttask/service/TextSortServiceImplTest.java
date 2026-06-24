package by.shved.texttask.service;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.parser.TextFacadeParser;
import by.shved.texttask.service.impl.TextSortServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextSortServiceImplTest {
    private final TextSortService service = new TextSortServiceImpl();
    private final TextFacadeParser parser = new TextFacadeParser();

    @BeforeEach
    void setUp() {
    }

    @Test
    void sortSentencesByLetterShouldSortAscending() {
        // given
        String source = """
                aaa.
                a.
                aa.
                """;
        TextNode text = parser.parse(source);
        // when
        List<TextNode> actual = service.sortSentencesByLetter(text, 'a');
        // then
        assertAll(
                () -> assertEquals("a.", actual.get(0).restoreText()),
                () -> assertEquals("aa.", actual.get(1).restoreText()),
                () -> assertEquals("aaa.", actual.get(2).restoreText())
        );
    }

    @AfterEach
    void tearDown() {
    }
}