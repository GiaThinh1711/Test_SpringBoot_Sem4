package aptech.t2008m.testspringboot.seed;

import aptech.t2008m.testspringboot.entity.District;
import aptech.t2008m.testspringboot.entity.Street;
import aptech.t2008m.testspringboot.entity.enums.StreetStatus;
import aptech.t2008m.testspringboot.service.DistrictService;
import aptech.t2008m.testspringboot.service.StreetService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/seed/generate")
public class generate {
    @Autowired
    DistrictService districtService;
    @Autowired
    StreetService streetService;
    @GetMapping
    public ResponseEntity<String > seeder(){
        Faker faker = new Faker();
        try{
            if (districtService.findAll().isEmpty()){
                List<District> districts = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    districts.add(new District(i,faker.pokemon().name()));
                }
                districtService.saveAll(districts);
            }
            if (streetService.findAll().isEmpty()){
                List<District> districts = districtService.findAll();
                List<Street> streets = new ArrayList<>();
                List<StreetService> enumList= new ArrayList<>();
                for (int i = 0; i <10; i++) {
                    Street street = Street.builder()
                            .createdAt(faker.date().birthday())
                            .description(faker.superhero().descriptor())
                            .districtId(districts.get(faker.random().nextInt(0, districts.size()-1)).getId())
                            .nameStreet(faker.name().lastName())
                            .status(StreetStatus.valueOf(enumList.get(faker.random().nextInt(0,enumList.size()-1))))
                            .build();
                    streets.add(street);
                }
                streetService.saveAll(streets);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Seed fail");
        }
        return ResponseEntity.ok("Seed success");
    }
}
