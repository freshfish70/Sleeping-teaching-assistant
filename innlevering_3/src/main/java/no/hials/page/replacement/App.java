package no.hials.page.replacement;

public class App {

    private static final int FRAMES = 3;

    public static void main(String[] args){
        OptimalReplacement or = new OptimalReplacement();
        or.setup(FRAMES);
        or.process("7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1");
    }
}
