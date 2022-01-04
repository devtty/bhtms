package com.devtty;

import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.openshift.api.model.Route;
import io.fabric8.openshift.client.OpenShiftClient;
import io.quarkus.runtime.StartupEvent;
import javax.json.Json;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.quarkus.scheduler.Scheduled;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;


@ApplicationScoped
public class LaMetricPushResource {

    private static final String LAMETRIC_DEV_CON = "https://developer.lametric.com";
    private static final String LAMETRIC_WIDGET_URI = "api/V1/dev/widget/update/com.lametric.20d9fabf0b232dc145b0b82d9deb8ea9/1 ";

    @Inject OpenShiftClient openshiftClient;

    String host = null;
    
    void onStart(@Observes StartupEvent ev){
	LabelSelector selector = new LabelSelectorBuilder().withMatchLabels(Map.ofEntries(entry("endpoint", "client"))).build();

	List<Route> routes = openshiftClient.routes().withLabelSelector(selector).list().getItems();

	Route route = routes.get(0);
        
        host = route.getSpec().getHost();

    }
    
    @Scheduled(every="300s")
    void push() {	
	
        try {
            URL url = new URL("http://" + host);
            Scanner s = new Scanner(url.openStream());
            String message = s.nextLine();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LaMetricPushResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LaMetricPushResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	JsonObject message = Json.createObjectBuilder()
	    .add("frames", Json.createObjectBuilder()
		 .add("index", 0)
		 .add("text", messageText)
		 .add("icon", "i1347")
		 )
	    .build();

	Client restClient = new ResteasyClientBuilder().build();      
        
        WebTarget target = restClient.target(LAMETRIC_DEV_CON).path(LAMETRIC_WIDGET_URI);
        
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);

        Response response = target.request().accept(MediaType.APPLICATION_JSON).cacheControl(cacheControl).header("X-Access-Token", accessToken).post(Entity.json(message));
    }
}
