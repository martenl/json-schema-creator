package de.brands4friends.schemacreator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface SchemaCreation {

    JsonNode createSchema() throws JsonProcessingException;
}
