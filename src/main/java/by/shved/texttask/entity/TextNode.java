package by.shved.texttask.entity;

import java.util.List;

public interface TextNode {  // Component
    boolean add(TextNode component);
    boolean remove(TextNode component);
    TextNodeType getType();
    List<TextNode> getChildren();
    String restoreText();
    int countSymbols();
    int countLetters();
}
