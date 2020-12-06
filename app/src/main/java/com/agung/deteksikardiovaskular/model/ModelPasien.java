package com.agung.deteksikardiovaskular.model;

public class ModelPasien {
    private String name, dateBirth, address, noKtp;

    public ModelPasien(String name, String dateBirth, String address, String noKtp) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.address = address;
        this.noKtp = noKtp;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }
}
