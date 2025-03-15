package com.amazon.ata.dynamodbscanandserialization.scanClothing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides access to the ClothingStore table.
 */
public class ClothingDao {
    private final DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Clothing objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public ClothingDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Use the scan() method to retrieve all the items from the ClothingStore table that have a given clothing type.
     * @param clothingType the given clothing type
     * @return the list of clothing retrieved from the database
     */
    public List<Clothing> scanByClothingType(final String clothingType) {

       /* return mapper.scan(Clothing.class,
                DynamoDBMapper.ScanFilter.builder()
                       .attribute("clothingType").eq(clothingType)
                       .build());*/
        // If you want to sort the results by price, you can add the following line:
        //.sortAscending("price")
        //.build());
        // If you want to filter results by price range, you can add the following line:
        //.filterExpression("#price BETWEEN :minPrice AND :maxPrice")
        //.expressionValues(Map.of(":minPrice", BigDecimal.valueOf(minPrice), ":maxPrice", BigDecimal.valueOf(maxPrice)))
        //.build());
        // If you want to filter results by color, you can add the following line:
        //.filterExpression("#color = :color")
        //.expressionValues(Map.of(":color", color))
        //.build());

        // If you want to filter results by multiple conditions, you can add multiple filter expressions and expression values.
        //.filterExpression("#price BETWEEN :minPrice AND :maxPrice AND #color = :color")
        //.expressionValues(Map.of(":minPrice", BigDecimal.valueOf(minPrice), ":maxPrice", BigDecimal.valueOf(maxPrice), ":color", color))
        //.build());
        // If you want to filter results by multiple conditions with OR, you can add multiple filter expressions and expression values.
        //.filterExpression("(#price BETWEEN :minPrice AND :maxPrice OR #color = :color)")
        //.expressionValues(Map.of(":minPrice", BigDecimal.valueOf(minPrice), ":maxPrice", BigDecimal.valueOf(maxPrice), ":color", color))
        //.build());

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put("clothingType", new AttributeValue().withS(clothingType));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#clothingType = :clothingType")
                .withExpressionAttributeValues(expressionAttributeValues);
        return mapper.scan(Clothing.class, scanExpression);






    }
}
