package com.geographical.api.model.Converter;

import com.geographical.api.model.NodeType;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Optional;

@Component
public class NodeTypeConverter implements AttributeConverter<NodeType, String> {

    @Override
    public String convertToDatabaseColumn(NodeType nodeType) {
        return Optional.ofNullable(nodeType)
                .map(NodeType::getNodeType)
                .orElse(null);
    }

    @Override
    public NodeType convertToEntityAttribute(String s) {
        return Optional.ofNullable(s)
                .map(NodeType::fromString)
                .orElse(null);
    }
}
