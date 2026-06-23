package by.shved.texttask.parser.impl;

import by.shved.texttask.entity.TextNode;
import by.shved.texttask.entity.impl.SymbolLeaf;
import by.shved.texttask.parser.TextParser;

public class SymbolParser implements TextParser {
    public SymbolParser() {
    }

    @Override
    public void parse(TextNode parent, String text) {
        for (char symbol : text.toCharArray()) {
            parent.add(new SymbolLeaf(symbol));
        }
    }
}
