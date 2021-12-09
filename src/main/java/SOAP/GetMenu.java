package SOAP;

import Server.REST.RestClient;
import Server.SOAP_Interface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMenu {

    public void getMenu() throws MalformedURLException {
        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();

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
