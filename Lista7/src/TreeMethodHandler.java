/**
 * Wykonuje metody na dowolnym drzewie z odpowiednimi argumentami i zwraca
 * odpowiedzi jako String.
 * 
 * Klasa bierze komendę i wykonuje przypisaną jej operacje na drzewie binarnym,
 * a także wypisuje rezultat.
 * 
 * Ta klasa mogłaby znajdować się w BinaryTree ale chciałem rozdzielić
 * wykonywanie komend od modelu danych
 * A klasa istnieje ponieważ chciałem uniknąć kodu sprawdzania typu drzewa dla
 * każdej metody z osobna, teraz sprawdzenie metody i typu drzewa jest napisane
 * tylko raz, więc gdy dodamy nowy typ drzewa, trzeba go dodać tylko 1 ifa i do
 * możliwych drzew w enumie.
 */
class TreeMethodHandler<T extends Comparable<T>> {
    private BinaryTree<T> bt;

    /**
     * Podstawowy konstruktor.
     * Tworzy klasę i przypisuje jej drzewo binarne, na którym będzie wykonywać
     * metody
     * 
     * @param bt drzewo binarne, na którym będzie wykonywać metody
     */
    TreeMethodHandler(BinaryTree<T> bt) {
        this.bt = bt;
    }

    /**
     * Przyjmuje napis komendy, sprawdza czy taka istnieje, a później ją wykonuje i
     * zwraca rezultat w postaci napisu.
     * 
     * Przyjmuje dowolną ilość argumentów, aby ułatwić implementacje nowych metod.
     * 
     * @param methodName napis komendy
     * @param args       dowolna ilość argumentów
     * @return
     */
    public String runMethod(String methodName, T[] args) {
        //sprawdz czy taka komenda istnieje
        TreeCommand command = null;
        for (TreeCommand tCommand : TreeCommand.values()) {
            if (methodName.equals(tCommand.name)) {
                command = tCommand;
                break;
            }
        }
        if (command == null) {
            return "Nie ma takiej metody";
        }

        //wykonaj komendę i zwróć rezultat
        String outString = "";
        switch (command) {
            case TreeCommand.search:
                boolean successful = bt.search(args[0]);

                outString = "Wyszukiwanie zakończone: ";
                if (!successful) {
                    outString += "nie ";
                }
                outString += "znaleziono";

                return outString;
            case TreeCommand.insert:
                bt.insert(args[0]);
                return bt.draw();
            case TreeCommand.delete:
                bt.delete(args[0]);
                return bt.draw();
            case TreeCommand.draw:
                return bt.draw();
            default:
                return "Metoda nie jest zaimplementowana.";
        }
    }
}