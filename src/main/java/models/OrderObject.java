package models;

import com.google.gson.Gson;

public class OrderObject {
    private static final Gson gson = new Gson();
    private int ordernumber;
    private int price;
    private String items;
    private String adr;
    private String email;
    private String phone;

    public OrderObject(){
    }

    public OrderObject newOrder(int ordernumber, int price, String items, String adr, String email, String phone){
        OrderObject neworder = new OrderObject();
        neworder.setOrdernumber(ordernumber);
        neworder.setPrice(price);
        neworder.setItems(items);
        neworder.setAdr(adr);
        return neworder;
    }
    public void setOrdernumber( int ordernumber )
    {
        this.ordernumber = ordernumber;
    }
    public void setPrice( int price )
    {
        this.price = price;
    }
    public void setItems( String items )
    {
        this.items = items;
    }
    public void setAdr( String adr )
    {
        this.adr = adr;
    }
    public void setEmail( String email )
    {
        this.email = email;
    }
    public void setPhone( String phone )
    {
        this.phone = phone;
    }
    public int getPrice()
    {
        return price;
    }
    public int getOrderNumber()
    {
        return ordernumber;
    }
    public String getItems()
    {
        return items;
    }
    public String getAdr()
    {
        return adr;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhone()
    {
        return phone;
    }
    public String toJson()
    {
        return gson.toJson( this );
    }
    public static OrderObject fromJson(String json )
    {
        return gson.fromJson( json, OrderObject.class );
    }
}
