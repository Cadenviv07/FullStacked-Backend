import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;


@Service
public class ExercsieInfoService {

    Gson gson = new Gson();

    @Value("${exercisedb.api.key}")
    private String apiKey;

    @Value("${exercisedb.api.host}")
    private String apiHost;

    public Exercise getExerciseInfo (Exercise exercise){

        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://exercisedb.p.rapidapi.com/exercises/name" + exercise.getName()))
		.header("x-rapidapi-key", "apiKey")
		.header("x-rapidapi-host", "apiHost")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        exercise = gson.fromJson(response, exercise.class);
        return exercise;
    }
}
