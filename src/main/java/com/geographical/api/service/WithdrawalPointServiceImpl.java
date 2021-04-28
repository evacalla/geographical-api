package com.geographical.api.service;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.exception.RequestPayloadException;
import com.geographical.api.model.Node;
import com.geographical.api.model.NodeType;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalPointServiceImpl implements WithdrawalPointService {

    private final static Logger logger = LoggerFactory.getLogger(WithdrawalPointService.class);
    @Override
    public Node create(NodeRequest request) {
        logger.info("[WITHDRAWAL-POINT-SERVICE] create node with request {}", request.toString());
        List<String> errors = this.validate(request);

        if(!errors.isEmpty()) {
            throw new RequestPayloadException(errors);
        }

        Node withdrawalPoint = new Node();

        withdrawalPoint.setCapacity(request.getCapacity());
        withdrawalPoint.setLatitude(request.getLatitude());
        withdrawalPoint.setLongitude(request.getLongitude());
        withdrawalPoint.setType(NodeType.WITHDRAWAL_POINT);

        return withdrawalPoint;
    }

    @Override
    public Node edit(NodeRequest request, Node node) {
        logger.info("[WITHDRAWAL-POINT-SERVICE] edit node {}, with request {}", node.toString(), request.toString());

        List<String> errors = this.validate(request);

        if (!errors.isEmpty()) {
            throw new RequestPayloadException(errors);
        }

        node.setCapacity(request.getCapacity());
        node.setLatitude(request.getLatitude());
        node.setLongitude(request.getLongitude());

       return node;
    }

    private List<String> validate(NodeRequest nodeRequest) {

        List<String> errors = new ArrayList<>();
        if(Optional.ofNullable(nodeRequest.getCapacity()).isPresent()) {
            errors.add("The field 'Capacity' could not to be empty");
        }

        return errors;

    }
}
