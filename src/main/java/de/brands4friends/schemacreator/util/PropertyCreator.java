package de.brands4friends.schemacreator.util;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class PropertyCreator {

    private String type;
    private Integer min = null;
    private Integer max = null;

    public PropertyCreator type(String type){
        this.type = type;
        return this;
    }

    public PropertyCreator min(int min){
        this.min = min;
        return this;
    }

    public PropertyCreator max(int max){
        this.max = max;
        return this;
    }

    public void create(ObjectNode objectNode){
        objectNode.put("type",type);
        if(type.equals("array")){
            createArray(objectNode);
        }
    }

    private void createArray(ObjectNode objectNode) {
        if(min != null){
            objectNode.put("minItems",min);
        }
        if(max != null){
            objectNode.put("maxItems",max);
        }
    }

}
