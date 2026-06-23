package by.shved.texttask.entity;

import by.shved.texttask.entity.impl.SymbolLeaf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SymbolLeafTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'b', '1', '.'})
    void getTypeShouldReturnSymbol(char symbol) {
        // given
        TextNodeType expected = TextNodeType.SYMBOL;
        SymbolLeaf leaf = new SymbolLeaf(symbol);
        // when
        TextNodeType actual = leaf.getType();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void restoreTextShouldReturnSymbolAsString() {
        // given
        String expected = "A";
        SymbolLeaf leaf = new SymbolLeaf('A');
        // when
        String actual = leaf.restoreText();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void addShouldThrowException() {
        // given
        SymbolLeaf leaf = new SymbolLeaf('A');
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                () -> leaf.add(new SymbolLeaf('B'))
        );
    }

    @Test
    void removeShouldThrowException() {
        // given
        SymbolLeaf leaf = new SymbolLeaf('A');
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                () -> leaf.remove(new SymbolLeaf('B'))
        );
    }

    @Test
    void getChildrenShouldThrowException() {
        // given
        SymbolLeaf leaf = new SymbolLeaf('A');
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