package by.shved.texttask.parser.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.parser.TextParser;
import by.shved.texttask.entity.TextNodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements TextParser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private final TextParser nextParser;

    public SentenceParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextNode parent, String text) {
        String[] sentences = text.split(SENTENCE_REGEX);
        logger.debug("Found {} sentences", sentences.length);
        for (String sentence : sentences) {
            TextNode sentenceNode = new TextComposite(TextNodeType.SENTENCE);
            parent.add(sentenceNode);
            nextParser.parse(sentenceNode, sentence);
        }
    }
}
