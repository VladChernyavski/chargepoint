package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.repository.ChargePointRepository;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ChargePointService {

    final ServerWebSocket serverWebSocket;
    final ChargePointRepository chargePointRepository;

    public ChargePointService(ServerWebSocket serverWebSocket, ChargePointRepository chargePointRepository) {
        this.serverWebSocket = serverWebSocket;
        this.chargePointRepository = chargePointRepository;
    }

    @PostConstruct
    public void postConstruct(){

        // TODO get everything from database and put to serverWebSocket.map


    }

    public void broadcastUpdate(Set<Integer> updateIds){

        // TODO get all updated ids and set in to the map by using some methods of serverWebSocket

        serverWebSocket.broadcastUpdate(updateIds);
    }

    public void broadcastUpdate(Integer updateId){

        // TODO get all updated ids and set in to the map by using some methods of serverWebSocket

        serverWebSocket.broadcastUpdate(updateId);
    }

    public List<ChargePointEntity> findAll(){
        return chargePointRepository.findAll();
    }

}
