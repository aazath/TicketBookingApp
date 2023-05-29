package lk.ccs.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("For Registering Tourist")
    public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist) {
        try {
            String msg = service.registerTourist(tourist);
            return new ResponseEntity<String>(msg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Failed to register the tourist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    @ApiOperation("For Fetch All Tourist")
    public ResponseEntity<?> displayTouristDetails() {
        try {
            List<Tourist> allTourist = service.getAllTourist();
            return new ResponseEntity<List<Tourist>>(allTourist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Unable to fetch tourists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    @ApiOperation("For Find a Tourist by ID")
    public ResponseEntity<?> displayTouristyId(@PathVariable("id") Integer id) {
        try {
            Tourist touristById = service.getTouristById(id);
            return new ResponseEntity<Tourist>(touristById, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modify")
    @ApiOperation("For Modifying the Tourist Details")
    public ResponseEntity<String> modifyTourist(@RequestBody Tourist tourist) {
        try {
            String msg = service.modifyTourist(tourist);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/budgetModify/{id}/{amt}")
    @ApiOperation("For Updating the Package Details")
    public ResponseEntity<String> updateBudgetById(@PathVariable("id") Integer id,@PathVariable("amt") Float amt) {
        try{
            String msg = service.updateTouristById(id,amt);
            return new ResponseEntity<String>(msg,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("For Deleting a Tourist by ID")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        try{
            String msg = service.deleteTouristById(id);
            return new ResponseEntity<String>(msg,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
