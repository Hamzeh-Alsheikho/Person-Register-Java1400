package sample;

public class Dato {
    int dag;
    int måned;
    int år;

    public Dato(int dag, int måned, int år) {
        this.dag = dag;
        this.måned = måned;
        this.år = år;
    }
    public String toString(){
        String show = dag+"."+måned+"."+år;
        return show;
    }
}
