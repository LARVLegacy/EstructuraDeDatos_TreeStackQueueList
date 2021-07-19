package Proyecto_final;

public class Playback {
    
    private String type; //tipo de la pelicula
    private String title; //titulo de la pelicula
    private String genre; //genero de la pelicula
    private double duration; //duracion de la pelicula
    private int year; //ano de la pelicula
    
    public Playback(){
    }
    
    public Playback(String type, String title, String genre, double duration, int year){
        this.type=type;
        this.title=title;
        this.genre=genre;
        this.duration=duration;
        this.year=year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Playback\n" + "Type= " + type + ", title= " + title + ", genre= " + genre +
                ", duration= " + duration + ", year= " + year;
    }
    
}