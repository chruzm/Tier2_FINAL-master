package SOAP;

import Server.REST.RestClient;
import models.OrderObject;
import models.ReviewObject;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class NewReview  {

    public synchronized void newReview(ReviewObject review) throws MalformedURLException, InterruptedException {
        /*
         1st argument service URI, refer to wsdl document above
        2nd argument is service name, refer to wsdl document above
         port og service links kan ses i den skabte soap server adresse "http://localhost:9999/ws/tst"
         */

        ReviewObject rew = new ReviewObject();

        //rest client til at sende data modtaget fra tier3 til tier2 api
        RestClient RC = new RestClient();
        //------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
        URL urlr = new URL("http://localhost:9990/ws/re");


        //portservice for addOrder til tier3, som så gemmes i database, virker
        QName portnamer = new QName("http://soap/", "AddReviewImplPort");
        QName servicenamer= new QName("http://soap/", "AddReviewImplService");
        //brug service
        Service servicer = Service.create(urlr, servicenamer);
        SOAP_Interface addrew = servicer.getPort(portnamer, SOAP_Interface.class);

        addrew.addReview(review);
    }
}
