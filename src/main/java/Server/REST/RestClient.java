package Server.REST;

import com.google.gson.Gson;
import models.MenuObject;
import models.OrderObject;
import models.ReviewObject;
import org.springframework.web.client.RestTemplate;


public class RestClient
{
    //adresse af denne server 'Tier2 Logic Server
    private static final String ROOT = "http://localhost:8080/";
    private static final Gson gson = new Gson();

    private RestTemplate rest = new RestTemplate();

    public static void main( String[] args )
    {
        new RestClient().run();
    }

    private String toJson(String s){
        return gson.toJson( this );
    }

    private synchronized void run()
    {
        //storeFriend( new Test( "42", "Troels" ) );
        //storeFriend( new Test( "1111", "Line" ) );
        //storeFriend( new Test( "3211", "Povl" ) );
        //storeFriend( new Test( "4711", "Jan" ) );

        //Test f = fetchFriend( "" );
        //System.out.println( f.getPhoneNo() + " " + f.getName() );

        //deleteFriend( "4711" );

        //nameConcat( "42", " Mortensen" );
    }

    //gem menu object modtaget fra tier3(som er modtaget fra database)
    public synchronized void storeMenu(MenuObject s){rest.put(ROOT +"menu/"+s.getNumber(), s.toJson());}

    public synchronized MenuObject fetchMenuItem(int itemnumber )
    {
        return MenuObject.fromJson( rest.getForObject( ROOT + "menu/" + itemnumber, String.class ) );
    }

    //gem og modtage orders
    public synchronized void storeOrder(OrderObject s){rest.put(ROOT +"order/"+s.getOrderNumber(), s.toJson());}


    public synchronized OrderObject fetchOrder(int ordernumber )
    {
        return OrderObject.fromJson( rest.getForObject( ROOT + "order/" + ordernumber, String.class ) );
    }


    //modtage fra db, send til chef
    public synchronized void storeO(OrderObject s){rest.put(ROOT +"orders/"+s.getOrderNumber(), s.toJson());}

    public OrderObject fetchOItem(int ordernumber )
    {
        return OrderObject.fromJson( rest.getForObject( ROOT + "orders/" + ordernumber, String.class ) );
    }

    //modtage fra klient, send til database
    public synchronized void storeR(ReviewObject s){rest.put(ROOT +"r/"+s.getId(), s.toJson());}
    public ReviewObject fetchReview(int id )
    {
        return ReviewObject.fromJson( rest.getForObject( ROOT + "r/" + id, String.class ) );
    }



    //modtage fra database, send til klient
    public synchronized void storeRevRev(ReviewObject s){rest.put(ROOT +"review/"+s.getId(), s.toJson());}

    public ReviewObject fetchRevRev(int id )
    {
        return ReviewObject.fromJson( rest.getForObject( ROOT + "review/" + id, String.class ) );
    }



}
