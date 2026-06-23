package by.shved.texttask.type;

public enum TextNodeType {
    TEXT(""),
    PARAGRAPH("\n"),
    SENTENCE(" "),
    LEXEME(" "),
    SYMBOL("");

    private final String delimiter;

    TextNodeType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
