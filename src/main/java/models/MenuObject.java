package models;

import com.google.gson.Gson;

public class MenuObject {
    private static final Gson gson = new Gson();
    private int number;
    private int price;
    private String food;

    public MenuObject(){
    }

    public MenuObject newmenuobject(int number,int price, String food){
        MenuObject newmenuo = new MenuObject();
        newmenuo.setNumber(number);
        newmenuo.setPrice(price);
        newmenuo.setFood(food);
        return newmenuo;
    }

    public void setNumber( int number )
    {
        this.number = number;
    }
    public int getNumber( )
    {
        return number;
    }
    public void setPrice( int price )
    {
        this.price = price;
    }
    public void setFood( String food )
    {
        this.food = food;
    }
    public int getPrice()
    {
        return price;
    }
    public String getFood()
    {
        return food;
    }
    public String toJson()
    {
        return gson.toJson( this );
    }
    public static MenuObject fromJson(String json )
    {
        return gson.fromJson( json, MenuObject.class );
    }
}
