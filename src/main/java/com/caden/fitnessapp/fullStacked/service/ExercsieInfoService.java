import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Servcice
public class ExercsieInfoService {
    
    public String getExerciseInfo (String exerciseName){
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://exercisedb.p.rapidapi.com/exercises/targetList"))
		.header("x-rapidapi-key", "0e9ed6b719msh6e45e95521e2dfcp18c26djsn0630a3e0a192")
		.header("x-rapidapi-host", "exercisedb.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
