import java.util.Date;
import java.util.Scanner;
interface ModeOfTransport{
  double getPrice(double kms);
  //int id;
  //void setArrival(double kms);
}

class Flight implements ModeOfTransport{
  Date departureTime,arrivalTime;
  public double getPrice(double kms){
    return(kms*5);
  }

  public String toString(){
    return "Flight";
  }

}

class Bus implements ModeOfTransport{
  Date departureTime,arrivalTime;


  public double getPrice(double kms){
    return(kms*1.5);
  }

    public String toString(){
      return "Bus";
    }

}


class Train implements ModeOfTransport{
  Date departureTime,arrivalTime;


  public double getPrice(double kms){
    return(kms*0.5);
  }

    public String toString(){
      return "Train";
    }

}

class TravelPortal{
  ModeOfTransport mot;
  double fare;

  void selectMode(int mode)
  {
    switch(mode){
      case 1:
        mot = new Flight();
      break;
      case 2:
        mot = new Train();

      break;
      case 3:
        mot = new Bus();
      break;

    }
  }
  //priority: 1=time,2=money
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
  void calcFare(double kms,int passengers){
    fare=this.mot.getPrice(kms)*passengers;
  }
  void applyCoupon(Coupon c){
    fare = fare- fare*c.discount;
  }
}

class User{
  public static void main(String [] args){
    System.out.println("--------------------------------------------Welcome to NS3J Travels--------------------------------------------");

    String source,destination;

    Scanner scan = new Scanner(System.in);
    System.out.println("Enter Source");
    source = scan.next();
    System.out.println("Enter Destination");
    destination = scan.next();
    System.out.println("Enter kilometers to travel");
    double km = scan.nextDouble();
    System.out.println("Enter number of passengers");
    int pas = scan.nextInt();
    TravelPortal tp = new TravelPortal();

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

    System.out.println("Have discount coupon?\n1.yes\n2.no");
    choice=scan.nextInt();
    if(choice==1){
      //System.out.println("Enter coupon code eg. FLY40");
      System.out.println("Enter dicount percent");
      double discnt = scan.nextDouble();
      Coupon c = Coupon.getCoupon(discnt);
      tp.applyCoupon(c);
    }

    tp.calcFare(km,pas);
    System.out.println("\n------------------Order Summary------------------");
    System.out.println("Mode of transport: "+tp.mot+"\nSource: "+source+"\nDestination: "+destination+"\nNo. of passengers"+pas+"\n-------------------------\nTotal Fare: "+tp.fare+"\n-------------------------");
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