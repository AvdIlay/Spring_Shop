package geekbrains.album;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;


@Data
@JsonAutoDetect
public class Album extends Music {
    private String nameAlbum;
    private String dataRelease;
    private Integer quantity;


    public Album() {}

    public Album(String nameAlbum, Integer quantity,String dataRelease) {
        this.nameAlbum = nameAlbum;
        this.quantity = quantity;
        this.dataRelease = dataRelease;

    }
    @Override
    public String toString() {
        return "Название альбома: " + nameAlbum + " | " + "Колличество песен: " + quantity + " | " + "Дата выхода:" + dataRelease;
    }
}