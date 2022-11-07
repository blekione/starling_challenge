package com.blekione.rest.client;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

public class StarlingApiHeadersFactory implements ClientHeadersFactory {

    @ConfigProperty(name = "rest-client.auth-key")
    String authKey;

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {

        var resultHeaders = new MultivaluedHashMap<String, String>();
        resultHeaders.add("Authorization", "Bearer " + authKey);

        return resultHeaders;
    }

}
