package by.shved.texttask.service.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.TextNodeType;
import by.shved.texttask.service.TextStatisticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextStatisticsServiceImpl implements TextStatisticsService {
    private static final Logger logger = LogManager.getLogger(TextStatisticsServiceImpl.class);

    @Override
    public int findMaxSentencesWithSameWords(TextNode textNode) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        List<TextNode> sentences = new ArrayList<>();
        collectSentences(textNode, sentences);
        for (TextNode sentence : sentences) {
            Set<String> uniqueWords = new HashSet<>();
            collectWords(sentence, uniqueWords);
            for (String word : uniqueWords) {
                wordFrequency.merge(
                        word,  // key
                        1,  // default value
                        Integer::sum  // Bifunction (if key exists, then -> sum)
                );
            }
        }
        int max = 0;
        for (Integer value : wordFrequency.values()) {
            if (value > max) {
                max = value;
            }
        }
        logger.info("Found maximum number of sentences with the same word: {}", max);
        return max;
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

    private void collectWords(TextNode node, Set<String> words) {
        if (node.getType() == TextNodeType.WORD) {
            words.add(node.restoreText().toLowerCase());
            return;
        }
        if (node.getType() == TextNodeType.SYMBOL) {
            return;
        }
        for (TextNode child : node.getChildren()) {
            collectWords(child, words);
        }
    }
}
