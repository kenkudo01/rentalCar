package rental;
import java.util.ArrayList;
import java.util.*;
import java.lang.NumberFormatException;
import java.io.*;

public class CarRental{

    private ArrayList<Car> cars;

   @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Car car : cars) {
            str.append(car.toString()).append("\n");
        }
         return str.toString();
        }



    public CarRental(String filename){

        cars = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = br.readLine();
            while(line != null)
            {
                if(line.matches(".+:.+,.+"))
                {
                String[] token = line.split(":");
                String brand = token[0];
                token = token[1].split(",");
                String licensePlate = token[0];
                double price = Double.parseDouble(token[1]);

                Car newCar = Car.make(brand, licensePlate, price);
                if(newCar != null)
                    cars.add(newCar);

                line = br.readLine();
                }
            }
        }catch(NumberFormatException | IOException e){

        }

    }

    public int numberOfCars(){
        return cars.size();
    }

    public void insertionSort(){
        for(int i = 1; i < cars.size(); i++)
        {
             Car pivot = cars.get(i);
             int j = i - 1;
             while(j >= 0)
             {
                if(cars.get(j).getPrice() > pivot.getPrice()){
                    Car temp = cars.get(j);
                    cars.set(j+1, cars.get(j));
                    j--;
                }
                cars.set(j, pivot);


             }
        }
       
    }

    public double weightedAverage(){
        if(cars == null)
            return -1.0;
        
        double totalWeights = 0;
        double totalPrice = 0;

        for(int i = 0; i < cars.size(); i++)
        {
            totalPrice += cars.get(i).getPrice();
            totalWeights += i + 1;
        }

        return totalPrice / totalWeights;
    }

    public Car rentCheapest() {
        if (cars.isEmpty()) return null;
        insertionSort();
        return cars.remove(0); 
    }

    public ArrayList<Car> sale() {
        ArrayList<Car> allCars = new ArrayList<Car>(cars);
        Random random = new Random();
        for (Car car: allCars ) {
            if (random.nextInt(3) == 1) car.decreasePrice();
        }
        return allCars;
    }



}