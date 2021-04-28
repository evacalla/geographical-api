package com.geographical.api.repository;

import com.geographical.api.model.Node;
import com.geographical.api.model.NodeType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {

    @Query(value = "SELECT n.* FROM nodes n ORDER BY n.geo <-> " +
            "ST_SETSRID(ST_MAKEPOINT(:latitude, :longitude), 4326)",
            nativeQuery = true)
    List<Node> findByCoordinate(Double longitude, Double latitude);

    @Modifying
    @Query(value = "INSERT INTO nodes(latitude, longitude, address, schedule, type, geo) " +
            "VALUES (:latitude, :longitude, :address, :schedule, :type, ST_SetSRID(ST_MakePoint(:latitude, :longitude), 4326))",
            nativeQuery = true)
    void createNodeTypeBranchOffice(Double latitude, Double longitude, String address, String schedule, String type);

    @Modifying
    @Query(value = "INSERT INTO nodes(latitude, longitude, capacity, type, geo) " +
            "VALUES (:latitude, :longitude, :capacity, :type, ST_SetSRID(ST_MakePoint(:latitude, :logitude), 4326))", nativeQuery = true)
    void createNodeTypeWithdrawalPoint(Double latitude, Double longitude, Integer capacity, String type);

    @Modifying
    @Query(value = "UPDATE nodes SET latitude=:latitude, longitude=:longitude, capacity=:capacity, " +
            "tpye=:type, geo=(ST_SetSRID(ST_MakePoint(:latitude, :longitude), 4326)) WHERE id =:id",
            nativeQuery = true)
    void editNodeTypeWithdrawalPoint(Double latitude, Double longitude, Integer capacity, String type, Long id);

    @Modifying
    @Query(value = "UPDATE nodes SET latitude=:latitude, longitude=:longitude, " +
            "address=:address, schedule=:schedule, type=:type,  geo= (ST_SetSRID(ST_MakePoint(:latitude, :longitude), 4326)) " +
            "WHERE id=:id", nativeQuery = true)
    void editNodeTypeBranchOffice(Double latitude, Double longitude, String address, String schedule, String type, Long id);


}
