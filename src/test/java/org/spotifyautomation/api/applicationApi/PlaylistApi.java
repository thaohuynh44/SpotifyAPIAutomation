package org.spotifyautomation.api.applicationApi;

import io.restassured.response.Response;
import org.spotifyautomation.api.RestResource;
import org.spotifyautomation.pojo.Playlist;

import static org.spotifyautomation.api.Route.PLAYLISTS;
import static org.spotifyautomation.api.Route.USERS;
import static org.spotifyautomation.api.TokenManager.getToken;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist, String userId) {
        return RestResource.post(USERS + "/" + userId + PLAYLISTS, getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist, String userId) {
        return RestResource.post(USERS + "/" + userId + PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return  RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }
}
