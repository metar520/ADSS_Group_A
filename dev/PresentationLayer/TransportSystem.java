package PresentationLayer;

import BussinessLayer.*;
import ServiceLayer.DriverService;
import ServiceLayer.TransportService;
import ServiceLayer.TruckService;

import java.util.*;
import java.util.stream.Collectors;

public class TransportSystem

{

    private  static TransportService transportServices = new TransportService();
    private  static DriverService ds = new DriverService();
    private static TruckService truckService = new TruckService();

    public static void main(String[] args)
    {

        TruckFacade truckFacade = makeSomeTrucks();
        List<Destination> dests = makeSomeDestinations();
        List<Destination> sources = makeSomeSources();
        List<Delivery> deliveries = transportServices.createDeliveries(sources,dests);


        TransportFacade transportFacade = letTheUserMatch(deliveries,truckService.truckFacade, ds.driverFacade);

        transportFacade.runTheTransports();



    }

    public static TransportFacade letTheUserMatch(List<Delivery> deliveries, TruckFacade truckFacade, DriverFacade driverFacade )
    {
        TransportFacade transportFacade= TransportFacade.getInstance();
        List<Driver> availableDrivers = driverFacade.getAvailableDrivers();
        List<Truck> availableTrucks = truckFacade.getAvailableTrucks();
        List<Delivery> availableDeliveries = new ArrayList<>(deliveries);

        int driverId = 0;
        int truckId = 0;
        int deliveryId = 0;

        while (!availableDeliveries.isEmpty() && !availableDrivers.isEmpty() && !availableTrucks.isEmpty()) {
            System.out.println("\nAvailable drivers:");
            printDrivers(availableDrivers);
            System.out.println("\nAvailable trucks:");
            printTrucks(availableTrucks);
            System.out.println("\nAvailable deliveries:");
            printDeliveries(availableDeliveries);

            Scanner scanner = new Scanner(System.in);

            // Match driver and truck
            boolean matchFound = false;
            Driver driver = null;
            Truck truck = null;
            while (!matchFound) {
                System.out.print("\nEnter the ID of the driver to match: ");
                driverId = scanner.nextInt();
                driver = availableDrivers.get(driverId);

                System.out.print("\nEnter the ID of the truck to match: ");
                truckId = scanner.nextInt();
                truck = availableTrucks.get(truckId);

                if (driver.getLicense().equals(truck.getModel())) {
                    matchFound = true;
                } else {
                    System.out.println("The driver's license does not match the truck's model. Please try again.");
                }
            }

            // Match deliveries to the driver
            System.out.print("\nEnter the number of deliveries to match for this driver: ");
            int numDeliveries = scanner.nextInt();

            List<Delivery> matchedDeliveries = new ArrayList<>();
            for (int i = 1; i <= numDeliveries; i++) {
                printDeliveries(availableDeliveries);
                System.out.print("\nEnter the ID of the delivery to match: ");
                deliveryId = scanner.nextInt();

                Delivery delivery = availableDeliveries.get(deliveryId);
                matchedDeliveries.add(delivery);
                availableDeliveries.remove(delivery);
                delivery.setStatus(Status.INVITED);
                delivery.setDriver(driver);
                delivery.setTruck(truck);
            }

            // Update available drivers and trucks
            availableDrivers.remove(driver);
            availableTrucks.remove(truck);

            // Print matched driver, truck, and deliveries
            System.out.println("\nMatched driver: " + driver.getName() + " (" + driver.getLicense() + ")");
            System.out.println("Matched truck: " + truck.getPlateNumber() + " (" + truck.getModel() + ")");
            System.out.println("Matched deliveries:");
            printDeliveries(matchedDeliveries);
            Date d = new Date();
            List<Destination> destinationList = letTheUserChooseTheOrder(matchedDeliveries);

            transportFacade.createTransport("11/1/22","0000",truck.getPlateNumber(),driver.getName(),driver.getId(),"source",
                    destinationList,matchedDeliveries,truck.getWeightNeto(),truck.getWeightMax());
        }

        if (availableTrucks.isEmpty()) {
            System.out.println("\nNo more available trucks.");
        }

        if (availableDrivers.isEmpty()) {
            System.out.println("\nNo more available drivers.");
        }

        if (availableDeliveries.isEmpty()) {
            System.out.println("\nNo more available deliveries.");
        }
        return transportFacade;
    }

    private static List<Destination> letTheUserChooseTheOrder(List<Delivery> matchedDeliveries) {
        // Create a list of all destinations without duplicates
        List<Destination> allDestinations = new ArrayList<>();
        for (Delivery delivery : matchedDeliveries) {
            allDestinations.add(delivery.getSource());
            allDestinations.add(delivery.getDest());
        }
        List<Destination> uniqueDestinations = allDestinations.stream().distinct().collect(Collectors.toList());

        // Print all destinations with indexes for the user to choose from
        System.out.println("Please choose the order of destinations:");
        for (int i = 0; i < uniqueDestinations.size(); i++) {
            Destination destination = uniqueDestinations.get(i);
            System.out.println(i + ": " + destination.getAddress() + " (" + destination.getLocation() + ")");
        }

        // Ask user to input the order of destinations
        List<Destination> chosenOrder = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the next destination or enter -1 to finish the order:");
        int index = scanner.nextInt();
        while (index != -1) {
            if (index >= 0 && index < uniqueDestinations.size()) {
                Destination destination = uniqueDestinations.get(index);
                if (!chosenOrder.contains(destination)) {
                    chosenOrder.add(destination);
                    System.out.println("Added " + destination.getAddress() + " to the order.");
                } else {
                    System.out.println("Destination already added to the order.");
                }
            } else {
                System.out.println("Invalid index.");
            }
            System.out.println("Enter the index of the next destination or enter -1 to finish the order:");
            index = scanner.nextInt();
        }

        // Print the chosen order
        System.out.println("Chosen order of destinations:");
        for (int i = 0; i < chosenOrder.size(); i++) {
            Destination destination = chosenOrder.get(i);
            System.out.println(i + ": " + destination.getAddress() + " (" + destination.getLocation() + ")");
        }

        return chosenOrder;
    }


    public static void printDrivers(List<Driver> drivers) {
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            System.out.println(i + ": " + driver.getName() + " (" + driver.getLicense() + ")");
        }
    }

    public static void printTrucks(List<Truck> trucks) {
        for (int i = 0; i < trucks.size(); i++) {
            Truck truck = trucks.get(i);
            System.out.println(i + ": " + truck.getPlateNumber() + " (" + truck.getModel() + ")");
        }
    }


    public static void printDeliveries(List<Delivery> deliveries) {
        for (int i = 0; i < deliveries.size(); i++) {
            Delivery delivery = deliveries.get(i);
            System.out.println(i + ": " + delivery.getSource().getAddress() + " (" + delivery.getSource().getLocation() + ") -> "
                    + delivery.getDest().getAddress() + " (" + delivery.getDest().getLocation() + ")");
        }
        System.out.println();
    }

    public static void printDestinations(List<Destination> destList) {
        for (Destination d : destList) {
            System.out.println("Address: " + d.getAddress());
            System.out.println("Phone number: " + d.getPhoneNumber());
            System.out.println("Contact name: " + d.getContactName());
            System.out.println("Location: " + d.getLocation());
            System.out.println();
        }
    }

    private static List<Destination> makeSomeDestinations()
    {
        List<Destination> dests = new ArrayList<Destination>();

        dests.add(new Destination("tel aviv", "555-1234", "John Smith", Location.NORTH,DestinationType.DESTINATION));
        dests.add(new Destination("raanana", "555-5678", "Jane Doe", Location.SOUTH,DestinationType.DESTINATION));
        //dests.add(new Destination("ashkelon", "555-9012", "Bob Johnson", Location.CENTER,DestinationType.DESTINATION));
        return dests;

    }
    private static List<Destination> makeSomeSources()
    {
        List<Destination> sources = new ArrayList<Destination>();

        sources.add(new Destination("cola", "555-1234", "John Smith", Location.NORTH,DestinationType.SOURCE));
        sources.add(new Destination("osem", "555-5678", "Jane Doe", Location.SOUTH,DestinationType.SOURCE));
        sources.add(new Destination("tnuva", "555-9012", "Bob Johnson", Location.CENTER,DestinationType.SOURCE));
        return sources;

    }


    private static TruckFacade makeSomeTrucks()
    {
        Truck T1= new Truck("aaaa","a",200,1000);
        Truck T2= new Truck("bbbb","b",200,1000);
        Truck T3= new Truck("cccc","c",200,1000);
        Truck T4= new Truck("dddd","d",200,1000);
        Truck T5= new Truck("eeee","e",200,1000);
        TruckFacade T = TruckFacade.getInstance();
        T.addTruck(T1);
        T.addTruck(T2);
        T.addTruck(T3);
        T.addTruck(T4);
        T.addTruck(T5);
        return T;
    }

    private static void makeSomeDrivers()
    {

        ds.addDriver(1,"rotem","a");
        ds.addDriver(2,"kfir","b");
        ds.addDriver(3,"adi","c");
        ds.addDriver(4,"messi","d");
        ds.addDriver(5,"ronaldo","e");

    }
    public static void printDeliveryDetails(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            System.out.println("Delivery #" + delivery.getId());
            System.out.println("Driver: " + delivery.getDriver().getName());
            System.out.println("Truck: " + delivery.getTruck().getPlateNumber() + " (" + delivery.getTruck().getModel() + ")");
            System.out.println("Status: " + delivery.getStatus());
            System.out.println("Source: " + delivery.getSource().getAddress() + " (" + delivery.getSource().getLocation() + ")");
            System.out.println("Destination: " + delivery.getDest().getAddress() + " (" + delivery.getDest().getLocation() + ")");
            System.out.println("Items:");
            for (String item : delivery.getItems()) {
                System.out.println("- " + item );
            }
            System.out.println();
        }
    }
}