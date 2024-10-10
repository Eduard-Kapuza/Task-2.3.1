package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String listCarsByLimit(@RequestParam(value = "count", required = false, defaultValue = "5") int amount,
                                  Model model) {
        model.addAttribute("listCars", carService.getListCarLimit(amount));
        return "cars";
    }

    @GetMapping("/{id}")
    public String carById(@PathVariable("id") int id, Model model) {
        model.addAttribute("getCarById", carService.getCarById(id));
        return "carById";
    }

    @GetMapping("/getAmountCars")
    public String showAmountCars(ModelMap model) {
        model.addAttribute("messages", carService.showAmountCars());
        return "getAmountCars";
    }

    @GetMapping("/newCar")
    public String addCar(@ModelAttribute("car") Car car
//                         @RequestParam(value = "model", required = false) String modelCar,
//                         @RequestParam(value = "series", required = false) int series,
//                         @RequestParam(value = "color", required = false) String color,
//                         ,ModelMap model
    ) {

//        Car car = new Car(modelCar, series, color);
//        carService.saveCar(car);
//        model.addAttribute("car", car);
        return "/newCar";
    }

    @GetMapping("/getListCars")
    public String getListCars(ModelMap model) {
        model.addAttribute("listCars", carService.getListCar());
        return "listCars";
    }
}