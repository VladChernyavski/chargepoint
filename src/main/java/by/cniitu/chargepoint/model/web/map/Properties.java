package by.cniitu.chargepoint.model.web.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
            "tittle": "String",
            "address": "String",
            "worktime": "String"
         }
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Properties {

    private String tittle;
    private String address;
    private String worktime;

    public Properties(Properties properties){
        this.tittle = properties.tittle;
        this.address = properties.address;
        this.worktime = properties.worktime;
    }

}
