package Server.REST;
import SOAP.NewOrder;
import SOAP.NewReview;
import SOAP.Orders2Chef;
import models.MenuObject;
import models.OrderObject;
import models.ReviewObject;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
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
    private TreeMap<String, ReviewObject> reviews = new TreeMap<String, ReviewObject>();
    private TreeMap<String, ReviewObject> rew2chef = new TreeMap<String, ReviewObject>();
    private static final ArrayList<OrderObject> orderlist = new ArrayList<>();
    private static final ArrayList<ReviewObject> reviewList = new ArrayList<>();

    private static final String dummmmy = new MenuObject().toJson();
    private static final String dummmmyorder = new OrderObject().toJson();
    private static final String dummmmyrew = new ReviewObject().toJson();
    private static final String ord = new OrderObject().toJson();


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

    //metoder for kunden som laver ny bestilling
    @GetMapping("/order/{ordernumber}")
    public synchronized String getOrder( @PathVariable(value = "ordernumber") String ordernumber ){
        OrderObject order = orders.get( ordernumber );

        if( order == null )
            return ord;
        else
            return order.toJson();
    }

    @RequestMapping("/order/{ordernumber}")
    public synchronized OrderObject putOrder(@RequestBody String json, @PathVariable String ordernumber ) throws MalformedURLException, InterruptedException {
        NewOrder newOrder = new NewOrder();
        OrderObject order = OrderObject.fromJson( json );
        orders.put( Integer.toString(order.getOrderNumber()), order );
        System.out.println("ordernumber bestilt af kunde: "+order.getOrderNumber());

        OrderObject finalOrder = new OrderObject();
        finalOrder.setOrdernumber(order.getOrderNumber());
        finalOrder.setItems(order.getItems());
        finalOrder.setPrice(order.getPrice());
        finalOrder.setAdr(order.getAdr());
        finalOrder.setEmail(order.getEmail());
        finalOrder.setPhone(order.getPhone());
        System.out.println(("#"+finalOrder.getOrderNumber()+"//phone number: "+finalOrder.getPhone()));

        newOrder.newOrder(finalOrder);


        return finalOrder;
    }


    //metoder til chefen
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

        RestClient rc = new RestClient();
        OrderObject ord = OrderObject.fromJson( json );
        orderlist.add(ord);
        for (int xx = 0; xx<orderlist.size(); xx++)
        {
            orderlist.get(xx).setOrdernumber(xx+1);
            System.out.println("adressse af order: "+ orderlist.get(xx).getAdr()+ ", o number af bestilling: "+orderlist.get(xx).getOrderNumber());
            orders2chef.put( Integer.toString(ord.getOrderNumber()), ord );
        }
        System.out.println(orderlist.size());

        return ord;
    }

    //metoder til klienten, modtag review fra klient, send til database
    @GetMapping("/r/{number}")
    public synchronized String getReview( @PathVariable(value = "number") String number ){
        ReviewObject rew = rew2chef.get( number );

        if( rew == null )
            return dummmmy;
        else
            return rew.toJson();
    }

    @RequestMapping("/r/{number}")
    public synchronized ReviewObject putReview(@RequestBody String json, @PathVariable String number ) throws MalformedURLException, InterruptedException {
        ReviewObject review = new ReviewObject();
        NewReview newReview = new NewReview();
        ReviewObject revob = ReviewObject.fromJson( json );
        reviews.put( Integer.toString(revob.getId()), revob );
        System.out.println("review indsendt af kunde: "+revob.getId());

        review.setId(revob.getId());
        review.setName(revob.getName());
        review.setReview(revob.getReview());
        System.out.println(("#"+review.getId()+"//phone number: "+review.getName()));

        newReview.newReview(review);

        return review;
    }





    //metoder til klienten, modtage reviews fra dataabase, send til klient
    @GetMapping("/review/{id}")
    public synchronized String getMSavedRevs( @PathVariable(value = "id") String id ){
        ReviewObject rev = reviews.get( id );

        if( rev == null )
            return dummmmyrew;
        else
            return rev.toJson();
    }

    @RequestMapping("/review/{id}")
    public synchronized ReviewObject putSavedRevs(@RequestBody String json, @PathVariable int id )
    {
        ReviewObject reviewObject = ReviewObject.fromJson( json );
        reviewList.add(reviewObject);
        for (int xx = 0; xx<reviewList.size(); xx++)
        {
            //reviewList.get(xx).setOrdernumber(xx+1);
            System.out.println("review: "+ reviewList.get(xx).getReview()+reviewList.get(xx).getId());

        }rew2chef.put( Integer.toString(reviewObject.getId()), reviewObject );
        System.out.println(orderlist.size());
        return reviewObject;
    }
}
