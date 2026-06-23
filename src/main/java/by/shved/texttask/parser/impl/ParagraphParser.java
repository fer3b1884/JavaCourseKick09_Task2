package by.shved.texttask.parser.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.parser.TextParser;
import by.shved.texttask.type.TextNodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements TextParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private final TextParser nextParser;

    public ParagraphParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextNode parent, String text) {
        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        logger.info("Found {} paragraphs", paragraphs.length);
        for (String paragraph : paragraphs) {
            TextNode paragraphNode = new TextComposite(TextNodeType.PARAGRAPH);
            parent.add(paragraphNode);
            nextParser.parse(paragraphNode, paragraph);
        }
    }
}
