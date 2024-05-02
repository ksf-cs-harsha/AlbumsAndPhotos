package Photography.Photography1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    private RestTemplate restTemplate;

    private final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums";
    private final String PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos";

    @GetMapping("/api/v1/album/{albumId}")
    public ResponseEntity<AlbumWithPhotos> getAlbumWithPhotos(@PathVariable Long albumId) {
        Album album = restTemplate.getForObject(ALBUMS_URL + "/" + albumId, Album.class);
        Photo[] photosArray = restTemplate.getForObject(PHOTOS_URL + "?albumId=" + albumId, Photo[].class);
        List<Photo> photos = Arrays.asList(photosArray);
        AlbumWithPhotos albumWithPhotos = new AlbumWithPhotos();
        albumWithPhotos.setId(album.getId());
        albumWithPhotos.setId(album.getTitle());
        albumWithPhotos.setPhotos(photos);

        return new ResponseEntity<>(albumWithPhotos, HttpStatus.OK);
    }
}
