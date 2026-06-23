package by.shved.texttask.entity.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.TextNodeType;

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
    public boolean add(TextNode component) {
        return children.add(component);
    }

    @Override
    public boolean remove(TextNode component) {
        return children.remove(component);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        if (type != that.type) {
            return false;
        }
        return children.equals(that.children);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextComposite.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("children=" + children)
                .toString();
    }
}
