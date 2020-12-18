package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.model.web.map.*;
import by.cniitu.chargepoint.model.web.map.Properties;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChargePointService {

    // all charge points for map
    // TODO use real values after all
    // TODO save changes in mongoDB
    public static Map<Integer, MapPoint> chargePointsMap = new HashMap<>();

    static{

        chargePointsMap.put(31, new MapPoint(31, new Properties("заправка 31",
                "27.6416015625, 53.97183955821782", "00:00 - 24:00"), new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.6416015625, 53.97183955821782))));
        chargePointsMap.get(31).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2",
                "12kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(32, new MapPoint(32, new Properties("заправка 32", "27.733612060546875, 53.97305115985005", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.733612060546875, 53.97305115985005))));
        chargePointsMap.get(32).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2", "12kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(32).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC3/Type2", "25kW", new Tariffs(5.99, 0.56)));

        chargePointsMap.put(3, new MapPoint(3, new Properties("заправка 3", "27.540664672851562, 53.893819129552305", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.540664672851562, 53.893819129552305))));
        chargePointsMap.get(3).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(3).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(4, new MapPoint(4, new Properties("заправка 4", "27.6416015625, 53.97183955821782", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.6416015625, 53.97183955821782))));
        chargePointsMap.get(4).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(4).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(5, new MapPoint(5, new Properties("заправка 5", "27.713699340820312, 53.95527747164856", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.713699340820312, 53.95527747164856))));
        chargePointsMap.get(5).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(5).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(6, new MapPoint(6, new Properties("заправка 6", "27.805709838867188, 53.879654718999255", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.805709838867188, 53.879654718999255))));
        chargePointsMap.get(6).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(6).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(7, new MapPoint(7, new Properties("заправка 7", "27.743911743164062, 53.814841989541314", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.743911743164062, 53.814841989541314))));
        chargePointsMap.get(7).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2", "12kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(7).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(8, new MapPoint(8, new Properties("заправка 8", "27.5482177734375, 53.80186739998563", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.5482177734375, 53.80186739998563))));
        chargePointsMap.get(8).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(8).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(9, new MapPoint(9, new Properties("заправка 9", "27.38616943359375, 53.824164995581235", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.38616943359375, 53.824164995581235))));
        chargePointsMap.get(9).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(9).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(10, new MapPoint(10, new Properties("заправка 10", "27.384109497070312, 53.91930297491356", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.384109497070312, 53.91930297491356))));
        chargePointsMap.get(10).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(10).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(11, new MapPoint(11, new Properties("заправка 11", "27.522811889648438, 53.960933558166715", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.522811889648438, 53.960933558166715))));
        chargePointsMap.get(11).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(12, new MapPoint(12, new Properties("заправка 12", "27.59765625, 54.010189840172714", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.59765625, 54.010189840172714))));
        chargePointsMap.get(12).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(12).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(13, new MapPoint(13, new Properties("заправка 13", "27.568130493164062, 54.03721564638805", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.568130493164062, 54.03721564638805))));
        chargePointsMap.get(13).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(13).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(14, new MapPoint(14, new Properties("заправка 14", "27.587356567382812, 53.86589040962872", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.587356567382812, 53.86589040962872))));
        chargePointsMap.get(14).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(14).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC3/Type2", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(15, new MapPoint(15, new Properties("заправка 15", "27.475433349609375, 53.93830460778626", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.475433349609375, 53.93830460778626))));
        chargePointsMap.get(15).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(15).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(16, new MapPoint(16, new Properties("заправка 16", "27.456207275390625, 53.88410690807865", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.456207275390625, 53.88410690807865))));
        chargePointsMap.get(16).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(17, new MapPoint(17, new Properties("заправка 17", "27.518692016601562, 53.86508060329478", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.518692016601562, 53.86508060329478))));
        chargePointsMap.get(17).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(17).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC3/Type2", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(18, new MapPoint(18, new Properties("заправка 18", "27.620315551757812, 53.94396299650727", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.620315551757812, 53.94396299650727))));
        chargePointsMap.get(18).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(18).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(19, new MapPoint(19, new Properties("заправка 19", "27.642288208007812, 53.91040562094631", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.642288208007812, 53.91040562094631))));
        chargePointsMap.get(19).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(19).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(20, new MapPoint(20, new Properties("заправка 20", "27.562637329101562, 53.93102840881328", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.562637329101562, 53.93102840881328))));
        chargePointsMap.get(20).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(20).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC3/Type2", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(21, new MapPoint(21, new Properties("заправка 21", "27.489852905273438, 53.91687661183859", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.489852905273438, 53.91687661183859))));
        chargePointsMap.get(21).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(22, new MapPoint(22, new Properties("заправка 22", "27.49465942382812, 53.84199453239005", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.49465942382812, 53.84199453239005))));
        chargePointsMap.get(22).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(22).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(23, new MapPoint(23, new Properties("заправка 23", "27.651901245117188, 53.86386586440115", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.651901245117188, 53.86386586440115))));
        chargePointsMap.get(23).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(23).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC3/Type2", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(24, new MapPoint(24, new Properties("заправка 24", "27.65533447265625, 53.888558623056724", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.65533447265625, 53.888558623056724))));
        chargePointsMap.get(24).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(24).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(25, new MapPoint(25, new Properties("заправка 25", "27.694473266601562, 53.89826981020947", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.694473266601562, 53.89826981020947))));
        chargePointsMap.get(25).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(25).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(26, new MapPoint(26, new Properties("заправка 26", "27.737045288085938, 53.93102840881328", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.737045288085938, 53.93102840881328))));
        chargePointsMap.get(26).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(26).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(27, new MapPoint(27, new Properties("заправка 27", "27.739791870117188, 53.882892721712686", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.739791870117188, 53.882892721712686))));
        chargePointsMap.get(27).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(27).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));


        chargePointsMap.put(28, new MapPoint(28, new Properties("заправка 28", "27.66357421875, 53.8282178295217", "00:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.66357421875, 53.8282178295217))));
        chargePointsMap.get(28).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(28).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(29, new MapPoint(29, new Properties("заправка 29", "27.588043212890625, 53.835917133888245", "01:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.588043212890625, 53.835917133888245))));
        chargePointsMap.get(29).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)));
        chargePointsMap.get(29).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC3/Type2", "25kW", new Tariffs(5.99, 0.56)));


        chargePointsMap.put(30, new MapPoint(30, new Properties("заправка 30", "27.34840393066406, 53.880059483052605", "06:00 - 24:00"),
                new HashMap<>(),
                new Geometry("Point", Arrays.asList(27.34840393066406, 53.880059483052605))));
        chargePointsMap.get(30).getConnectors().put(1, new Connector(1, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)));
        chargePointsMap.get(30).getConnectors().put(2, new Connector(2, ConnectorStatus.random(), "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)));

    }

    public Connector getConnector(Integer id, Integer conId) throws Exception{
        MapPoint mapPoint = chargePointsMap.get(id);
        if(mapPoint == null){
           throw new Exception("unknown id");
        }
        Connector connector = mapPoint.getConnectors().get(conId);
        if(connector == null){
            throw new Exception("unknown connector id");
        }
        return connector;
    }

}
