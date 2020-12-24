package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.repository.ChargePointRepository;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChargePointService {

    @Autowired
    ServerWebSocket serverWebSocket;

    @Autowired
    ChargePointRepository chargePointRepository;

    public void broadcastUpdate(Set<Integer> updateIds){
        serverWebSocket.broadcastUpdate(updateIds);
    }

    public void broadcastUpdate(Integer updateId){
        serverWebSocket.broadcastUpdate(updateId);
    }

    public List<ChargePointEntity> findAll(){
        return chargePointRepository.findAll();
    }

}
