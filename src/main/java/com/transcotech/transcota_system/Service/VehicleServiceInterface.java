/*package com.transcotech.transcota_system.Service;

import java.util.List;

import com.transcotech.transcota_system.dto.VehicleDTO;
import com.transcotech.transcota_system.model.Vehicle;

public interface VehicleServiceInterface {

    List<Vehicle> findAll();

    VehicleDTO searchId(Long id);

    boolean deleteVehicle(Long id);

    boolean createVehicle(Vehicle vehicle);

    boolean updateVehicle(Long id, Vehicle vehicle);
}*/
// VehicleServiceInterface.java
package com.transcotech.transcota_system.Service;

import java.util.List;
import com.transcotech.transcota_system.model.Vehicle;

public interface VehicleServiceInterface {
    List<Vehicle> findAll();
    Vehicle searchId(Long id);
    boolean deleteVehicle(Long id);
    boolean createVehicle(Vehicle vehicle);
    boolean updateVehicle(Long id, Vehicle vehicle);
}
