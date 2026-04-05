package app;

import http.HttpMethod;
import http.Request;
import http.response.NotFoundResponse;
import http.response.Response;

public class QuoteRequestHandler implements RequestHandler {

    @Override
    public Response handle(Request request) throws Exception {
        if (request.getPath().equals("/quotes") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return new QuoteController(request).doGet();
        } else if (request.getPath().equals("/save-quote") && request.getHttpMethod().equals(HttpMethod.POST)) {
            return new QuoteController(request).doPost();
        }

        // browser automatski salje ove zahteve, ignorisemo ih
        return new NotFoundResponse();
    }
}
