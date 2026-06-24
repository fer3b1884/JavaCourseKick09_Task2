package by.shved.texttask.service;

import by.shved.texttask.entity.TextNode;

public interface TextStatisticsService {
    int findMaxSentencesWithSameWords(TextNode textNode);
}
