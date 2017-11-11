package com.bluebik.car.musclecar;

import java.util.List;
import java.util.Map;
import com.bluebik.car.musclecar.*;

/**
 * Copyright © 2016 Bluebik Group.
 * Created by khakhanat on 24/10/2017 AD.
 */
public interface MuscleCarDao {

    public MuscleCar getCarFromList(int id);

    public void removeCarFromList(int id);

    public void addCarToList(MuscleCar muscleCar);

    public void updateCarFromList(int id, MuscleCar muscleCar);

    public List<Map<String, Object>> listAllCars();

}
