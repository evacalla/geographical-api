package com.geographical.api.service;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.controller.response.NodeResponse;
import com.geographical.api.exception.ResourceException;
import com.geographical.api.model.Node;
import com.geographical.api.model.NodeType;
import com.geographical.api.repository.NodeRepository;
import com.geographical.api.service.converter.NodeToNodeResponseConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;
    private final WithdrawalPointService withdrawalPointService;
    private final BranchOfficeService branchOfficeService;
    private final NodeToNodeResponseConverter nodeToNodeResponseConverter;

    private static final Logger logger = LoggerFactory.getLogger(NodeService.class);

    @Autowired
    public NodeServiceImpl(NodeRepository nodeRepository, WithdrawalPointService withdrawalPointService,
                           BranchOfficeService branchOfficeService, NodeToNodeResponseConverter nodeToNodeResponseConverter) {
        this.nodeRepository = nodeRepository;
        this.withdrawalPointService = withdrawalPointService;
        this.branchOfficeService = branchOfficeService;
        this.nodeToNodeResponseConverter = nodeToNodeResponseConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NodeResponse> getNodeByCoordinate(Double longitude, Double latitude) {
        logger.info("[NODE-SERVICE] - get node by coordinate longitude {}, latitude {}", longitude, latitude);
        List<Node> nodes = this.nodeRepository.findByCoordinate(longitude, latitude);
        return nodes
                .stream()
                .map(this.nodeToNodeResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public NodeResponse getNodeById(Long id) {
        logger.info("[NODE-SERVICE] - get node by id {}", id);
        Node node = this.nodeRepository.findById(id).orElseThrow( () ->
                new ResourceException(Collections.singletonList(String.format("Not found node with id %s", id))));

        return this.nodeToNodeResponseConverter.convert(node);
    }

    @Override
    @Transactional
    public NodeResponse createNode(NodeRequest request) {

        logger.info("[NODE-SERVICE] - create node request {}", request.toString());
        Node node = null;
        if(NodeType.WITHDRAWAL_POINT.equals(request.getType())) {
            node = this.withdrawalPointService.create(request);
            this.nodeRepository.createNodeTypeWithdrawalPoint(node.getLatitude(),
                    node.getLongitude(), node.getCapacity(), NodeType.WITHDRAWAL_POINT.getNodeType());
        } else {
            node = this.branchOfficeService.create(request);
            this.nodeRepository.createNodeTypeBranchOffice(node.getLatitude(),
                    node.getLongitude(), node.getAddress(), node.getSchedule(), NodeType.BRANCH_OFFICE.getNodeType());
        }

        return this.nodeToNodeResponseConverter.convert(node);

    }


    @Override
    @Transactional
    public NodeResponse deleteNode(Long id) {
        logger.info("[NODE-SERVICE] delete node by id {}", id);
        Node node = this.nodeRepository.findById(id).orElseThrow( () ->
                new ResourceException(Collections.singletonList(String.format("Not found node with id %s", id))));

        this.nodeRepository.delete(node);

        return this.nodeToNodeResponseConverter.convert(node);

    }

    @Override
    @Transactional
    public NodeResponse editNode(NodeRequest request, Long id) {
        logger.info("[NODE-SERVICE] edit node with node id {}, request {}", id, request.toString());
        Node node = this.nodeRepository.findById(id).orElseThrow(() ->
                new ResourceException(Collections.singletonList(String.format("Not found node with id %s", id)))
        );

        if(NodeType.WITHDRAWAL_POINT.equals(request.getType())) {
            node = this.withdrawalPointService.edit(request, node);
            this.nodeRepository.editNodeTypeWithdrawalPoint(node.getLatitude(), node.getLongitude(),
                    node.getCapacity(), node.getType().getNodeType(), node.getId());
        } else {
            node = this.branchOfficeService.edit(request, node);
            this.nodeRepository.editNodeTypeBranchOffice(node.getLatitude(), node.getLongitude(),
                    node.getAddress(), node.getSchedule(), node.getType().getNodeType(), node.getId());
        }

        return this.nodeToNodeResponseConverter.convert(node);

    }


}
