package app;

import http.Request;
import http.response.Response;

public interface RequestHandler {
    Response handle(Request request) throws Exception;
}
