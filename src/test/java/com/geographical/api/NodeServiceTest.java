package com.geographical.api;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.controller.response.NodeResponse;
import com.geographical.api.exception.RequestPayloadException;
import com.geographical.api.model.Node;
import com.geographical.api.model.NodeType;
import com.geographical.api.repository.NodeRepository;
import com.geographical.api.service.BranchOfficeServiceImpl;
import com.geographical.api.service.NodeServiceImpl;
import com.geographical.api.service.converter.NodeToNodeResponseConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NodeServiceTest {

    @InjectMocks private NodeServiceImpl nodeService;
    @InjectMocks private BranchOfficeServiceImpl branchOfficeService;
    @Mock private NodeRepository nodeRepository;
    @Mock private NodeToNodeResponseConverter nodeToNodeResponseConverter;

    @Test(expected = RequestPayloadException.class)
    public void when_create_node_fail() {

        NodeRequest request = this.buildRequest();
        request.setCapacity(null);
        request.setAddress(null);

        this.branchOfficeService.create(request);
    }

    @Test
    public void when_search_point_return_near() {

        Double longitude = 2.394694;
        Double latitude = 48.9123;

        List<NodeResponse> response = Collections.singletonList(this.buildResponse());
        when(nodeRepository.findByCoordinate(longitude, latitude))
                .thenReturn(Collections.singletonList(this.buildNode()));
        when(nodeToNodeResponseConverter.convert(any()))
                .thenReturn(this.buildResponse());

        List<NodeResponse> responseExpected = this.nodeService.getNodeByCoordinate(longitude, latitude);

        assertEquals(responseExpected.get(0).getLongitude(), response.get(0).getLongitude());
        assertEquals(responseExpected.get(0).getLatitude(), response.get(0).getLatitude());


    }

    private NodeRequest buildRequest() {

        NodeRequest request = new NodeRequest();
        request.setType(NodeType.BRANCH_OFFICE);
        request.setAddress("Av Calle Falsa 123");
        request.setLatitude(48.858093);
        request.setLongitude(2.294694);
        request.setSchedule("L a V - 10:00 13:00");

        return request;
    }

    private Node buildNode() {

        Node node = new Node();

        node.setLongitude(2.294694);
        node.setLatitude(48.858093);

        return node;
    }

    private NodeResponse buildResponse() {

        NodeResponse response = new NodeResponse();
        response.setLatitude(48.858093);
        response.setLongitude(2.294694);

        return response;
    }



}
