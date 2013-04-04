package designpattern.builder;

public class Vehicle {

    private String model;
    private String make;
    private int numberOfWheels;
    private double height;
    private double width;
    private double length;
    private int numberOfDoors;

    private Vehicle(String model, String make, int numberOfWheels, double height, double width, double length, int numberOfDoors) {
        this.model = model;
        this.make = make;
        this.numberOfWheels = numberOfWheels;
        this.height = height;
        this.width = width;
        this.length = length;
        this.numberOfDoors = numberOfDoors;
    }

    public static class Builder {

        private String model = "M5";
        private String make = "BMW";
        private int numberOfWheels = 4;
        private double height = 1.45;
        private double width = 1.9;
        private double length = 4.62;
        private int numberOfDoors = 5;

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withMake(String make) {
            this.make = make;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(model, make, numberOfWheels, height, width, length, numberOfDoors);
        }
    }
}
