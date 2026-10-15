public class Complex {
    double r;
    double i;
    public Complex(double r, double i) 
    {
        this.r=r;
        this.i=i;
    }


    public Complex(double r){
        this(r,0);
    }
    public Complex add(Complex b){

        return new Complex(r+b.r, i+b.i);


    }

    public Complex sub(Complex b) {
        return new Complex(r-b.r, i-b.i);
    
    }

    public Complex mul(Complex b) {
        return new Complex(r*b.r-i*b.i, r*b.i+i*b.r);
    }

    public Complex div(Complex b) {
        double d=b.r*b.r+b.i*b.i;
        if (d==0){
            
            throw new ArithmeticException("Деление на ноль");
        }
        return new Complex((r*b.r+i*b.i)/d, (i*b.r-r*b.i)/d);
    }
    public double abs(){
        
        return Math.sqrt(r*r+i*i);
    }

    public String toString() {
        if (i<0){
            return r+"-"+(-i)+"i";
        }
        return r+"+"+i+"i";
    
    }
}