package by.shved.texttask.entity;

public enum TextNodeType {
    TEXT(""),
    PARAGRAPH("\n"),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    PUNCTUATION(""),
    SYMBOL("");

    private final String delimiter;

    TextNodeType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
