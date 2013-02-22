package org.enernoc.oadr2.vtn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.JAXBManager;
import service.oadr.EiEventService;

/**
 * Controller to respond for OADR requests with an XML payload
 * @author jlajoie
 *
 */
public class EiEventController{
    

    static JAXBManager jaxbManager;
    static{
        try {
            jaxbManager = new JAXBManager("org.enernoc.open.oadr2.model");
        } catch (JAXBException e) {
            //Logger.error("Could not initialize JAXBManager in EiEvents", e);
        }
    }
    
    static EiEventService eiEventService = EiEventService.getInstance();

    /**
     * Returns a Result object that will be returned via the PlayFramework
     * containing the payload based upon the incoming HTTP request
     * 
     * @return the Result to be rendered by PlayFramework 
     * @throws JAXBException
     */
    //@Transactional
    def sendHttpResponse() throws JAXBException{
        Unmarshaller unmarshaller = jaxbManager.getContext().createUnmarshaller();
        Object payload = unmarshaller.unmarshal(new ByteArrayInputStream(request().body().asRaw().asBytes()));
        Object eiResponse = eiEventService.handleOadrPayload(payload);
        Marshaller marshaller = jaxbManager.createMarshaller();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(eiResponse, outputStream);
        response().setContentType("application/xml");
        render(outputStream.toByteArray());
    }
    
    
}