package by.shved.texttask.entity.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.TextNodeType;

import java.util.List;
import java.util.StringJoiner;

public class SymbolLeaf implements TextNode {
    private final Character symbol;

    public SymbolLeaf(Character symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean add(TextNode component)  {
        throw new UnsupportedOperationException("Leaf component does not support add operation");
    }

    @Override
    public boolean remove(TextNode component) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SymbolLeaf that = (SymbolLeaf) o;
        return symbol.equals(that.symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SymbolLeaf.class.getSimpleName() + "[", "]")
                .add("symbol=" + symbol)
                .toString();
    }
}
