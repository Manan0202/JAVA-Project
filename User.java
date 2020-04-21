import java.util.Date;
import java.util.Scanner;

//Using Inetrface Concept 
interface ModeOfTransport{

  //declaring methos without body
  double getPrice(double kms);
  //int id;
  //void setArrival(double kms);
}

//Class implementing ModeofTransport Interface
class Flight implements ModeOfTransport{
  Date departureTime,arrivalTime;
 
  //use of final keyword
  final int flightrent = 6;

  //Overriding method
  public double getPrice(double kms){
    return(kms*flightrent);
  }

  public String toString(){
    return "Flight";
  }

}

//Class implementing ModeofTransport Interface
class Bus implements ModeOfTransport{
  Date departureTime,arrivalTime;

  //use of final keyword
  final int busrent = 3;

  //overriding method
  public double getPrice(double kms){
    return(kms*busrent);
  }

    public String toString(){
      return "Bus";
    }

}

//Class implementing ModeofTransport Interface
class Train implements ModeOfTransport{
  Date departureTime,arrivalTime;
  
  //Use of final keyword
  final int trainrent = 2;

  //Overriding method
  public double getPrice(double kms){
    return(kms*trainrent);
  }

    public String toString(){
      return "Train";
    }

}

//Class portal will return object of class from which user wants to travel
class TravelPortal{

  //Using Reference Variable concept
  ModeOfTransport mot;
  double fare;

  void selectMode(int mode)
  {
    switch(mode){
      case 1:
        //creating object for flight class
        mot = new Flight();
      break;
      case 2:
        //creating object for train class
        mot = new Train();

      break;
      case 3:

        //creating object for bus class
        mot = new Bus();
      break;

    }
  }
  //priority: 1=time,2=money
  //Thismethod help user to choose better strategy according to TRAVEL_AGENT.com
  void selectMode(int priority,double kms){
    if(priority==2){
      mot = new Train();
    }
    else{
      if(kms>1000){
        mot = new Flight();
      }
      else{
        mot = new Bus();
      }

    }
  }
  //Creating to method to calculate fare for the bill generation
  void calcFare(double kms,int passengers){
    fare=this.mot.getPrice(kms)*passengers;
  }
  //creating method to calculate discount
  void applyCoupon(Coupon c){
    fare = fare- fare*c.discount*0.01;
  }
}
//creating class terms in which if user acccepts the TRAVEL_AGENT terms conditon than only he/she will alowed to book the ticket. 
class Terms{
  String accept;
  void agree(String accept)
  {
    //checking if user agreed the terms and conditions
    if(accept.equals("yes")){    
    System.out.println("You are Allowed to book the tickets ");
    System.out.println(" ");
    System.out.println(" ");
  }
  else{
    //exiting the system if user decline to accept the terms and conditons
    System.out.println("Sorry you are not allowed to book the tickets .....");
    System.out.println(" ");
    System.out.println(" ");
    System.out.println("Have a Nice Day !!");
    System.exit(0);
  }

  }
}
class Company_info{
  //Use of constructre
  //default constructore
  Company_info()
  {
    System.out.println("Pack your bags. We’re going on vacation!........");
    System.out.println(" ");
    System.out.println(" ");
    System.out.println("Travel_AGENT.Com is a private retailer that provides travel and tourism related services to the public on behalf of suppliers such as  airplane tickets, train tickets, bus tickets..");
    System.out.println(" ");
    System.out.println(" ");
    System.out.println(" ");
  }
}
//use of inheritance concept
class Display_info extends Company_info{
   //use of constructor concept
   //default constructor of passenger_info class
   //constructor 1 with name passenger_info
   Display_info()
   {
    // use of super keyword to invoke parent class(Company_info) constructor
    super();
    System.out.println("---------------------------Welcome TRAVEL_AGENT.COM----------------------------");
   }
}
 
class Passenger_info 
{
   String name;
   int age , phone;
   
   Passenger_info()
   {
    System.out.println("Please Enter valid Information :");
   }
  
   //method 1 with name getDetails
   //we will be taking user information who is going totravel with us
   void getDetails()
   {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name of Passenger : ");
        name= sc.next();
        System.out.print("Enter Age of Passenger :  ");
        age = sc.nextInt();
        System.out.print("Enter Contact Number oF Passenger : ");
        phone = sc.nextInt();
   }
   void displayDetails(){
 
        System.out.println("The Name of Passenger is :"+name);
        System.out.println("The Age of Passenger is :" +age);
        System.out.println("The Contact of Passenger is :"+phone);
  }
  //method 2 with same name as method 1 within same class
  //here comes the concept of methodoverloading(methods with same name with different parameters)
  void getDetails(String aname)
  {
    System.out.println("You have successfully made  : " + aname + "Payment");
  }

  //constructor 2 with same name as constructor 1 so here comes the concept constructor overloadinng(same name with different parameters)
  //Parametrized Constructor
  Passenger_info(String rating)
  {
    System.out.println("Thank You for giving us " + rating + "  Stars :) :) ");
    System.out.println("We Appreciate Your Value feedback !!!!!!");
    System.out.println("Have A Safe and Happy Journey.....");
  }

}

class User{
  //using of static keyword
  //we will be creating object and call all the method that require to run the system.
  public static void main(String [] args){
    ///Using scanner class to take input

    Scanner scan = new Scanner(System.in);

    //creating object of terms classes and calling its coresponding methods 
    Terms t = new Terms();
    System.out.println("Are u agreed with the terms and conditons : YES or NO");
    String accept =  scan.next();
    t.agree(accept);
    //creating object of displaying_info class
    Display_info di = new Display_info();

    //creating object of passenger class and calling its corresponding methods
    Passenger_info p = new Passenger_info();
    String source,destination; 
    System.out.println("Enter The Mode of Payment : ");
    String aname = scan.next();
    System.out.println("Enter Source");
    source = scan.next();
    System.out.println("Enter Destination");
    destination = scan.next();
    System.out.println("Enter kilometers to travel");
    double km = scan.nextDouble();
    System.out.println("Enter number of passengers");
    int pas = scan.nextInt();
    
    //creating array to take no of passenger wants to travel
    Passenger_info pi[] = new Passenger_info[pas];

    //looping throug type array
    //Using concept of array of objects
    for(int i = 0;i<pas;i++){
    pi[i] = new Passenger_info();
    pi[i].getDetails();
    }
    
    //creating object of class travel portal 
    TravelPortal tp = new TravelPortal();
    //Asking user the mode of transport
    System.out.println("\nSelect mode of transport\n1.Flight\n2.Train\n3.Bus\n4.Can't decide suggest me something!");
    int choice = scan.nextInt();
    if(choice<=3){
      tp.selectMode(choice);
    }
    else{
        System.out.println("\nTell us your priority?\n1.Need to reach ASAP money doesn't matter! \nor\n2.Low on budget but have enough time!");
        int prior=scan.nextInt();
        tp.selectMode(prior,km);
    }
 
    //Here we are using concept of singleton Pattern creating object once and using it again again whenever a user want to book the tickets
    System.out.println("Have discount coupon?\n1.yes\n2.no");
    choice=scan.nextInt();
    if(choice==1){
      //System.out.println("Enter coupon code eg. FLY40");
      System.out.println("Enter discount percent");
      double discount = scan.nextDouble();
      
      Coupon c = Coupon.getCoupon(discount);
      tp.calcFare(km,pas);
      tp.applyCoupon(c);
      System.out.println("Test fare:"+tp.fare);
      }
      else
      {
        tp.calcFare(km,pas);
        System.out.println("Test fare:"+tp.fare); 
      }
      
    System.out.println("\n------------------Order Summary------------------");
    
    //using of date library and priting the date on which user booked the ticket  
    System.out.println("Date Of Booking is  : " + new Date());
    System.out.println("Mode of transport: "+tp.mot+"\nSource: "+source+"\nDestination: "+destination+"\nNo. of passengers"+pas+"\n-------------------------\nTotal Fare: "+tp.fare+"\n-------------------------");
    
    //using concept of call by reference 
    p.getDetails(aname);
    //displaying the user innformation o order summary
    for(int j = 0;j < pas;j++){
    pi[j].displayDetails(); 
    }
    System.out.println("Please rate us on a  scale of One to Five : ");
    String rating =scan.next();
    Passenger_info p2 = new Passenger_info(rating); 
  }

}

class Coupon{
  static Coupon coupon=null;
  double discount;
  private Coupon(double discount){
    this.discount=discount;
  }
  public static Coupon getCoupon(double discount){
    if(coupon==null){
      coupon = new Coupon(discount);
    }
    return coupon;
  }
}