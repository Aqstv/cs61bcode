public class OffByN implements CharacterComparator {
    private int n;
    OffByN(int nn) {
        n = nn;
    }

    @Override
    public boolean equalChars(char x, char y){
        return x - y == n || x - y == -n;
    }
}
