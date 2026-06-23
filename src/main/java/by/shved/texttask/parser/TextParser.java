package by.shved.texttask.parser;

import by.shved.texttask.entity.TextNode;

public interface TextParser {
    String PARAGRAPH_REGEX = "\\R+";
    String SENTENCE_REGEX = "(?<=[.!?])\\s+";
    String LEXEME_REGEX = "\\s+";

    void parse(TextNode parent, String text);
}
