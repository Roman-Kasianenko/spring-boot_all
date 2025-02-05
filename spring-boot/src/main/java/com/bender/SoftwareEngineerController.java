package com.bender;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    @GetMapping("{id}")
    public SoftwareEngineer getSoftwareEngineerById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @PostMapping
    public void addSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @DeleteMapping("{id}")
    public void deleteSoftwareEngineerById(@PathVariable Integer id) {
        softwareEngineerService.deleteSoftwareEngineerById(id);
    }

    @PutMapping("{id}")
    public void updateSoftwareEngineerById(@RequestBody SoftwareEngineer softwareEngineer,
                                           @PathVariable Integer id) {
        softwareEngineerService.updateSoftwareEngineerById(softwareEngineer, id);
    }
}
