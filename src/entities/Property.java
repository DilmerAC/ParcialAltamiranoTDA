/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Dilmer
 */
public class Property {

    private int id;
    private String name;
    private String address;
    private String state;
    private String characteristics;
    private Double rent_price;

    //Constructors
    public Property() {
    }

    public Property(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Property(int id, String name, String address, String state, String characteristics, Double rent_price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.characteristics = characteristics;
        this.rent_price = rent_price;
    }

    //Getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Double getRentPrice() {
        return rent_price;
    }

    public void setRentPrice(Double rent_price) {
        this.rent_price = rent_price;
    }

    //Method toString
    @Override
    public String toString() {
        return "YourClass{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + address + '\''
                + ", state='" + state + '\''
                + ", characteristics='" + characteristics + '\''
                + ", rent_price=" + rent_price
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.address);
        hash = 67 * hash + Objects.hashCode(this.state);
        hash = 67 * hash + Objects.hashCode(this.characteristics);
        hash = 67 * hash + Objects.hashCode(this.rent_price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Property other = (Property) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }

        if (!Objects.equals(this.characteristics, other.characteristics)) {
            return false;
        }

        if (!Objects.equals(this.rent_price, other.rent_price)) {
            return false;
        }
        return true;
    }

}
