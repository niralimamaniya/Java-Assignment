class InvalidDurability extends Exception{
    InvalidDurability(){
        System.out.println("-**-Exception occured : Durability must be greater than 0 and less than or equal to 1-**-");
    }
}

class InvalidWarranty extends Exception{
    InvalidWarranty(){
        System.out.println("-**-Exception occured : Warranty must be greater than 0 -**-");
    }
}

abstract class Equipment{
    private String make;
    private String model;
    private int purchaseYear;

    //EquipmentConstructor
    Equipment(String make,String model,int purchaseYear){
        this.make=make;
        this.model=model;
        this.purchaseYear=purchaseYear;
    }

    public void setMake(String make){
        this.make=make;
    }
    public String getMake(){
        return make;
    }
    public void setModel(String model){
        this.model=model;
    }
    public String getModel(){
        return model;
    }
    public void setPurchaseYear(int purchaseYear){
        this.purchaseYear=purchaseYear;
    }
    public int getPurchaseYear(){
        return purchaseYear;
    }
    abstract int replacementYear();
    //display details
    public String showDetails(){
        return (make+" "+model+"\nPurchase Year : "+purchaseYear);
    }
}

class BatteryPoweredEquipment extends Equipment{
    private int warranty;
    BatteryPoweredEquipment(String make, String model, int purchaseYear,int warranty) {
    super(make, model, purchaseYear); 
    }
        public void setWarranty(int warranty) throws InvalidWarranty{
            this.warranty=warranty;
            if(warranty<=0)
            {
                //throws exception if warranty is less than or equal to 0
                throw new InvalidWarranty();
            }
        }
        public int getWarranty(){
            return warranty;
        }
        public int replacementYear(){
            int replacementyear=warranty+super.getPurchaseYear();
            return replacementyear;
        }
}


class FuelPoweredEquipment extends Equipment{

    private int usage;

    //constant-value: maximum number of days an item can be used
    final int MaximumDays=500;
    FuelPoweredEquipment(String make, String model, int purchaseYear,int usage) {
        super(make, model, purchaseYear);
    }

    public void setUsage(int usage){
        this.usage=usage;
    }
    public int getUsage(){
        return usage;
    }
    public int replacementYear() {
        int replacementyear=MaximumDays/usage+super.getPurchaseYear();
        return replacementyear;
    }
}

class StandardEquipment extends Equipment{

    private double durability;

    //constant-value: maximum lifespan
    final int maximumRetention=7;
    StandardEquipment(String make, String model, int purchaseYear,double durability) {
        super(make, model, purchaseYear);
    }

    public void setDurability(double durability) throws InvalidDurability{
        this.durability=durability;
        if(durability==0 && durability<1 || durability>1)
            {
                //throws exception if durability is less than 0 and greater than or equal to 1
                throw new InvalidDurability();
            }
    }
    public double getDurability(){
        return durability;
    }
    public int replacementYear() {
        int replacementyear=(int) (maximumRetention*durability+super.getPurchaseYear());
        return replacementyear;
    }   
}

public class ReplacementTest{
    public static void main(String[] args) {
        StandardEquipment se=new StandardEquipment(null, null, 0, 0);
        FuelPoweredEquipment fpe=new FuelPoweredEquipment(null, null, 0, 0);
        BatteryPoweredEquipment bpe=new BatteryPoweredEquipment(null, null, 0, 0);

        try{
            se.setMake("Binnings Rake");
            se.setModel("GSM");
            se.setPurchaseYear(2017);
            se.setDurability(1);
            System.out.println("\n"+se.showDetails()+"\nReplacement Year : "+se.replacementYear());
        }
        catch(InvalidDurability e)
        {
            System.out.println(e.getMessage());
        }
        try{
            se.setMake("Binnings Spade");
            se.setModel("RAK");
            se.setPurchaseYear(2019);
            se.setDurability(0);
            System.out.println("\n"+se.showDetails()+"\nReplacement Year : "+se.replacementYear());
        }
        catch(InvalidDurability e)
        {
            System.out.println(e.getMessage());
        }
        try{
            fpe.setMake("Botch Mower");
            fpe.setModel("Rotak 40");
            fpe.setPurchaseYear(2020);
            fpe.setUsage(200);
            System.out.println("\n"+fpe.showDetails()+"\nReplacement Year : "+fpe.replacementYear());
            fpe.setMake("Havana Chain Saw");
            fpe.setModel("Rotak 22");
            fpe.setPurchaseYear(2019);
            fpe.setUsage(300);
            System.out.println("\n"+fpe.showDetails()+"\nReplacement Year : "+fpe.replacementYear());
        }
        catch(Exception e){
            System.out.println(e);
        }
        try{
            bpe.setMake("Nikita Edge Trimmer");
            bpe.setModel("RBC411U");
            bpe.setPurchaseYear(2021);
            bpe.setWarranty(3);
            System.out.println("\n"+bpe.showDetails()+"\nReplacement Year :"+bpe.replacementYear());
        }
        catch(InvalidWarranty e)
        {
            System.out.println(e.getMessage());
        }
        try{
            bpe.setMake("Nikita Brush Cutter");
            bpe.setModel("RBC411U");
            bpe.setPurchaseYear(2020);
            bpe.setWarranty(0);
            System.out.println("\n"+bpe.showDetails()+"\nReplacement Year :"+bpe.replacementYear());
        }
        catch(InvalidWarranty e)
        {
            System.out.println(e.getMessage());
        }
    }
}