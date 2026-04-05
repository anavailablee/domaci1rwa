package http.response;

public class JsonResponse extends Response {

    private final String json;

    public JsonResponse(String json) {
        this.json = json;
    }

    @Override
    public String getResponseString() {
        return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + json;
    }
}
