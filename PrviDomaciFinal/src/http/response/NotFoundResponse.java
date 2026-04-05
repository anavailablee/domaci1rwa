package http.response;

public class NotFoundResponse extends Response {

    @Override
    public String getResponseString() {
        return "HTTP/1.1 404 Not Found\r\n\r\n";
    }
}
