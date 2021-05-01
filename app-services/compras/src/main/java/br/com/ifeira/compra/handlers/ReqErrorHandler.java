package br.com.ifeira.compra.handlers;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class ReqErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

//        System.out.println(response.getBody().READ);
//
//        StringWriter writer = new StringWriter();
//        IOUtils.copy(response.getBody(),  "UTF-8");
    }
}
