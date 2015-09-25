package de.brands4friends.schemacreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.brands4friends.schemacreator.schemas.basket.GetBasketSchema;
import de.brands4friends.schemacreator.util.SchemaCreation;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws JsonProcessingException {
        boolean asYaml = true;
        ObjectMapper mapper;
        if(asYaml){
            mapper = new ObjectMapper(new YAMLFactory());
        }else{
            mapper = new ObjectMapper();
        }
        List<SchemaCreation> schemaCreations = new ArrayList<SchemaCreation>();
        schemaCreations.add(new GetBasketSchema());
        for(SchemaCreation schemaCreation : schemaCreations){
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schemaCreation.createSchema()));
        }
    }
}
