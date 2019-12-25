package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.model.Car;
import web.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String printCars(ModelMap modelMap) {
        List<Car> cars = carService.getFiveCars();
        modelMap.addAttribute("cars", cars);
        return "cars";
    }

}
