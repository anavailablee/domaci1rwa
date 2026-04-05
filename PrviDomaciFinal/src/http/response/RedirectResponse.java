package http.response;

public class RedirectResponse extends Response {

    private final String location;

    public RedirectResponse(String location) {
        this.location = location;
    }

    @Override
    public String getResponseString() {
        return "HTTP/1.1 302 Found\r\nLocation: " + location + "\r\n\r\n";
    }
}
