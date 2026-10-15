public class Matrix {
    int row;
    int col;
    Complex[][] a;
    
    public Matrix(int row, int col) {
        this.row=row;
        this.col=col;
        this.a=new Complex[row][col];
    
    }
    
    public Matrix(Complex[][] a) {
        this.row=a.length;
        this.col=a[0].length;
        this.a=a;
    
    }
    public Matrix add(Matrix b) {
        if (row!=b.row || col!=b.col) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }

        Matrix res=new Matrix(row, col);
        for (int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                res.a[i][j]=a[i][j].add(b.a[i][j]);
            }
        }
        return res;
    }

    public Matrix mul (Matrix b) {
        if(col!=b.row) {
            throw new IllegalArgumentException("Число столбцов первой матрицы не равно числу строк второй матрицы");
        }
        Matrix res=new Matrix(row, b.col);
        for (int i=0; i<row; i++) {
            for (int j=0; j<b.col; j++) {
                Complex s=new Complex(0);
                
                for (int k=0; k<col; k++) {
                    s=s.add(a[i][k].mul(b.a[k][j]));
                }
                res.a[i][j]=s;
           
            }
        
        }
        return res;
    }

    public Matrix trans() {
        
        Matrix res=new Matrix(col, row);
        for (int i=0; i<row; i++) {
            
            for (int j=0; j<col; j++) {
                res.a[j][i]=a[i][j];
            }
        }
        return res;
    
    }

    public Complex det() {
        if (row!=col) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
        
        return calcDet(a);
    }

    Complex calcDet(Complex[][] m) {
        int n=m.length;
        if (n==1) {
            
            return m[0][0];
        }

        if (n==2) {
            return m[0][0].mul(m[1][1]).sub(m[0][1].mul(m[1][0]));
        }
        
        Complex res=new Complex(0);
        int sign=1;
        for (int j=0; j<n; j++) {
            Complex[][] sub=new Complex[n-1][n-1];

            for (int r=1; r<n; r++) {
                int sc=0;
                
                for (int c=0; c<n; c++) {
                    if(c!=j) {
                        sub[r-1][sc]=m[r][c];
                        sc++;
                    }
                }
            }
            Complex term=m[0][j].mul(calcDet(sub));
            if(sign>0) {
                res=res.add(term);
            } else {
                res=res.sub(term);
            
            }
            sign=-sign;
        }
        return res;
    }

    public Matrix inv() {
        if(row!=col) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        
        }
        int n=row;
        Complex[][] m=new Complex[n][2*n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                m[i][j]=a[i][j];
                m[i][j+n]=new Complex(0);
            }
            m[i][i+n]=new Complex(1);
        
        }
        for(int i=0; i<n; i++) {
            int piv=i;
            for(int j=i+1; j<n; j++) {
                if (m[j][i].abs()>m[piv][i].abs()){
                    piv=j;
                
                }
            }
            if(m[piv][i].abs()<1e-9){
                throw new ArithmeticException("Матрица вырождена, обратной матрицы не существует");
            }
            if (piv!=i) {
                Complex[] rw=m[i];
                m[i]=m[piv];
                
                m[piv]=rw;
            }
            
            Complex pv=m[i][i];
            for (int j=0; j<2*n; j++) {
                
                m[i][j]=m[i][j].div(pv);
            }
            for (int j=0; j<n; j++) {
                if (j!=i) {
                    Complex f=m[j][i];
                    
                    for (int k=0; k<2*n; k++) {
                        m[j][k]=m[j][k].sub(f.mul(m[i][k]));
                    }
                }
            }
        }
        Matrix res=new Matrix(n, n);
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                res.a[i][j]=m[i][j+n];
            
            }
        }
        return res;
    }

    public Matrix div(Matrix b) {
        if (b.row!=b.col) {
            throw new IllegalArgumentException("Делитель должен быть квадратной матрицей");
        }
        if (col!=b.row) {
            throw new IllegalArgumentException("Число столбцов делимого не равно размеру делителя");
        }
        return mul(b.inv());
    }

    public void print() {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(a[i][j]+"\t");
            
            }
            System.out.println();
        
        }
    }
}

