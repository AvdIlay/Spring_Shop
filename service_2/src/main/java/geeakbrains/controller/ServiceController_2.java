package geeakbrains.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient("service-1")
public interface ServiceController_2 {

    @GetMapping("/first")
    String firstMethodService();

    @GetMapping("/parameter/{id}")
    String secondMethodService(@PathVariable(value = "id") Integer id);

}
