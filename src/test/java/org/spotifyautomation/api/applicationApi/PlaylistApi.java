package org.spotifyautomation.api.applicationApi;

import io.restassured.response.Response;
import org.spotifyautomation.api.RestResource;
import org.spotifyautomation.pojo.Playlist;
public class PlaylistApi {
    static String access_token = "BQAA2p5S6xCWvr489tM4Y5R0diUAFuWgBbK9wzVxtLzbBDodJakB-lGi7vYKBUkEa8tJygWLxDLbsZOa1La98p1kjoA77IgcsKP0XqDgnm9GKnwLI0tGNAEKTiZASc2ehQ6b7diyb4LF8hrDQ6HaZEnXr3i54XczvkfsNpMv3-8SUHtG43-cNTkw4-A3g_JdGEkSy_0F2Fni091GvTG0gUxHZvnQTNidt8XzJTeE3acdjzA0ACwWgToZdcy1GC_WhxdbIgFrDLSVMfGeHDHvT7em";

    public static Response post(Playlist requestPlaylist, String userId) {
        return RestResource.post("/users/" + userId + "/playlists", access_token, requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist, String userId) {
        return RestResource.post("/users/" + userId +  "/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get("/playlists/" + playlistId, access_token);
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return  RestResource.update("/playlists/" + playlistId, access_token, requestPlaylist);
    }
}
