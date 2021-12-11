package Server.REST;
import SOAP.NewOrder;
import SOAP.Orders2Chef;
import models.MenuObject;
import models.OrderObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.TreeMap;

@org.springframework.web.bind.annotation.RestController
public class RestController
{
    private RestClient RC = new RestClient();
    private static String x,y;
    private TreeMap<String, MenuObject> foods = new TreeMap<String, MenuObject>();
    private TreeMap<String, OrderObject> orders = new TreeMap<String, OrderObject>();
    private TreeMap<String, OrderObject> orders2chef = new TreeMap<String, OrderObject>();
    private static final ArrayList<OrderObject> orderlist = new ArrayList<>();
    private static final String dummmmy = new MenuObject().toJson();
    private static final String dummmmyorder = new OrderObject().toJson();
    private static final String ord = new OrderObject().toJson();

    /*
    /////*****************************************TESTS
    @GetMapping("/friend/{phoneNo}")
    public synchronized String getFriend( @PathVariable(value = "phoneNo") String phoneNo )
    {
        Test f = friends.get( phoneNo );

        if( f == null )
            return dummy;
        else
            return f.toJson();
    }

    @RequestMapping("/friend/{phoneNo}")
    public synchronized Test putFriend(@RequestBody String json, @PathVariable String phoneNo )
    {
        Test f = Test.fromJson( json );
        friends.put( f.getPhoneNo(), f );

        return f;
    }

    @GetMapping("/lists/{listnumber}")
    public synchronized String getList( @PathVariable(value = "listnumber") String listnumber )
    {
        TestList t = lists.get( listnumber );

        if( t == null )
            return dummmy;
        else
            return t.toJson();
    }
    //********************************************TESTS
     */


    @GetMapping("/menu/{number}")
    public synchronized String getMenuItem( @PathVariable(value = "number") String number ){
        MenuObject food = foods.get( number );

        if( food == null )
            return dummmmy;
        else
            return food.toJson();
    }

    @RequestMapping("/menu/{number}")
    public synchronized MenuObject putMenuItem(@RequestBody String json, @PathVariable String number )
    {

        MenuObject food = MenuObject.fromJson( json );
        foods.put( Integer.toString(food.getNumber()), food );

        return food;
    }

    @GetMapping("/order/{ordernumber}")
    public synchronized String getOrder( @PathVariable(value = "ordernumber") String ordernumber ){
        OrderObject order = orders.get( ordernumber );

        if( order == null )
            return ord;
        else
            return order.toJson();
    }

    @RequestMapping("/order/{ordernumber}")
    public synchronized OrderObject putOrder(@RequestBody String json, @PathVariable String ordernumber )
    {
        Orders2Chef o2c = new Orders2Chef();
        NewOrder newOrder = new NewOrder();
        OrderObject order = OrderObject.fromJson( json );
        orders.put( Integer.toString(order.getOrderNumber()), order );

        OrderObject finalOrder = new OrderObject();
        finalOrder.setItems(order.getItems());
        finalOrder.setPrice(order.getPrice());
        finalOrder.setAdr(order.getAdr());
        System.out.println((finalOrder.getItems()+", pris: "+finalOrder.getPrice()+", til adresse: "+finalOrder.getAdr()));


        return finalOrder;
    }




    @GetMapping("/orders/{number}")
    public synchronized String getOItem( @PathVariable(value = "number") String number ){
        OrderObject ord = orders2chef.get( number );

        if( ord == null )
            return dummmmyorder;
        else
            return ord.toJson();
    }

    @RequestMapping("/orders/{number}")
    public synchronized OrderObject putOItem(@RequestBody String json, @PathVariable String number )
    {

        OrderObject ord = OrderObject.fromJson( json );
        orderlist.add(ord);
        for (int xx = 0; xx<orderlist.size(); xx++)
        {
            orderlist.get(xx).setOrdernumber(xx+1);
            System.out.println("adressse af order: "+ orderlist.get(xx).getAdr()+ ", o number af a: "+orderlist.get(xx).getOrderNumber());
            orders2chef.put( Integer.toString(ord.getOrderNumber()), ord );
        }
        System.out.println(orderlist.size());
        return ord;
    }
}
