public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() { return price; }
    // Ignoring the methods written for output formatting as described in the instruction of part3 of assignment
    /*@Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
*/
}
