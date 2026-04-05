package app;

import com.google.gson.Gson;
import http.Request;
import http.response.JsonResponse;
import http.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuoteOfTheDayController extends Controller {

    private static final List<Quote> QUOTES = Arrays.asList(
            new Quote("Be yourself; everyone else is already taken.", "Oscar Wilde"),
            new Quote("In the middle of every difficulty lies opportunity.", "Albert Einstein"),
            new Quote("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
            new Quote("Life is what happens when you're busy making other plans.", "John Lennon"),
            new Quote("The only way to do great work is to love what you do.", "Steve Jobs")
    );

    public QuoteOfTheDayController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        Quote quote = QUOTES.get(new Random().nextInt(QUOTES.size()));
        return new JsonResponse(new Gson().toJson(quote));
    }

    @Override
    public Response doPost() {
        return null;
    }
}
