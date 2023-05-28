package lk.ccs.service;

import lk.ccs.dao.ITouristRepo;
import lk.ccs.exception.TouristNotFoundException;
import lk.ccs.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristMgmtServiceImpl implements ITouristMgmtService{

    @Autowired
    private ITouristRepo repo;
    @Override
    public String registerTourist(Tourist tourist) {
        Integer id = repo.save(tourist).getTid();
        return "Tourist Saved with ID: "+id;
    }

    @Override
    public List<Tourist> getAllTourist() {
        List<Tourist> all = repo.findAll();
        all.sort((t1,t2) -> t1.getTid().compareTo(t2.getTid()));
        return all;
    }

    @Override
    public Tourist getTouristById(Integer id) {
        Optional<Tourist> optional = repo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new TouristNotFoundException("Record not found for the  ID "+id);
        }
    }

    @Override
    public String modifyTourist(Tourist tourist) {
        Optional<Tourist> byId = repo.findById(tourist.getTid());
        if(byId.isPresent()){
            repo.save(tourist);
            return "Tourist with the ID "+tourist.getTid() + " is updated";
        }
        else {
            throw new TouristNotFoundException("Tourist with the ID "+ tourist.getTid() + " is not available  for updation");
        }
    }

    @Override
    public String updateTouristById(Integer id, Float hikeAmount) {
        Optional<Tourist> byId = repo.findById(id);
        if(byId.isPresent()){
            Tourist tourist = byId.get();
            tourist.setCost((tourist.getCost() + hikeAmount));
            repo.save(tourist);
            return "Tourist with the ID "+tourist.getTid() + " is updated";
        }
        else {
            throw new TouristNotFoundException("Tourist with the ID "+ id + " is not available  for updation");
        }
    }

    @Override
    public String deleteTouristById(Integer id) {
        Optional<Tourist> optional = repo.findById(id);
        if(optional.isPresent()){
            repo.delete(optional.get());
            return "Tourist with the ID "+id + " is deleted";
        }
        else {
            throw new TouristNotFoundException("Tourist with the ID "+ id + " is not available  for deletion");
        }
    }


}
