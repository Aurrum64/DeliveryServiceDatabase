package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CallCentreEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CallCentreRepository;

import java.util.List;
import java.util.Map;

@Controller
public class CallCentreController {

    private CallCentreRepository callCentreRepository;

    @Autowired
    public CallCentreController(CallCentreRepository callCentreRepository) {
        this.callCentreRepository = callCentreRepository;
    }

    @GetMapping("/callcentre")
    public String CallCentreView(Map<String, Object> model) {
        Iterable<CallCentreEntity> callCentres = callCentreRepository.findAll();
        model.put("callcentres", callCentres);
        return "callcentre";
    }

    @PostMapping("/callcentre")
    public String add(@RequestParam (required = false, defaultValue = "0") Integer managerId,
                      @RequestParam String name,
                      @RequestParam String deliveryRegion,
                      @RequestParam (required = false, defaultValue = "0")Integer locationId) {
        CallCentreEntity callCentre = new CallCentreEntity();
        callCentre.setManagerId(managerId);
        callCentre.setName(name);
        callCentre.setDeliveryRegion(deliveryRegion);
        callCentre.setLocationId(locationId);
        callCentreRepository.save(callCentre);
        return "redirect:/callcentre";
    }

    @PostMapping("/callcentreFilter")
    public String findByName(@RequestParam String name, Map<String, Object> model)  {
        Iterable<CallCentreEntity> callCentres;
        if (!name.isEmpty()) {
            callCentres = callCentreRepository.findByName(name);
        }
        else {
            callCentres = callCentreRepository.findAll();
        }
        if (!callCentres.iterator().hasNext()) {
            model.put("filterCheck", "No callcentre with such name!");
            return "callcentre";
        } else {
            model.put("callcentres", callCentres);
        }
        return "callcentre";
    }
    @Transactional
    @PostMapping("/callcentreDelete")
    public String deleteCallCentre(@RequestParam Integer departmentId, Map<String, Object> model) {
        List<CallCentreEntity> callCentre = callCentreRepository.findByDepartmentId(departmentId);
        if (callCentre.isEmpty()) {
            model.put("deleteIdCheck", "No callcentre with such index!");
            return "callcentre";
        } else {
            callCentreRepository.deleteByDepartmentId(departmentId);
        }
        return "redirect:/callcentre";
    }

    @Transactional
    @PostMapping("/callcentreUpdate")
    public String updateCallCentre(@RequestParam Integer departmentId,
                                   @RequestParam (required = false) Integer managerId,
                                   @RequestParam (required = false) String name,
                                   @RequestParam (required = false) String deliveryRegion,
                                   @RequestParam (required = false) Integer locationId,
                                Map<String, Object> model) {
        List<CallCentreEntity> callCentre = callCentreRepository.findByDepartmentId(departmentId);
        if (callCentre.isEmpty()) {
            model.put("updateIdCheck", "Callcentre with such index does not exist!");
            return "callcentre";
        } else {
            if (!name.isEmpty()) {
                callCentreRepository.setNameFor(name, departmentId);
            }
            if (!deliveryRegion.isEmpty()) {
                callCentreRepository.setDeliveryRegionFor(deliveryRegion, departmentId);
            }
            if (managerId != null) {
                callCentreRepository.setManagerIdFor(managerId, departmentId);
            }
            if (locationId != null) {
                callCentreRepository.setLocationFor(locationId, departmentId);
            }
        }
        return "redirect:/callcentre";
    }
}
