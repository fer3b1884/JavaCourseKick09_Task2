package by.shved.texttask.parser;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.parser.impl.*;
import by.shved.texttask.entity.TextNodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextFacadeParser {
    private static final Logger logger = LogManager.getLogger(TextFacadeParser.class);
    private final TextParser paragraphParser;

    public TextFacadeParser() {
        paragraphParser =
                new ParagraphParser(
                        new SentenceParser(
                                new LexemeParser(
                                        new WordParser(
                                                new SymbolParser()
                                        )
                                )
                        )
                );
    }

    public TextNode parse(String text) {
        logger.info("Started parsing text");
        TextNode root = new TextComposite(TextNodeType.TEXT);
        paragraphParser.parse(root, text);
        logger.info("Text parsed successfully");
        return root;
    }
}
