package com.levi.dataStructures.stack;

import java.util.ArrayList;
import java.util.HashMap;

public class MyStack {
    private HashMap <Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }

        if (visited.containsKey(node)){
            return visited.get(node);
        }

        var cloned = new Node(node.val, new ArrayList<>());
        visited.put(node, cloned);

        for (var neighbor : node.neighbors){
            cloned.neighbors.add(cloneGraph(neighbor));
        }

        return cloned;

    }
}
