package com.github.jingerjesus.pandoracompiler.AST;

public abstract class Node {
    private Node[] childNodes;

    public void setChildNodes(Node[] nodes) {
        childNodes = nodes;
    }

    public Node[] getChildNodes() {
        return childNodes;
    }


    public String toString() {
        String out = "Hi! I'm a node!";
        if (childNodes != null ) {
            out = out +  " I have children! Here they are: \n " + childNodes[0].toString() + ", " + childNodes[1].toString();
        } else {
            out = out + " I have no children.";
        }
        return out;
    }

}
