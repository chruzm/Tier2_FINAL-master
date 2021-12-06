package Server.REST;

import com.google.gson.Gson;
import models.MenuObject;
import models.OrderObject;
import models.Test;
import models.TestList;
import org.springframework.core.annotation.Order;
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

    private void run()
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

    /*
    //*************************************TESTS
    public Test fetchFriend(String phoneNo )
    {
        return Test.fromJson( rest.getForObject( ROOT + "friend/" + phoneNo, String.class ) );
    }
    public void storeFriend( Test f )
    {
        rest.put( ROOT + "friend/" + f.getPhoneNo(), f.toJson() );
    }
    //metode for at gemme testlist fra tier3 database, til tier2 restAPI
    public void storeTestList(TestList s){rest.put(ROOT +"lists/"+s.getListnumber(), s.toJson());}
    //************************************TESTS
     */

    //gem menu object modtaget fra tier3(som er modtaget fra database)
    public void storeMenu(MenuObject s){rest.put(ROOT +"menu/"+s.getNumber(), s.toJson());}

    public MenuObject fetchMenuItem(int itemnumber )
    {
        return MenuObject.fromJson( rest.getForObject( ROOT + "menu/" + itemnumber, String.class ) );
    }

    //gem og modtage orders
    public void storeOrder(OrderObject s){rest.put(ROOT +"order/"+s.getOrderNumber(), s.toJson());}


    public OrderObject fetchOrder(int ordernumber )
    {
        return OrderObject.fromJson( rest.getForObject( ROOT + "order/" + ordernumber, String.class ) );
    }



    //modtage fra db, send til chef
    public void storeO(OrderObject s){rest.put(ROOT +"orders/"+s.getOrderNumber(), s.toJson());}

    public OrderObject fetchOItem(int ordernumber )
    {
        return OrderObject.fromJson( rest.getForObject( ROOT + "orders/" + ordernumber, String.class ) );
    }


}
