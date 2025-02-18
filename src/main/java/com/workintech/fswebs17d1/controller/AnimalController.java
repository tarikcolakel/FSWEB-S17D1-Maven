package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private final Map<Integer, Animal> animals = new HashMap<>();

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    // 📌 Tüm hayvanları getir
    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    // 📌 Belirli bir hayvanı ID'ye göre getir
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animals.get(id);
    }

    // 📌 Yeni bir hayvan ekle (RequestBody ile)
    @PostMapping
    public Animal addAnimal(@RequestBody Animal newAnimal) {
        animals.put(newAnimal.getId(), newAnimal);
        return newAnimal;
    }

    // 📌 Belirli bir hayvanı güncelle (RequestBody ile)
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        if (animals.containsKey(id)) {
            animals.put(id, updatedAnimal);
            return updatedAnimal;
        }
        return null;
    }

    // 📌 Belirli bir hayvanı sil
    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable int id) {
        if (animals.containsKey(id)) {
            animals.remove(id);
            return "Animal deleted successfully!";
        }
        return "Animal not found!";
    }

    // 📌 @Value ile properties değişkenlerini döndür
    @GetMapping("/info")
    public Map<String, String> getCourseInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("Course Name", courseName);
        info.put("Developer Name", developerName);
        return info;
    }
}
