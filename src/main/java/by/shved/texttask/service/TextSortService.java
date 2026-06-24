package by.shved.texttask.service;

import by.shved.texttask.entity.TextNode;

import java.util.List;

public interface TextSortService {
    List<TextNode> sortSentencesByLetter(TextNode text, char letter);
}
