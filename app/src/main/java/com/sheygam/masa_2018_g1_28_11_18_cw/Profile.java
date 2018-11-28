package com.sheygam.masa_2018_g1_28_11_18_cw;

public class Profile {
    private String name;
    private String phone;
    private String address;
    private String age;

    public Profile() {
    }

    public Profile(String name, String phone, String address, String age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "," + phone + "," + address + "," + age;
    }

    public static Profile newInstance(String data){
        String[] arr = data.split(",");
        return new Profile(arr[0],arr[1],arr[2],arr[3]);
    }
}
