package geeakbrains.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public  class ServiceControllerImpl_2  {

    private ServiceController_2 serviceController2;



    @Autowired
    public void setServiceController_2(ServiceController_2 serviceController_2) {
        this.serviceController2 = serviceController_2;
    }


    @GetMapping("/first")
    public String firstMethodService2() {
        String string = "Первый  метод во втором сервие!\n" +
                serviceController2.firstMethodService2();
        return string;
    }



    @ResponseBody
    @GetMapping("/parameter/{id}")
    public String secondMethodService2(@PathVariable String id) {
        return String.format("Второй метод второго сервиса! #%s" +
                serviceController2.secondMethodService2(1), id);
    }

    @GetMapping("/test")
    public String test(Model model) {
        String s = "Первый тестовый  метод во втором сервие! \n" +
                serviceController2.firstMethodService2();
        model.addAttribute("test", s);
        return "greeting-view";
    }


}
