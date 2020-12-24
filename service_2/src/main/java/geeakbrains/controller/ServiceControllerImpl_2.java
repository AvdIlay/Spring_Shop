package geeakbrains.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public  class ServiceControllerImpl_2  {

    private ServiceController_2 serviceController2;

        @Autowired
      public void setServiceController_2(ServiceController_2 serviceController2){
       this.serviceController2 = serviceController2;
   }

    @ResponseBody
    @GetMapping("/first")
    public String firstMethodMicroserviceSecond() {
        String string = "Первый  метод во втором сервие!\n" +
                serviceController2.firstMethodService();
        return string;
    }

    @GetMapping("/test")
    public String test(Model model) {
        String string = "Первый тестовый  метод во втором сервие\n" +
                serviceController2.firstMethodService();
        model.addAttribute("test", string);
        return "greeting-view";
    }


    @ResponseBody
    @GetMapping("/parameter/{id}")
    public String secondMethodMicroserviceSecond(@PathVariable String id) {
        return String.format("Второй метод второго сервиса! #%s" +
                serviceController2.secondMethodService(1), id);
    }

}
