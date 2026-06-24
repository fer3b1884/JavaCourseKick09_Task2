package by.shved.texttask.parser.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.impl.TextComposite;
import by.shved.texttask.parser.TextParser;
import by.shved.texttask.entity.TextNodeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements TextParser {
    private final TextParser nextParser;

    public WordParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    @Override
    public void parse(TextNode parent, String text) {
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            createPunctuation(parent, text);
            return;
        }
        int start = matcher.start();
        int end = matcher.end();
        String leftPunctuation = text.substring(0, start);
        String word = matcher.group();
        String rightPunctuation = text.substring(end);
        if (!leftPunctuation.isEmpty()) {
            createPunctuation(parent, leftPunctuation);
        }
        TextNode wordNode = new TextComposite(TextNodeType.WORD);
        parent.add(wordNode);
        nextParser.parse(wordNode, word);
        if (!rightPunctuation.isEmpty()) {
            createPunctuation(parent, rightPunctuation);
        }
    }

    private void createPunctuation(TextNode parent, String punctuation) {
        TextNode punctuationNode = new TextComposite(TextNodeType.PUNCTUATION);
        parent.add(punctuationNode);
        nextParser.parse(punctuationNode, punctuation);
    }
}
