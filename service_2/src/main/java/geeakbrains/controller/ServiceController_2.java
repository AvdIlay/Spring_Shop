package geeakbrains.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository

@FeignClient("service_1")
public interface ServiceController_2 {
    @GetMapping("/first")
    String firstMethodService2();

    @GetMapping("/parameter/{id}")
    String secondMethodService2(@PathVariable(value = "id") Integer id);
}
