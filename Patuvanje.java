import java.util.Scanner;
 
 
 
abstract class Patuvanje{
    private String agencija;
    private int cena;
    public Patuvanje() {}
    public Patuvanje(String agencija,int cena) {
        super();
        this.agencija=agencija;
        this.cena=cena;
    }
    public abstract int vratiVremeVoDenovi();
    public String getAgencija() {
        return this.agencija;
    }
    public int getCena() {
        return this.cena;
    }
    public void setAgencija(String agencija) {
        this.agencija=agencija;
    }
    public void setCena(int cena) {
        this.cena=cena;
    }
    public boolean daliPraznicno() {
        return false;
    }
    public static int vratiMinCena(Patuvanje []niza,int n, Patuvanje zaSporedba) {
        int min=9999999;
        boolean flag=false;
        for(int i=0;i<n;++i) {
            if(zaSporedba.vratiVremeVoDenovi()<niza[i].vratiVremeVoDenovi()) {
                if(min>niza[i].getCena()) {
                    min=niza[i].getCena();
                    flag=true;
                }
            }
        }
        if(flag==false) return 0;
        else return min;
    }
}
 //вашиот код
class GodishenOdmor extends Patuvanje{
    private int duration;
    //sekoj mesec ima 30 denovi
    public GodishenOdmor() { super(); }
    public GodishenOdmor(String agencija,int cena,int duration){
        super(agencija,cena);
        this.duration=duration;
    }
    public int getCena() {
        return super.getCena()-1000;
    }
    @Override
    public boolean daliPraznicno() {
        return false;
    }
    public int getDuration() {
        return duration;
 
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    @Override
    public int vratiVremeVoDenovi() {
        return duration;
    }
}
 
class PraznicnoPatuvanje extends Patuvanje{
    private int pocD;
    private int pocM;
    private int krajD;
    private int krajM;
    public PraznicnoPatuvanje(String agencija,int cena,int pocD,int pocM,int krajD,int krajM){
        super(agencija,cena);
        int pocetokodmor=pocM*30+pocD;
        int krajodmor=krajM*30+krajM;
        try{
            if(krajodmor<pocetokodmor) throw new Exception();
            this.pocD = pocD;
            this.pocM = pocM;
            this.krajD = krajD;
            this.krajM = krajM;
            
        }
        catch(Exception e) {
            this.pocD=krajD;
            this.krajD=pocD;
            this.pocM=krajM;
            this.krajM=pocM;
            System.out.println("Iskluchok");
        }
    }
    
    public int getPocD() {
        return pocD;
    }
    public void setPocD(int pocD) {
        this.pocD = pocD;
    }
    public int getPocM() {
        return pocM;
    }
    public void setPocM(int pocM) {
        this.pocM = pocM;
    }
    public int getKrajD() {
        return krajD;
    }
    public void setKrajD(int krajD) {
        this.krajD = krajD;
    }
    public int getKrajM() {
        return krajM;
    }
    public void setKrajM(int krajM) {
        this.krajM = krajM;
    }
    public int getCena() {
        return super.getCena();
    }
    @Override
    public int vratiVremeVoDenovi() {
        return (krajM-pocM)*30 +(krajD-pocD);
    }
    @Override
    public boolean daliPraznicno() {
        return true;
    }
}
public class Test {
 
    
    public static void main(String[] args) {
        
        
        int n;
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        
        Patuvanje nizaPatuvanje[]=new Patuvanje[n];
        
        for (int i=0;i<n;i++){
            int tip=in.nextInt();
            if (tip==0){
                String ime=in.next();
                int cena =in.nextInt();
                int vreme=in.nextInt();
                nizaPatuvanje[i]=new GodishenOdmor(ime,cena,vreme);
            }
            else {
                String ime=in.next();
                int cena =in.nextInt();
                int pocD=in.nextInt();
                int pocM=in.nextInt();
                int krajD=in.nextInt();
                int krajM=in.nextInt();
                nizaPatuvanje[i]=new PraznicnoPatuvanje(ime,cena,pocD,pocM, krajD,krajM);
                
            }
        }
        
        //решение на барање 1
        
        for(int i=0;i<n;++i) {
            if(nizaPatuvanje[i].daliPraznicno()) {
                if(((PraznicnoPatuvanje)nizaPatuvanje[i]).getPocM()==6 || ((PraznicnoPatuvanje)nizaPatuvanje[i]).getKrajM()==6 )
                    System.out.print(nizaPatuvanje[i].getAgencija() + " ");
            }
        }
        
        //решение на барање 2
        
        double sredna=0;
        for(int i=0;i<n;++i) sredna+=nizaPatuvanje[i].vratiVremeVoDenovi();
        sredna /=(double)nizaPatuvanje.length;
        System.out.println("\n" + sredna);
        
        //решение на барање 3   
        
        String agencija=in.next();
        int cena=in.nextInt();
        int vremetraenje=in.nextInt();
        GodishenOdmor odmor = new GodishenOdmor(agencija,cena,vremetraenje);
        
        //решение на барање 4
        
        int min=Patuvanje.vratiMinCena(nizaPatuvanje,n,odmor);
        System.out.println(min);
 
    }
 
}
