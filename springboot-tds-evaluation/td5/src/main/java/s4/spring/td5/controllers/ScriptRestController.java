package s4.spring.td5.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.ScriptRepository;
import s4.spring.td5.entities.Search;

/**
 * ScriptRestController
 */
@RestController
public class ScriptRestController {

    @Autowired
    private ScriptRepository scriptRepo;

    @PostMapping(value="/rest/script", consumes="application/json",produces="application/json")
    public ArrayList<Script> getScripts(@RequestBody Search search){

        String id = search.getId();
        String s = search.getSearch();

        switch (id) {
            case "1":
                
                return scriptRepo.searchScripstByTitle(s);
            case "2":

                return scriptRepo.searchScripstByContent(s);
            case "3":

                return scriptRepo.searchScripstByDescription(s);
            case "4":

                return scriptRepo.searchScripstByName(s);

            case "5":

                return scriptRepo.searchScripstByCategory(s);

            default:
                return null;
        }
    }


    
}