package com.moj.adviser.service.api.controller;


import com.moj.adviser.service.api.domain.Adviser;
import com.moj.adviser.service.api.domain.Category;
import com.moj.adviser.service.api.repository.AdviserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/advisers")
public class AdviserResource {

    @Autowired
    AdviserRepository adviserRepository;


    @GetMapping(value = "/{id}", produces = "application/json")
    public com.moj.adviser.service.api.controller.response.Adviser getAdviser(
            @PathVariable Long id) {
        Adviser adviser = adviserRepository.findById(id).get();
        return mapToAdviserRestResponse(adviser);
    }

    @GetMapping(produces = "application/json")
    public List<com.moj.adviser.service.api.controller.response.Adviser> getAdviserWithTypeAndCategory(
            @RequestParam(required = false, name = "orgType") String orgType,
            @RequestParam(required = false, name = "categoryName") String categoryName) {

        List<Adviser> advisers;

        if (orgType != null && categoryName != null) {
            advisers = adviserRepository.getAllAdviserWithType(orgType, categoryName);
        } else {
            advisers = adviserRepository.findAll();
        }

        List<com.moj.adviser.service.api.controller.response.Adviser> adviserList = new ArrayList<>();

        for (Adviser adviser : advisers) {
            adviserList.add(mapToAdviserRestResponse(adviser));
        }

        return adviserList;
    }

    private com.moj.adviser.service.api.controller.response.Adviser mapToAdviserRestResponse(Adviser adviser) {

        ArrayList<String> categories = new ArrayList<>();

        for (Category category : adviser.getCategory()) {
            categories.add(category.getName());
        }

        com.moj.adviser.service.api.controller.response.Adviser adviser1 = com.moj.adviser.service.api.controller.response.Adviser.builder()
                .adviserId(adviser.getId().toString())
                .name(adviser.getName())
                .address(adviser.getAddress1() + ", " + adviser.getCity() + ", " + adviser.getPostcode())
                .contactNumber(adviser.getPhone())
                .organizationType(adviser.getOrgType())
                .categories(categories)
                .build();

        return adviser1;
    }
}
