package by.cniitu.chargepoint.model.web.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Properties {

    private String tittle;
    private String address;

    // TODO discuss the necessity
    private String workTime;

    public Properties(Properties properties){
        this.tittle = properties.tittle;
        this.address = properties.address;
        this.workTime = properties.workTime;
    }

}
