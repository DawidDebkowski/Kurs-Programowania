/**
 * Stworzenie klasy implementującej Comparable.
 * Dodanie drzewa na serwer.
 * Dodanie do możliwych typów drzewa. (enum)
 * Dodanie drzewa na ServerThread.
 * Przetwarzanie napisu na nasz typ danych. (executeCommand w ServerThread) + metoda toString to typu danych
 * Śmiga
 */

public class MyDataType implements Comparable<MyDataType>{
    int key;
    String name;

    public MyDataType(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int compareTo(MyDataType o) {
        if(key < o.key) {
            return -1;
        } else if(key == o.key) {
            return name.compareTo(o.name);
        }

        return 1;
    }
    
    public String toString() {
        return key + " " + name;
    }

    public static MyDataType parseFromString(String key, String name) throws NumberFormatException{
        return new MyDataType(Integer.parseInt(key), name);
    }
}
