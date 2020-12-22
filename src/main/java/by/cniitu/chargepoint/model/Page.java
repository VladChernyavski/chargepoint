package by.cniitu.chargepoint.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Page<T> {

    // TODO maybe change it
    static public int pageSize = 50;

    Integer pageAmount;
    Integer currentPage;

    List<T> info;

    public Page(Integer pageAmount, Integer currentPage, List<T> info) throws Exception {
        if(info.size() > pageSize)
            throw new Exception("info.size() > pageSize");
        this.pageAmount = pageAmount;
        this.currentPage = currentPage;
        this.info = new LinkedList<>();
        this.info.addAll(info);

    }

}
