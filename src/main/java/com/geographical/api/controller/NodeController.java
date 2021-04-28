package com.geographical.api.controller;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.controller.response.NodeResponse;
import com.geographical.api.model.Node;
import com.geographical.api.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/node")
public class NodeController {

    private final NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NodeResponse> getNodeById(@PathVariable("id") Long id) {
        NodeResponse response = this.nodeService.getNodeById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NodeResponse>> getNodeByCoordinate(@RequestParam("latitude") Double latitude,
                                                                  @RequestParam("longitude") Double longitude) {
        List<NodeResponse> nodes = this.nodeService.getNodeByCoordinate(longitude, latitude);
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NodeResponse> createNode(@RequestBody @Validated NodeRequest request) {
        NodeResponse response = this.nodeService.createNode(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NodeResponse> editNode(@PathVariable("id") Long id, @RequestBody @Validated NodeRequest request) {
        NodeResponse response = this.nodeService.editNode(request, id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NodeResponse> deleteNode(@PathVariable("id") Long id) {
        NodeResponse response = this.nodeService.deleteNode(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
