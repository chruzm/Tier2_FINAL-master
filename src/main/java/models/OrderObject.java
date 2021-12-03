package models;

import com.google.gson.Gson;

public class OrderObject {
    private static final Gson gson = new Gson();
    private int ordernumber;
    private int price;
    private String items;
    private String adr;

    public OrderObject(){
    }

    public OrderObject newOrder(int ordern, int price, String items, String adr){
        OrderObject neworder = new OrderObject();
        neworder.setOrdernumber(ordernumber);
        neworder.setPrice(price);
        neworder.setItems(items);
        neworder.setAdr(adr);
        return neworder;
    }
    public void setOrdernumber( int ordern )
    {
        this.ordernumber = ordern;
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
    public String toJson()
    {
        return gson.toJson( this );
    }
    public static OrderObject fromJson(String json )
    {
        return gson.fromJson( json, OrderObject.class );
    }
}
