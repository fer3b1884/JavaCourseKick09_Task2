package by.shved.texttask.entity;

import by.shved.texttask.type.TextComponentType;

public interface TextComponent {
    void add(TextComponent component);
    TextComponentType getType();
    String restoreText();
}
