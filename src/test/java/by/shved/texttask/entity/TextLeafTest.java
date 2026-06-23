package by.shved.texttask.entity;

import by.shved.texttask.entity.impl.TextLeaf;
import by.shved.texttask.type.TextNodeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TextLeafTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'b', '1', '.'})
    void getTypeShouldReturnSymbol(char symbol) {
        // given
        TextNodeType expected = TextNodeType.SYMBOL;
        TextLeaf leaf = new TextLeaf(symbol);
        // when
        TextNodeType actual = leaf.getType();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void restoreTextShouldReturnSymbolAsString() {
        // given
        String expected = "A";
        TextLeaf leaf = new TextLeaf('A');
        // when
        String actual = leaf.restoreText();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void addShouldThrowException() {
        // given
        TextLeaf leaf = new TextLeaf('A');
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                () -> leaf.add(new TextLeaf('B'))
        );
    }

    @Test
    void removeShouldThrowException() {
        // given
        TextLeaf leaf = new TextLeaf('A');
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                () -> leaf.remove(new TextLeaf('B'))
        );
    }

    @Test
    void getChildrenShouldThrowException() {
        // given
        TextLeaf leaf = new TextLeaf('A');
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                leaf::getChildren
        );
    }

    @AfterEach
    void tearDown() {
    }
}