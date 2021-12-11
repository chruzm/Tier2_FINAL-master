package SOAP;

import Server.REST.RestClient;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMenu {

    public synchronized void getMenu() throws MalformedURLException {
        /*
         1st argument service URI, refer to wsdl document above
        2nd argument is service name, refer to wsdl document above
         port og service links kan ses i den skabte soap server adresse "http://localhost:9999/ws/tst"
         */


        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();
        //------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
        URL urlmenu = new URL("http://localhost:9990/ws/getmenu");

        //port service for modtage menu fra tier3
        QName portnamemenu = new QName("http://soap/", "GetMenuImplPort");
        QName servicenamemenu = new QName("http://soap/", "GetMenuImplService");
        //brug service
        Service servicemenu = Service.create(urlmenu, servicenamemenu);
        SOAP_Interface tstmenu = servicemenu.getPort(portnamemenu, SOAP_Interface.class);
        for (int x = 0; x<= 4; x++){
            System.out.println(tstmenu.getMenu(x).getFood());
            RC.storeMenu(tstmenu.getMenu(x));
        }
    }
}
