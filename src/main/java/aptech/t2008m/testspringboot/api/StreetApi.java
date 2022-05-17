package aptech.t2008m.testspringboot.api;

import aptech.t2008m.testspringboot.entity.Street;
import aptech.t2008m.testspringboot.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/streets")
public class StreetApi {
    @Autowired
    StreetService streetService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Street> getList() {
        return streetService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/search")
    public List<Street> search(@RequestParam(defaultValue = "")String name,@RequestParam(defaultValue = "")int districtId) {
        return streetService.findByStreetAnDistrict(name, districtId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Street save(@RequestBody Street street) {
        streetService.save(street);
        return street;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Street getDetail(@PathVariable int id) {
        return streetService.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public boolean delete(@PathVariable int id) {
        streetService.deleteById(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public Street update(@PathVariable int id, @RequestBody Street updateStreet) {
        Street existing = streetService.findById(id).get();
        existing.setNameStreet(updateStreet.getNameStreet());
        existing.setDistrictId(updateStreet.getDistrictId());
        existing.setDescription(updateStreet.getDescription());
        existing.setStatus(updateStreet.getStatus());
        streetService.save(existing);
        return updateStreet;
    }
}
