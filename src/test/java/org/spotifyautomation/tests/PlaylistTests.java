package org.spotifyautomation.tests;

import io.restassured.response.Response;
import org.spotifyautomation.api.applicationApi.PlaylistApi;
import org.spotifyautomation.pojo.ErrorRoot;
import org.spotifyautomation.pojo.Playlist;
import org.spotifyautomation.utils.AssertionHelper;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    void shouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = Playlist.builder()
                .name("New RestAssured playlist")
                .description("New playlist for learning Rest Assured")
                ._public(false).build();

        String userId = "316ugqqwclaof3kscof6ess2o2ve";

        Response response = PlaylistApi.post(requestPlaylist, userId);
        assertThat(response.statusCode(), equalTo(201));

        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    void shouldBeAbleToGetPlaylist() {
        String playlistId = "7yifSZlk96PGP0miWS6RNs";

        Response response = PlaylistApi.get(playlistId);
        assertThat(response.statusCode(), equalTo(200));



        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo("Update Playlist Name"));
        assertThat(responsePlaylist.get_public(), equalTo(true));
    }

    @Test
    void shouldBeAbleToUpdatePlaylist() {
        Playlist requestPlaylist = Playlist.builder()
                .name("Update Playlist Name")
                .description("Update playlist description")
                ._public(false).build();

        String playlistId = "7yifSZlk96PGP0miWS6RNs";

        Response response = PlaylistApi.update(playlistId, requestPlaylist);

        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    void shouldNotBeAbleToCreatePlaylistWithEmptyName() {
        Playlist requestPlaylist = Playlist.builder()
                .name("")
                .description("New playlist for learning Rest Assured")
                ._public(false).build();
        String userId = "316ugqqwclaof3kscof6ess2o2ve";

        Response response = PlaylistApi.post(requestPlaylist, userId);
        assertThat(response.statusCode(), equalTo(400));

        ErrorRoot errorResponse =  response.as(ErrorRoot.class);
        AssertionHelper.assertErrorMessage(errorResponse, 400, "Missing required field: name");
    }

    @Test
    void shouldNotBeAbleToCreatePlaylistWithInvalidToken() {
     Playlist requestPlaylist = Playlist.builder()
                .name("New RestAssured playlist")
                .description("New playlist for learning Rest Assured")
                ._public(false).build();

        String invalidToken = "1234";
        String userId = "316ugqqwclaof3kscof6ess2o2ve";

        Response response = PlaylistApi.post(invalidToken, requestPlaylist, userId);
        assertThat(response.statusCode(), equalTo(401));

        ErrorRoot errorResponse = response.as(ErrorRoot.class);
        AssertionHelper.assertErrorMessage(errorResponse, 401, "Invalid access token");
    }
}
