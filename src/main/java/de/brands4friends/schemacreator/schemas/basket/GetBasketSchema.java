package de.brands4friends.schemacreator.schemas.basket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import de.brands4friends.schemacreator.util.SchemaCreation;
import de.brands4friends.schemacreator.util.SchemaCreator;

public class GetBasketSchema implements SchemaCreation {

    @Override
    public JsonNode createSchema() throws JsonProcessingException {
        SchemaCreator creator = new SchemaCreator();
        creator.id("getBasket.json")
                .description("Schema for a reply to /user/basket/entry")
                .setRequiredFields("meta", "result")
                .externalDefinition("BasketEntry")
                .externalDefinition("Meta")
                .internalDefinition("LastPaymentUsage");

        creator.property("result").type("array").min(1).max(1);

        creator.property("meta").type("object");

        return creator.create();
    }
}
