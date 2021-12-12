package SOAP;

import Server.REST.RestClient;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Reviews2Client {

    public synchronized void Reviews2Client() throws MalformedURLException {
         /*
         1st argument service URI, refer to wsdl document above
        2nd argument is service name, refer to wsdl document above
         port og service links kan ses i den skabte soap server adresse "http://localhost:9999/ws/tst"
         */


        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();
        //------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
        URL urlr = new URL("http://localhost:9990/ws/r");


        //port service for modtage menu fra tier3
        QName portname = new QName("http://soap/", "SendReviewsImplPort");
        QName servicename = new QName("http://soap/", "SendReviewsImplService");
        //brug service
        Service service = Service.create(urlr, servicename);
        SOAP_Interface tst = service.getPort(portname, SOAP_Interface.class);
        for (int x = 0; x<= 3; x++){
            System.out.println(tst);
            RC.storeRevRev(tst.sendReview(x));
        }
    }
}
