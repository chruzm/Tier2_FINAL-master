package SOAP;

import Server.REST.RestClient;
import Server.SOAP_Interface;
import models.OrderObject;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class NewOrder {

    public void newOrder(OrderObject ord) throws MalformedURLException {
        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();

        URL urlorder = new URL("http://localhost:9990/ws/addorder");


        //portservice for addOrder til tier3, som så gemmes i database, virker
		QName portnameorder = new QName("http://soap/", "AddOrderImplPort");
		QName servicenameorder = new QName("http://soap/", "AddOrderImplService");
		//brug service
		Service serviceorder = Service.create(urlorder, servicenameorder);
		SOAP_Interface addorder = serviceorder.getPort(portnameorder, SOAP_Interface.class);

        addorder.addOrder(ord);
    }
}
