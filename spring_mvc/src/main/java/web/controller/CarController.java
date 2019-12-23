package web.controller;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class CarController {

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String printCars(ModelMap modelMap) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        CarService service = context.getBean(CarService.class);
        List<Car> cars = service.getFiveCars();
        modelMap.addAttribute("cars", cars);
        return "cars";
    }

}
