package by.shved.texttask.entity;

import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.entity.impl.TextLeaf;
import by.shved.texttask.type.TextNodeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addShouldAddChild() {
        // given
        int expectedSize = 1;
        TextComposite composite = new TextComposite(TextNodeType.LEXEME);
        TextLeaf leaf = new TextLeaf('A');
        // when
        composite.add(leaf);
        List<TextNode> actual = composite.getChildren();
        // then
        assertAll(
                () -> assertEquals(expectedSize, actual.size()),
                () -> assertEquals(leaf, actual.getFirst())
        );
    }

    @Test
    void removeShouldRemoveChild() {
        // given
        TextComposite composite = new TextComposite(TextNodeType.LEXEME);
        TextLeaf leaf = new TextLeaf('A');
        composite.add(leaf);
        // when
        composite.remove(leaf);
        List<TextNode> actual = composite.getChildren();
        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    void restoreTextShouldRestoreChildrenText() {
        // given
        String expected = "Hi";
        TextComposite composite = new TextComposite(TextNodeType.LEXEME);
        composite.add(new TextLeaf('H'));
        composite.add(new TextLeaf('i'));
        // when
        String actual = composite.restoreText();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void getTypeShouldReturnSpecifiedType() {
        // given
        TextNodeType expected = TextNodeType.SENTENCE;
        TextComposite composite = new TextComposite(TextNodeType.SENTENCE);
        // when
        TextNodeType actual = composite.getType();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void getChildrenShouldReturnImmutableList() {
        // given
        TextComposite composite = new TextComposite(TextNodeType.LEXEME);
        composite.add(new TextLeaf('A'));
        List<TextNode> actual = composite.getChildren();
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                () -> actual.add(new TextLeaf('B'))
        );
    }

    @AfterEach
    void tearDown() {
    }
}