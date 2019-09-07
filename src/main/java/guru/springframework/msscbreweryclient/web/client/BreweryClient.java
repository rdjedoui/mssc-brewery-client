package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.apihost", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;

    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerByid(UUID uuid) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid, BeerDto.class);
    }

    public URI SaveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + uuid, beerDto);
    }

    public void deleteBeerById(UUID uuid) {
        restTemplate.delete(apihost + BEER_PATH_V1 + uuid);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
