package advisor.app;

import advisor.entitie.Category;
import advisor.entitie.Features;
import advisor.entitie.Playlist;
import advisor.entitie.Release;
import advisor.json.JSONParser;
import java.util.List;
import java.util.Objects;

public class Api {
    private String resource;
    private Authentication authentication;

    final String GET_CATEGORIES = "/v1/browse/categories";
    final String GET_FEATURED_PLAYLISTS = "/v1/browse/featured-playlists";
    final String GET_NEW_RELEASES = "/v1/browse/new-releases";
    final String GET_PLAYLISTS = "/playlists";

    public Api(String resource, Authentication authentication) {
        this.resource = resource;
        this.authentication = authentication;
    }

    public void getAllCategories(List<Category> categories) {
        JSONParser.parseCategories(Objects.requireNonNull(RequestBuilder
                .requestBuild(resource, authentication, GET_CATEGORIES)).body(),
                categories);
    }

    public void getFeaturedPlaylists(List<Features> features) {
        JSONParser.parseFeatures(Objects.requireNonNull(RequestBuilder
                        .requestBuild(resource, authentication, GET_FEATURED_PLAYLISTS)).body(),
                features);
    }

    public void getNewReleases(List<Release> releases) {
        JSONParser.parseNewReleases(Objects.requireNonNull(RequestBuilder
                        .requestBuild(resource, authentication, GET_NEW_RELEASES)).body(),
                releases);
    }


    public void getPlaylists(String categoryId, List<Playlist> playlists) {
        JSONParser.parsePlayLists(Objects.requireNonNull(RequestBuilder
                .requestBuild(resource, authentication, GET_CATEGORIES
                + "/" + categoryId + GET_PLAYLISTS)).body(), playlists);

    }
}
