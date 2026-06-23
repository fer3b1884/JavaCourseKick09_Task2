package by.shved.texttask.entity.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.type.TextNodeType;

import java.util.List;
import java.util.StringJoiner;

public class TextLeaf implements TextNode {
    private final Character symbol;

    public TextLeaf(Character symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextNode component)  {
        throw new UnsupportedOperationException("Leaf component does not support add operation");
    }

    @Override
    public void remove(TextNode component) {
        throw new UnsupportedOperationException("Leaf component does not support remove operation");
    }

    @Override
    public List<TextNode> getChildren() {
        throw new UnsupportedOperationException("Leaf component does not support remove operation");
    }

    @Override
    public TextNodeType getType() {
        return TextNodeType.SYMBOL;
    }

    @Override
    public String restoreText() {
        return symbol.toString();
    }

    @Override
    public int countSymbols() {
        return 1;
    }

    @Override
    public int countLetters() {
        return Character.isLetter(symbol) ? 1 : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextLeaf.class.getSimpleName() + "[", "]")
                .add("symbol=" + symbol)
                .toString();
    }
}
