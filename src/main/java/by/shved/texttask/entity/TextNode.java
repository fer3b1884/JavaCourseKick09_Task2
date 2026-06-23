package by.shved.texttask.entity;

import by.shved.texttask.type.TextNodeType;

import java.util.List;

public interface TextNode {  // Component
    void add(TextNode component);
    void remove(TextNode component);
    TextNodeType getType();
    List<TextNode> getChildren();
    String restoreText();
    int countSymbols();
    int countLetters();
}
