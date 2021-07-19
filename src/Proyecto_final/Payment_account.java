
package Proyecto_final;

public class Payment_account {
    
    private String name;
    private int movies;
    private int series;
    private int others;
    private int price;

    public Payment_account(){
    
    }
    
    public Payment_account(String name, int movies, int series, int others, int price) {
        this.name = name;
        this.movies = movies;
        this.series = series;
        this.others = others;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovies() {
        return movies;
    }

    public void setMovies(int movies) {
        this.movies = movies;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cuenta_Cobro{" + "name=" + name + ", movies=" + movies + ", series=" + series + ", others=" + others + ", price=" + price + '}';
    }
    
    
}
