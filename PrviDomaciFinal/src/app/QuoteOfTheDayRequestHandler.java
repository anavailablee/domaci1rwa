package app;

import http.HttpMethod;
import http.Request;
import http.response.Response;

public class QuoteOfTheDayRequestHandler implements RequestHandler {

    @Override
    public Response handle(Request request) throws Exception {
        if (request.getPath().equals("/quote-of-the-day") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return new QuoteOfTheDayController(request).doGet();
        }

        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
