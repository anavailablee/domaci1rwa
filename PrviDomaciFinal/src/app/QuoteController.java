package app;

import com.google.gson.Gson;
import http.Request;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class QuoteController extends Controller {

    private static final List<Quote> savedQuotes = new ArrayList<>();
    private static final int HELPER_PORT = 8081;

    public QuoteController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        String quoteOfTheDay = fetchQuoteOfTheDay();

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Quotes</title></head><body>");

        html.append("<h2>Citat dana</h2>");
        html.append("<p><em>").append(quoteOfTheDay).append("</em></p>");

        html.append("<h2>Unesi novi citat</h2>");
        html.append("<form method=\"POST\" action=\"/save-quote\">");
        html.append("<label>Citat: </label><input name=\"text\" type=\"text\"><br><br>");
        html.append("<label>Autor: </label><input name=\"author\" type=\"text\"><br><br>");
        html.append("<button type=\"submit\">Save Quote</button>");
        html.append("</form>");

        html.append("<h2>Sacuvani citati</h2>");
        if (savedQuotes.isEmpty()) {
            html.append("<p>Nema sacuvanih citata.</p>");
        } else {
            html.append("<ul>");
            for (Quote q : savedQuotes) {
                html.append("<li>\"").append(q.getText()).append("\" - ").append(q.getAuthor()).append("</li>");
            }
            html.append("</ul>");
        }

        html.append("</body></html>");

        return new HtmlResponse(html.toString());
    }

    @Override
    public Response doPost() {
        String body = request.getBody();
        String text = "";
        String author = "";

        if (body != null) {
            for (String param : body.split("&")) {
                String[] kv = param.split("=", 2);
                if (kv.length == 2) {
                    try {
                        String key = URLDecoder.decode(kv[0], "UTF-8");
                        String value = URLDecoder.decode(kv[1], "UTF-8");
                        if (key.equals("text")) text = value;
                        if (key.equals("author")) author = value;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        savedQuotes.add(new Quote(text, author));
        return new RedirectResponse("/quotes");
    }

    private String fetchQuoteOfTheDay() {
        try {
            Socket socket = new Socket("localhost", HELPER_PORT);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.print("GET /quote-of-the-day HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n");
            out.flush();

            // preskoci headere
            String line;
            while ((line = in.readLine()) != null && !line.trim().equals("")) { }

            // procitaj JSON
            String jsonBody = in.readLine();

            in.close();
            out.close();
            socket.close();

            if (jsonBody != null && !jsonBody.trim().isEmpty()) {
                Gson gson = new Gson();
                Quote q = gson.fromJson(jsonBody, Quote.class);
                return "\"" + q.getText() + "\" - " + q.getAuthor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Nije moguce ucitati citat dana.";
    }
}
