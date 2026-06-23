package by.shved.texttask.parser.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.parser.TextParser;
import by.shved.texttask.type.TextNodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements TextParser {
    private static final Logger logger = LogManager.getLogger(LexemeParser.class);
    private final TextParser nextParser;

    public LexemeParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextNode parent, String text) {
        String[] lexemes = text.split(LEXEME_REGEX);
        logger.debug("Found {} lexemes", lexemes.length);
        for (String lexeme : lexemes) {
            TextNode lexemeNode = new TextComposite(TextNodeType.LEXEME);
            parent.add(lexemeNode);
            nextParser.parse(lexemeNode, lexeme);
        }
    }
}
