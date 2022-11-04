package ca.wonderfish.server.service;

import ca.wonderfish.server.domain.RealEstate;
import ca.wonderfish.server.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RealEstateService implements RealEstateRepository {

    @Autowired
    private RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstate> getRealEstateFromTableA() {
        return realEstateRepository.getRealEstateFromTableA();
    }

    @Override
    public List<RealEstate> getRealEstateFromTableB() {
        return realEstateRepository.getRealEstateFromTableB();
    }
}
