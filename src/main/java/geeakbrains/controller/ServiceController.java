package geeakbrains.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ServiceController {

    @GetMapping("/first")
    String firstMethodService();

    @GetMapping("/parameter/{id}")
    String secondMethodService(@PathVariable(value = "id") Integer id);
}
