package org.spotifyautomation.tests;

import io.restassured.response.Response;
import org.spotifyautomation.api.applicationApi.PlaylistApi;
import org.spotifyautomation.pojo.ErrorRoot;
import org.spotifyautomation.pojo.Playlist;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    void shouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New RestAssured playlist")
            .setDescription("New playlist for learning Rest Assured")
            .setPublic(false);

        String userId = "316ugqqwclaof3kscof6ess2o2ve";

        Response response = PlaylistApi.post(requestPlaylist, userId);
        assertThat(response.statusCode(), equalTo(201));

        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    void shouldBeAbleToGetPlaylist() {
        String playlistId = "7yifSZlk96PGP0miWS6RNs";

        Response response = PlaylistApi.get(playlistId);
        assertThat(response.statusCode(), equalTo(200));



        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo("Update Playlist Name"));
        assertThat(responsePlaylist.getPublic(), equalTo(true));
    }

    @Test
    void shouldBeAbleToUpdatePlaylist() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("Update Playlist Name")
            .setDescription("Update playlist description")
            .setPublic(false);

        String playlistId = "7yifSZlk96PGP0miWS6RNs";

        Response response = PlaylistApi.update(playlistId, requestPlaylist);

        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    void shouldNotBeAbleToCreatePlaylistWithEmptyName() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("")
            .setDescription("New playlist for learning Rest Assured")
            .setPublic(false);
        String userId = "316ugqqwclaof3kscof6ess2o2ve";

        Response response = PlaylistApi.post(requestPlaylist, userId);
        assertThat(response.statusCode(), equalTo(400));

        ErrorRoot errorRepsonse =  response.as(ErrorRoot.class);

        assertThat(errorRepsonse.getError().getStatus(), equalTo(400));
        assertThat(errorRepsonse.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    void shouldNotBeAbleToCreatePlaylistWithInvalidToken() {
        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New RestAssured playlist")
            .setDescription("New playlist for learning Rest Assured")
            .setPublic(false);

        String invalidToken = "1234";
        String userId = "316ugqqwclaof3kscof6ess2o2ve";

        Response response = PlaylistApi.post(invalidToken, requestPlaylist, userId);
        assertThat(response.statusCode(), equalTo(401));

        ErrorRoot errorResponse = response.as(ErrorRoot.class);

        assertThat(errorResponse.getError().getStatus(), equalTo(401));
        assertThat(errorResponse.getError().getMessage(), equalTo("Invalid access token"));
    }
}
