package geekbrains.album;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;



@Data
@JsonAutoDetect
public class Music {
    private String nameSign;
    private String time;

    public Music() {}

    public Music(String nameSign, String time) {
        this.nameSign = nameSign;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Имя песни: " + nameSign + " | "+ " Продолжительность: " + time;
    }
}

