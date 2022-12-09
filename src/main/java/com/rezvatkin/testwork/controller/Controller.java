package com.rezvatkin.testwork.controller;

import com.rezvatkin.testwork.entity.Division;
import com.rezvatkin.testwork.service.DivisionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class Controller {
    private DivisionService service;

    @GetMapping("/divisions")
    public List<Division> listDivisions() {
        return service.findAll();
    }

    // Здесь в качестве примера обработки прокинул эксепшн
    @GetMapping("divisions/{divisionId}")
    public Division getDivision(@PathVariable int divisionId) {
        var division = service.findById(divisionId);
        if (division == null) {
            throw new RuntimeException("Division id not found - " + divisionId);
        }
        return division;
    }

    @PostMapping("/divisions")
    public Division addDivision(@RequestBody Division division) {
        service.save(division);
        return division;
    }

    // Очевидно, что в реальном приложении этот метод должен включтать в себя обработку ошибок и null
    @PutMapping("/divisions")
    public Division updateDivision(@RequestBody Division division) {
        service.update(division);
        return division;
    }

    // Такую обработку сделал для наглядности и экономии времени
    @DeleteMapping("divisions/{divisionId}")
    public String deleteDivision(@PathVariable int divisionId){
        var tempDivision = service.findById(divisionId);
        if (tempDivision == null) {
            return "Division id not found - " + divisionId;
        } else if (tempDivision.isBadChoiceOfName()) {
            return "I can't delete the system record with id - " + divisionId;
        } else if (service.findAllByParentId(divisionId).size() != 0) {
            return "I can't delete a division with its child divisions - " + divisionId;
        }
        service.deleteById(divisionId);
        return "Deleted division id - " + divisionId + ", it's name " + tempDivision.getName();
    }
}
