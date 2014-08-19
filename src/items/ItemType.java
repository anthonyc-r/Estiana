package items;

public enum ItemType{
    NOTE, PAPER, PEN, DIRT;
    
    public String toString(){
        return super.toString().toUpperCase();
    }
}