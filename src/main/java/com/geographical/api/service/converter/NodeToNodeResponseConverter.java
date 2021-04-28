package com.geographical.api.service.converter;

import com.geographical.api.controller.response.NodeResponse;
import com.geographical.api.model.Node;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NodeToNodeResponseConverter implements Converter<Node, NodeResponse> {

    @Override
    public NodeResponse convert(Node node) {
        NodeResponse response = new NodeResponse();

        response.setId(node.getId());
        response.setAddress(node.getAddress());
        response.setLatitude(node.getLatitude());
        response.setLongitude(node.getLongitude());
        response.setCapacity(node.getCapacity());
        response.setSchedule(node.getSchedule());

        return response;
    }
}
