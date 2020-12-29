package geekbrains.album;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonAutoDetect
public class Singers {
    private String name;
    private String lastname;
    private String birthDay;
    private List<Music> musicTitles;

    private Album album;



    public Singers(String name, String lastname, String birthDay, Album album){
        this.name = name;
        this.lastname = lastname;
        this.birthDay = birthDay;
        this.musicTitles = new ArrayList<>();
        this.album =  album;

    }

    public void addMusic(Music music) { this.musicTitles.add(music);}


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Имя: ").append(name).append("\n")
                .append("Фамилия: ").append(lastname).append("\n")
                .append("Дата рождения: ").append(birthDay).append("\n")
                .append(album.toString()).append("\n");
        for (Music music : musicTitles) {
            stringBuilder.append("    ").append(music.toString()).append("\n");
        }
        return stringBuilder.toString();
    }


}

