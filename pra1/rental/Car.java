package rental;

public class Car{

    private String brand;
    private String licensePlate;
    private double price;
    public double getPrice(){return price;}
    private static final double MAX_PRICE = 500.0;
    private static final Car CAR_OF_THE_YEAR = new Car("Alfa Romeo", "ABC 123", MAX_PRICE);


     @Override
    public String toString() {
        String priceStr = String.format("%.1f", price).replace('.', ',');
        return String.format("%s (%s)   %s EUR", brand, licensePlate, priceStr);

    }
    
    private Car(String brand, String licensePlate, double price){
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.price = price;
    }

    public boolean isCheaperThan(Car other){
        return this.price < other.price;
    }

    public void decreasePrice(){
        if(price >= 10 && price < MAX_PRICE )
            price -= 10;
    }

    public static Car make(String brand, String licensePlate, double price){
        
        if(brand.length() <= 2 || !(brand.matches("[a-zA-Z ]+")))
            return null;

        if(!(isValidLicensePlate(licensePlate)))
            return null;

        if(0 >= price || price > MAX_PRICE )
            return null;


        return (new Car(brand, licensePlate, price));
    }




    private static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate.length() != 7) return false;
        for (int i=0; i<7; i++) {
            if (i < 3) {
                if (!Character.isUpperCase(licensePlate.charAt(i))) {
                    return false;
                }
            } else if (i == 3) {
                if (licensePlate.charAt(i) != ' ') {return false;}
            } else {
                if (!Character.isDigit(licensePlate.charAt(i))) {return false;}
            }
        }
        return true;
    }
}





