package SOAP;

import models.MenuObject;
import models.OrderObject;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SOAP_Interface {
    //metodenavne her er case-sensitive, navne skal hedde præcist det samme som i tier3 interface

    @WebMethod
    MenuObject getMenu(int x);

    @WebMethod
    OrderObject addOrder(OrderObject o);

    @WebMethod
    OrderObject sendOrder(int a);

    @WebMethod
    int sendAmount();
}
