
package Proyecto_final;

public class Profiles {
    
    private String name; //nombre
    private String language; //Lenguaje
    private String configuration; //configuracion por edad
    private Stack movies_seen; //peliculas vistas
    
    public Profiles(){
    }
    
    public Profiles(String name, String language, String configuration, Stack movies_seen){
        this.name=name;
        this.language=language;
        this.configuration=configuration;
        this.movies_seen=movies_seen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Stack getMovies_seen() {
        return movies_seen;
    }

    public void setMovies_seen(Stack movies_seen) {
        this.movies_seen = movies_seen;
    }

    @Override
    public String toString() {
        return "Profiles\n" + "Name= " + name + ", language= " + language + ", configuration= " + configuration +
                ", movies_seen= " + movies_seen;
    }
    
    
}
