package designpattern.builder

import org.junit.Test

class VehicleTest {

    @Test
    public void canBuildADefaultVehicle() {
        def defaultVehicle = Vehicle.Builder.newBuilder().build()
        assert defaultVehicle.model == "M5"
        assert defaultVehicle.make == "BMW"
        assert defaultVehicle.numberOfWheels == 4
        assert defaultVehicle.height == 1.45
        assert defaultVehicle.width == 1.9
        assert defaultVehicle.length == 4.62
        assert defaultVehicle.numberOfDoors == 5
    }

    @Test
    public void canBuildADifferentVehicle() {
        def defaultVehicle = Vehicle.Builder.newBuilder().withModel("M3").build()
        assert defaultVehicle.model == "M3"
        assert defaultVehicle.make == "BMW"
        assert defaultVehicle.numberOfWheels == 4
        assert defaultVehicle.height == 1.45
        assert defaultVehicle.width == 1.9
        assert defaultVehicle.length == 4.62
        assert defaultVehicle.numberOfDoors == 5
    }
}
