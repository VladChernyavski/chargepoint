package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.repository.ConnectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class ConnectorController {

    @Autowired
    ConnectorRepository connectorRepository;

    // an end-point that gives existing types of connectors (Type2, J1772, ...)
    @GetMapping("/get/connector/types")
    public ResponseEntity<Object> getConnectorTypes(){

        return ResponseEntity.ok(connectorRepository.findAll());

    }

}
