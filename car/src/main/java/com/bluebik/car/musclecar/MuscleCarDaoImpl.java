package com.bluebik.car.musclecar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bluebik.car.musclecar.*;

import org.springframework.stereotype.Repository;

/**
 * Copyright © 2016 Bluebik Group.
 * Created by khakhanat on 24/10/2017 AD.
 */

@Transactional
@Repository
public class MuscleCarDaoImpl implements MuscleCarDao {
	
	@PersistenceContext
	private EntityManager em;
    public MuscleCarDaoImpl() {
    	
    }

    @Override
    public MuscleCar getCarFromList(int id) {
    	MuscleCar car = null;
    	for (Map<String, Object> map : listAllCars()) {
    	    for (Map.Entry<String, Object> entry : map.entrySet()) {
    	    	if(Integer.parseInt(entry.getKey()) == id) {
    	    		car = (MuscleCar) entry.getValue();
    	    	}
    	    }
    	}
        return car;
    }

    @Override
    public void removeCarFromList(int id) {
    	MuscleCar car = null;
    	for (Map<String, Object> map : listAllCars()) {
    	    for (Map.Entry<String, Object> entry : map.entrySet()) {
    	    	if(Integer.parseInt(entry.getKey()) == id) {
    	    		listAllCars().remove(id);
    	    	}
    	    }
    	}
    }

    @Override
    public void addCarToList(MuscleCar muscleCar) {
    	if(muscleCar == null) return;
    	em.persist(muscleCar);
    }

    @Override
    public void updateCarFromList(int id, MuscleCar muscleCar) {
    	MuscleCar mc = getCarFromList(id);
    	if(mc == null) return;
    	mc.setCarBrand(muscleCar.getCarBrand());
    	mc.setCarEngine(muscleCar.getCarEngine());
    	mc.setCarModel(muscleCar.getCarModel());
    	mc.setHorsepower(muscleCar.getHorsepower());
		em.flush();
    }

    @Override
    public List<Map<String, Object>> listAllCars() {
    	String sql = "FROM MUSCLE_CAR as mc ORDER BY mc.carBrand";
    	 List<MuscleCar> car =   em.createQuery(sql).getResultList();
    	 List listAllCar = new ArrayList<HashMap<String,MuscleCar>>();
    	 Map<String,MuscleCar> m = new HashMap<>();
	    	 for(MuscleCar c : car) {
	    		 m.put(c.getId()+"", c);
	    	 }
    	 listAllCar.add(m);
		return  listAllCar;
    }

}
