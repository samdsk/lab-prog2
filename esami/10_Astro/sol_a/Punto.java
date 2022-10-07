public class Punto{
    private int x;
    private int y;
    private int z;

    public Punto(int x,int y,int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public Punto(){ this(0,0,0);}

    public Punto(Punto p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    public long norma(){

        return (long) Math.sqrt(x*x+y*y+z*z);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getZ(){return z;}
    
    public void updateX(int i){this.x += i;}
    public void updateY(int i){this.y += i;}
    public void updateZ(int i){this.z += i;}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Punto)) return false;

        Punto that = (Punto) o;

        return this.x == that.x && this.y == that.y && this.z == that.z;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("("+x+", "+y+", "+z+")");
        return str.toString();
    }
};