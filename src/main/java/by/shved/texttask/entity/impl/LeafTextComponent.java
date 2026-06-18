package by.shved.texttask.entity.impl;

import by.shved.texttask.entity.TextComponent;
import by.shved.texttask.type.TextComponentType;

import java.util.StringJoiner;

public class LeafTextComponent implements TextComponent {
    private final Character symbol;

    public LeafTextComponent(Character symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent component)  {
        throw new UnsupportedOperationException("Leaf component does not support add operation");
    }

    @Override
    public TextComponentType getType() {
        return TextComponentType.SYMBOL;
    }

    @Override
    public String restoreText() {
        return symbol.toString();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LeafTextComponent.class.getSimpleName() + "[", "]")
                .add("symbol=" + symbol)
                .toString();
    }
}
