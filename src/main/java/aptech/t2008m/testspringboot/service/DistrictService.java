package aptech.t2008m.testspringboot.service;

import aptech.t2008m.testspringboot.entity.District;
import aptech.t2008m.testspringboot.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    public List<District> findAll(){

        return districtRepository.findAll();
    }
    public List<District> saveAll(List<District> districts){
        return districtRepository.saveAll(districts);
    }
}
