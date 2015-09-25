package de.brands4friends.schemacreator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchemaCreator {

    public static final String SCHEMA = "http://json-schema.org/draft-04/schema#";
    String id;
    String description;
    final Map<String,PropertyCreator> properties;
    final Map<String,PropertyCreator> internalDefinitions;
    final List<String> externalDefinitions;
    final List<String> requiredFields;


    public SchemaCreator() {
        externalDefinitions = new ArrayList<String>();
        requiredFields = new ArrayList<String>();
        properties = new HashMap<String, PropertyCreator>();
        internalDefinitions = new HashMap<String, PropertyCreator>();
    }


    public SchemaCreator id(String id){
        this.id = id;
        return this;
    }

    public SchemaCreator description(String description){
        this.description = description;
        return this;
    }

    public SchemaCreator setRequiredFields(String ... fields){
        for(String field : fields){
            requiredFields.add(field);
        }
        return this;
    }

    public SchemaCreator internalDefinition(String definition){
        internalDefinitions.put(definition,new PropertyCreator());
        return this;
    }

    public SchemaCreator externalDefinition(String definition){
        externalDefinitions.add(definition);
        return this;
    }

    public PropertyCreator property(String name) {
        properties.put(name, new PropertyCreator());
        return properties.get(name);
    }


    public JsonNode create() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode schema = mapper.createObjectNode();
        schema.put("id", id).put("$schema", SCHEMA).put("description",description).put("type", "object");
        ArrayNode requiredFieldsArray = schema.putArray("required");
        createRequiredFields(requiredFieldsArray);
        ObjectNode propertiesObject = schema.putObject("properties");
        createProperties(propertiesObject);
        ObjectNode definitionsObject = schema.putObject("definitions");
        createDefinitions(definitionsObject);
        return schema;
    }

    private void createProperties(ObjectNode propertiesObject) {
        for(String property : properties.keySet()){
            PropertyCreator propertyCreator = properties.get(property);
            propertyCreator.create(propertiesObject.putObject(property));
        }
    }

    private void createRequiredFields(ArrayNode requiredFieldsArray) {
        for(String requiredField : requiredFields){
            requiredFieldsArray.add(requiredField);
        }
    }

    private void createDefinitions(ObjectNode definitionsObject) {
        for(String internalDefinition : internalDefinitions.keySet()){
            ObjectNode definitionNode = definitionsObject.putObject(internalDefinition);
        }
        for(String externalDefinition : externalDefinitions){
            ObjectNode definitionNode = definitionsObject.putObject(externalDefinition);
            definitionNode.put("$ref","/../definitions.json#/definitions/"+externalDefinition);
        }
    }
}
