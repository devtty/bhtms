package com.devtty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


@Path("/next")
public class AbWandlitzResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

	String ret = "";
	try{
	    Document doc = Jsoup.connect("https://www.lb-neb.de/de/app/webtools/trains.widget?action=departure&stop=1510837020596").get();
	    Element firTime = doc.select(".ids-table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)").first();
	    Element firDire = doc.select(".ids-table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3) > strong:nth-child(3)").first();
	    Element secTime = doc.select(".ids-table > tbody:nth-child(2) > tr:nth-child(2) > td:nth-child(2)").first();
	    ret = "naechste Abfahrt um " + firTime.text() + " nach " + firDire.text();
	}catch(Exception e){
	    ret = "error" + e.getMessage();
	}

        return ret;
    }
}
