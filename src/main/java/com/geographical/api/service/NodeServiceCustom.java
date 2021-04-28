package com.geographical.api.service;

import com.geographical.api.controller.request.NodeRequest;
import com.geographical.api.exception.RequestPayloadException;
import com.geographical.api.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface NodeServiceCustom<U> {

    U create(NodeRequest request);

    U edit(NodeRequest request, Node node);

    default List<String> validator(NodeRequest request) {

        List<String> errors = new ArrayList<>();

        switch (request.getType()) {

            case BRANCH_OFFICE:
                errors.addAll(this.branchOfficeValidator(request));
                break;

            case WITHDRAWAL_POINT:
                errors.addAll(this.withdrawalPointValidator(request));
                break;
            default:
                throw new RequestPayloadException(Collections.singletonList("The field 'type' could not to be empty"));

        }
        return errors;
    }

    default List<String> branchOfficeValidator(NodeRequest request) {

        List<String> errors = new ArrayList<>();

        if(!Optional.ofNullable(request.getAddress()).isPresent()) {
            errors.add("The field 'Address' could not to be empty");
        }

        if(!Optional.ofNullable(request.getSchedule()).isPresent()) {
            errors.add("The field 'Schedule' could not to be empty");
        }

        if(!Optional.ofNullable(request.getLatitude()).isPresent()){
            errors.add("The field 'Latitude' could not to be empty");
        }

        if(!Optional.ofNullable(request.getLongitude()).isPresent()){
            errors.add("The field 'Longitude' could not to be empty");
        }

        return errors;
    }

    default List<String> withdrawalPointValidator(NodeRequest request) {

        List<String> errors = new ArrayList<>();
        if(!Optional.ofNullable(request.getLatitude()).isPresent()){
            errors.add("The field 'Latitude' could not to be empty");
        }

        if(!Optional.ofNullable(request.getLongitude()).isPresent()){
            errors.add("The field 'Longitude' could not to be empty");
        }

        if(!Optional.ofNullable(request.getCapacity()).isPresent()){
            errors.add("The field 'Capacity' could not to be empty");
        }

        return errors;
    }

}
