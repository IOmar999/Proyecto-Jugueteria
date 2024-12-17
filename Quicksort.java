
class Quicksort {

    public void quick(String[] nombres) {
        quicksort(nombres, 0, nombres.length - 1);
    }

    private void quicksort(String[] nombres, int inicio, int fin) {
        int posI = inicio;
        int posD = fin;
        String pivote = nombres[(posI + posD) / 2];

        do {
            while (nombres[posI].compareToIgnoreCase(pivote) < 0)
                posI++;
            while (nombres[posD].compareToIgnoreCase(pivote) > 0)
                posD--;
            if (posI <= posD) {
                intercambiar(nombres, posI, posD);
                posI++;
                posD--;
            }
        } while (posI <= posD);

        if (inicio < posD)
            quicksort(nombres, inicio, posD);
        if (posI < fin)
            quicksort(nombres, posI, fin);
    }

    private void intercambiar(String[] nombres, int i, int j) {
        String temp = nombres[i];
        nombres[i] = nombres[j];
        nombres[j] = temp;
    }
}
