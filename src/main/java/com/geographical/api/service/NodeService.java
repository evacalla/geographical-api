package com.geographical.api.service;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.controller.response.NodeResponse;
import com.geographical.api.model.Node;

import java.util.List;

public interface NodeService {

    /**
     * Search node by parameters, return node nearer
     * @param longitude
     * @param latitude
     * @return
     */
    List<NodeResponse> getNodeByCoordinate(Double longitude, Double latitude);

    NodeResponse getNodeById(Long id);

    /**
     * Create node with request
     * @param request
     * @return
     */
    NodeResponse createNode(NodeRequest request);

    /**
     * Delete node with id
     * @param id
     * @return
     */
    NodeResponse deleteNode(Long id);

    /**
     * Edit node with request and id
     * @param request
     * @param id
     * @return
     */
    NodeResponse editNode(NodeRequest request, Long id);
}
