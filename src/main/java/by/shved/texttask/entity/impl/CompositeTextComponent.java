package by.shved.texttask.entity.impl;

import by.shved.texttask.entity.TextComponent;
import by.shved.texttask.type.TextComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CompositeTextComponent implements TextComponent {
    private final TextComponentType type;
    private final List<TextComponent> children = new ArrayList<>();

    public CompositeTextComponent(TextComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String restoreText() {
        StringBuilder builder = new StringBuilder();

        for (TextComponent child : children) {
            builder.append(child.restoreText());
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CompositeTextComponent.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("children=" + children)
                .toString();
    }
}
