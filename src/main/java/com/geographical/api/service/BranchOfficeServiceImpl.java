package com.geographical.api.service;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.exception.RequestPayloadException;
import com.geographical.api.model.Node;
import com.geographical.api.model.NodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchOfficeServiceImpl implements BranchOfficeService {

    private static final Logger logger = LoggerFactory.getLogger(BranchOfficeService.class);

    @Override
    public Node create(NodeRequest request) {
        logger.info("[BRANCH-OFFICE-SERVICE] create node with request {}", request.toString());
        List<String> errors = this.validator(request);

        if(!errors.isEmpty()) {
            throw new RequestPayloadException(errors);
        }

        Node branchOffice = new Node();
        branchOffice.setAddress(request.getAddress());
        branchOffice.setLatitude(request.getLatitude());
        branchOffice.setLongitude(request.getLongitude());
        branchOffice.setSchedule(request.getSchedule());
        branchOffice.setType(NodeType.BRANCH_OFFICE);

        return branchOffice;
    }

    @Override
    public Node edit(NodeRequest request, Node node) {
        logger.info("[BRANCH-OFFICE-SERVICE] edit node {}, request {}",node.toString(), request.toString() );
        List<String> errors = this.validator(request);

        if(!errors.isEmpty()) {
            throw new RequestPayloadException(errors);
        }

        node.setAddress(request.getAddress());
        node.setLatitude(request.getLatitude());
        node.setLongitude(request.getLongitude());
        node.setSchedule(request.getSchedule());

        return node;
    }
}
