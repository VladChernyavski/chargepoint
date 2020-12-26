package by.cniitu.chargepoint.model;

import by.cniitu.chargepoint.entity.Tariff;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TariffTo {

    private Double charge;
    private Double reserve;

    public TariffTo(Tariff tariff){
        charge = (double)(int)(tariff.getCharge() * 100) / 100;
        reserve = (double)(int)(tariff.getReserve() * 100) / 100;
    }

}
