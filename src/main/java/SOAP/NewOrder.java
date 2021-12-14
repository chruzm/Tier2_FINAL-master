package SOAP;

import Server.REST.RestClient;
import models.OrderObject;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class NewOrder {

    public synchronized void newOrder(OrderObject ord) throws MalformedURLException, InterruptedException {
        /*
         1st argument service URI, refer to wsdl document above
        2nd argument is service name, refer to wsdl document above
         port og service links kan ses i den skabte soap server adresse "http://localhost:9999/ws/tst"
         */

        Orders2Chef o2c = new Orders2Chef();

        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();
        //------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
        URL urlorder = new URL("http://localhost:9990/ws/addorder");


        //portservice for addOrder til tier3, som så gemmes i database, virker
		QName portnameorder = new QName("http://soap/", "AddOrderImplPort");
		QName servicenameorder = new QName("http://soap/", "AddOrderImplService");
		//brug service
		Service serviceorder = Service.create(urlorder, servicenameorder);
		SOAP_Interface addorder = serviceorder.getPort(portnameorder, SOAP_Interface.class);


        addorder.addOrder(ord);
        RC.storeO(ord);
    }
}
