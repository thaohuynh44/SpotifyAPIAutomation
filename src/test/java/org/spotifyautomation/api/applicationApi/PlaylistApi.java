package org.spotifyautomation.api.applicationApi;

import io.restassured.response.Response;
import org.spotifyautomation.api.RestResource;
import org.spotifyautomation.pojo.Playlist;

import static org.spotifyautomation.api.TokenManager.getToken;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist, String userId) {
        return RestResource.post("/users/" + userId + "/playlists", getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist, String userId) {
        return RestResource.post("/users/" + userId +  "/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get("/playlists/" + playlistId, getToken());
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return  RestResource.update("/playlists/" + playlistId, getToken(), requestPlaylist);
    }
}
