
package Proyecto_final;

public class Top {
    
    private Playback p;
    private int pos;

    public Top(Playback p, int pos) {
        this.p = p;
        this.pos = pos;
    }

    public Playback getP() {
        return p;
    }

    public void setP(Playback p) {
        this.p = p;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Top\n" + "Playback= " + p + ", pos= " + pos;
    }
    
    
}
