package com.github.jingerjesus.pandoracompiler.AST;

public abstract class Node {
    private Node[] childNodes;

    public void setChildNodes(Node[] nodes) {
        childNodes = nodes;
    }

    public Node[] getChildNodes() {
        return childNodes;
    }

}
