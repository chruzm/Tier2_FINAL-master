package SOAP;

import Server.REST.RestClient;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Orders2Chef {

    public synchronized void send2Chef() throws MalformedURLException {
        /*
         1st argument service URI, refer to wsdl document above
        2nd argument is service name, refer to wsdl document above
         port og service links kan ses i den skabte soap server adresse "http://localhost:9999/ws/tst"
         */

        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();
        //------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
        URL urlorder = new URL("http://localhost:9990/ws/orders");
        URL urla = new URL("http://localhost:9990/ws/amount");


        //port service for modtage AMOUNT of orders fra tier3. int fra denne service bruges i loop som sender orders til chefen
        QName portnamea = new QName("http://soap/", "SendAmountImplPort");
        QName servicenamea = new QName("http://soap/", "SendAmountImplService");
        //brug service
        Service servicea = Service.create(urla, servicenamea);
        SOAP_Interface tsta = servicea.getPort(portnamea, SOAP_Interface.class);
        System.out.println("size of list to chef: "+tsta.sendAmount());


        //port service for modtage orders fra tier3 som sendes til chefen
        QName portnameorder = new QName("http://soap/", "SendOrderImplPort");
        QName servicenameorder = new QName("http://soap/", "SendOrderImplService");
        //brug service
        Service serviceorder = Service.create(urlorder, servicenameorder);
        SOAP_Interface tstorder = serviceorder.getPort(portnameorder, SOAP_Interface.class);
        for (int x = 0; x<= tsta.sendAmount()-2; x++){
            System.out.println();
            RC.storeO(tstorder.sendOrder(x));
        }
        System.out.println("------------------");
        System.out.println(tstorder.sendOrder(tsta.sendAmount()-1).getAdr());
        RC.storeO(tstorder.sendOrder(tsta.sendAmount()-1));
    }
}
