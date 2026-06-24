package by.shved.texttask.service.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.TextNodeType;
import by.shved.texttask.service.TextSortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextSortServiceImpl implements TextSortService {
    private static final Logger logger = LogManager.getLogger(TextSortServiceImpl.class);

    @Override
    public List<TextNode> sortSentencesByLetter(TextNode textNode, char targetLetter) {
        List<TextNode> sentences = new ArrayList<>();
        collectSentences(textNode, sentences);
        char lowerLetter = Character.toLowerCase(targetLetter);
        Comparator<TextNode> comparator = Comparator.comparingInt(
                sentence -> countTargetLetters(sentence, lowerLetter));
        sentences.sort(comparator);
        logger.info("Sorted {} sentences by letter '{}'", sentences.size(), targetLetter);
        return sentences;
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

    private int countTargetLetters(TextNode node, char targetLetter) {
        if (node.getType() == TextNodeType.SYMBOL) {
            String text = node.restoreText();
            return (!text.isBlank() && Character.toLowerCase(text.charAt(0)) == targetLetter) ? 1 : 0;
        } else {
            int count = 0;
            for (TextNode child : node.getChildren()) {
                count += countTargetLetters(child, targetLetter);
            }
            return count;
        }
    }
}
