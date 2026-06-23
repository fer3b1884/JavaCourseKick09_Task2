package by.shved.texttask.entity.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.type.TextNodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextNode {
    private final TextNodeType type;
    private final List<TextNode> children = new ArrayList<>();

    public TextComposite(TextNodeType type) {
        this.type = type;
    }

    @Override
    public void add(TextNode component) {
        children.add(component);
    }

    @Override
    public void remove(TextNode component) {
        children.remove(component);
    }

    @Override
    public List<TextNode> getChildren() {
        return List.copyOf(children);
    }

    @Override
    public TextNodeType getType() {
        return type;
    }

    @Override
    public String restoreText() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < children.size(); i++) {
            builder.append(children.get(i).restoreText());
            if (i < children.size() - 1) {  // last element without delimiter
                builder.append(type.getDelimiter());
            }
        }
        return builder.toString();
    }

    @Override
    public int countSymbols() {
        int count = 0;
        for (TextNode child : children) {
            count += child.countSymbols();
        }
        return count;
    }

    @Override
    public int countLetters() {
        int count = 0;
        for (TextNode child : children) {
            count += child.countLetters();
        }
        return count;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextComposite.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("children=" + children)
                .toString();
    }
}
