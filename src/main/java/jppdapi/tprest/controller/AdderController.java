package jppdapi.tprest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jppdapi.tprest.service.AdderService;

@RequestMapping(value = "/adder", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AdderController {

    private final AdderService adderService;

    public AdderController(AdderService adderService) {
        this.adderService = adderService;
    }

    @GetMapping("/current")
    public int currentNum() {
        return adderService.currentBase();
    }

    @PostMapping
    public int add(@RequestBody int num) {
        return adderService.add(num);
    }

    @PostMapping("/acc")
    public int acc(@RequestBody int num) {
        return adderService.accumulate(num);
    }
}
