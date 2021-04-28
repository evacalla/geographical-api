package com.geographical.api.model;

public enum NodeType {

    BRANCH_OFFICE("BRANCH_OFFICE"),
    WITHDRAWAL_POINT("WITHDRAWAL_POINT");

    private String nodeType;

    NodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public static NodeType fromString(String nodeType) {
        NodeType type = null;
        for (NodeType n : NodeType.values()) {
            if(n.getNodeType().equals(nodeType)) type = n;
        }
        return type;
    }


}
