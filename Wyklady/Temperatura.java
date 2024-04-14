class MojException extends Exception {};

public class Temperatura {
    public int a;

    public int geta() throws MojException{
        if(a<0){
            throw new MojException();
        }
        return a;
    }
}
