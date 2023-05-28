package lk.ccs.service;

import lk.ccs.model.Tourist;

import java.util.List;

public interface ITouristMgmtService {
    public String registerTourist(Tourist tourist);
    public List<Tourist> getAllTourist();
    public Tourist getTouristById(Integer id);

    public String modifyTourist(Tourist tourist);
    public String updateTouristById(Integer id, Float hikeAmount);

    public String deleteTouristById(Integer id);


}
