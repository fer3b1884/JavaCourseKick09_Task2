package by.shved.texttask.entity;

import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.entity.impl.SymbolLeaf;
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
        SymbolLeaf leaf = new SymbolLeaf('A');
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
        SymbolLeaf leaf = new SymbolLeaf('A');
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
        composite.add(new SymbolLeaf('H'));
        composite.add(new SymbolLeaf('i'));
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
        composite.add(new SymbolLeaf('A'));
        List<TextNode> actual = composite.getChildren();
        // when + then
        assertThrows(
                UnsupportedOperationException.class,
                () -> actual.add(new SymbolLeaf('B'))
        );
    }

    @Test
    void countSymbolsShouldReturnTotalNumberOfSymbols() {
        // given
        int expected = 3;
        TextComposite composite = new TextComposite(TextNodeType.LEXEME);
        composite.add(new SymbolLeaf('A'));
        composite.add(new SymbolLeaf('B'));
        composite.add(new SymbolLeaf('.'));
        // when
        int actual = composite.countSymbols();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void countLettersShouldReturnOnlyLettersCount() {
        // given
        int expected = 2;
        TextComposite composite = new TextComposite(TextNodeType.LEXEME);
        composite.add(new SymbolLeaf('A'));
        composite.add(new SymbolLeaf('B'));
        composite.add(new SymbolLeaf('.'));
        composite.add(new SymbolLeaf('1'));
        // when
        int actual = composite.countLetters();
        // then
        assertEquals(expected, actual);
    }

    @Test
    void restoreTextShouldRestoreNestedStructure() {
        // given
        String expected = "Hello world.";
        TextComposite sentence = new TextComposite(TextNodeType.SENTENCE);
        TextComposite firstLexeme = new TextComposite(TextNodeType.LEXEME);
        TextComposite firstWord = new TextComposite(TextNodeType.WORD);
        firstWord.add(new SymbolLeaf('H'));
        firstWord.add(new SymbolLeaf('e'));
        firstWord.add(new SymbolLeaf('l'));
        firstWord.add(new SymbolLeaf('l'));
        firstWord.add(new SymbolLeaf('o'));
        firstLexeme.add(firstWord);
        TextComposite secondLexeme = new TextComposite(TextNodeType.LEXEME);
        TextComposite secondWord = new TextComposite(TextNodeType.WORD);
        secondWord.add(new SymbolLeaf('w'));
        secondWord.add(new SymbolLeaf('o'));
        secondWord.add(new SymbolLeaf('r'));
        secondWord.add(new SymbolLeaf('l'));
        secondWord.add(new SymbolLeaf('d'));
        TextComposite punctuation = new TextComposite(TextNodeType.PUNCTUATION);
        punctuation.add(new SymbolLeaf('.'));
        secondLexeme.add(secondWord);
        secondLexeme.add(punctuation);
        sentence.add(firstLexeme);
        sentence.add(secondLexeme);
        // when
        String actual = sentence.restoreText();
        // then
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
    }
}