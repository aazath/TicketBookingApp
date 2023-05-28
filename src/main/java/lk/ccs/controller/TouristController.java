package lk.ccs.controller;

import lk.ccs.model.Tourist;
import lk.ccs.service.ITouristMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourist")
public class TouristController {

    @Autowired
    private ITouristMgmtService service;

    @PostMapping("/save")
    public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist) {
        try {
            String msg = service.registerTourist(tourist);
            return new ResponseEntity<String>(msg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Failed to register the tourist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> displayTouristDetails() {
        try {
            List<Tourist> allTourist = service.getAllTourist();
            return new ResponseEntity<List<Tourist>>(allTourist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Unable to fetch tourists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> displayTouristyId(@PathVariable("id") Integer id) {
        try {
            Tourist touristById = service.getTouristById(id);
            return new ResponseEntity<Tourist>(touristById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyTourist(@RequestBody Tourist tourist) {
        try {
            String msg = service.modifyTourist(tourist);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/budgetModify/{id}/{amt}")
    public ResponseEntity<String> updateBudgetById(@PathVariable("id") Integer id,@PathVariable("amt") Float amt) {
        try{
            String msg = service.updateTouristById(id,amt);
            return new ResponseEntity<String>(msg,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        try{
            String msg = service.deleteTouristById(id);
            return new ResponseEntity<String>(msg,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
