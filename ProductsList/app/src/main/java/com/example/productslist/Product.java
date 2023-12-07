package com.example.productslist;

public class Product {
    private String Name;
    private String Quantity;
    private String Price;

    public Product(String Name ,String Quantity ,String Price){
        this.Name = Name;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public String getName(){
        return Name;
    }
    public String getQuantity(){
        return Quantity;
    }
    public String getPrice(){
        return Price;
    }
    @Override
    public String toString(){
        return Name + "\n" + Quantity + "\n" + Price;
    }
}
