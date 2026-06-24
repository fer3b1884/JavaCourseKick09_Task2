package by.shved.texttask.service.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.TextNodeType;
import by.shved.texttask.service.TextTransformService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextTransformServiceImpl implements TextTransformService {
    private static final Logger logger = LogManager.getLogger(TextTransformServiceImpl.class);

    @Override
    public void swapFirstAndLastLexeme(TextNode text) {
        List<TextNode> sentences = new ArrayList<>();
        int modifiedSentences = 0;
        collectSentences(text, sentences);
        for (TextNode sentence : sentences) {
            List<TextNode> lexemes = new ArrayList<>(sentence.getChildren());
            if (lexemes.size() < 2) {
                continue;
            }
            TextNode first = lexemes.getFirst();
            TextNode last = lexemes.getLast();
            sentence.remove(first);
            sentence.remove(last);
            sentence.add(last);
            sentence.add(first);
            modifiedSentences++;
        }
        logger.info("Swapped first and last lexeme in {} sentences", modifiedSentences);
    }

    private void collectSentences(TextNode node, List<TextNode> sentences) {
        if (node.getType() == TextNodeType.SENTENCE) {
            sentences.add(node);
        } else {
            for (TextNode child : node.getChildren()) {
                collectSentences(child, sentences);
            }
        }
    }
}
