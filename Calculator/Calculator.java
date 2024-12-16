public class Calculator
{
    private double num1;
    private double num2;
    private double num3;

    void setNum1(double num1)
    {
        this.num1 = num1;
    }

    double getNum1()
    {
        return num1;
    }

    void setNum2(double num2)
    {
        this.num2 = num2;
    }

    double getNum2()
    {
        return num2;
    }

    void setNum3(double num3)
    {
        this.num3 = num3;
    }

    double getNum3()
    {
        return num3;
    }

    static double add(double a, double b)
    {
        return a + b;
    }

    static double add(double a, double b, double c)
    {
        return a + b + c;
    }

    static double sub(double a, double b)
    {
        return a - b;
    }

    static double sub(double a, double b, double c)
    {
        return a - b - c;
    }

    static double multi(double a, double b)
    {
        return a * b;
    }

    static double multi(double a, double b, double c)
    {
        return (a * b * c);
    }


    static double div(double a, double b)
    {
        if (b == 0)
        {
            System.out.println("Error: Division by zero");
            return Double.NaN ;
        }
        return a / b;
    }

    static double pow(double a, double b)
    {
        return Math.pow(a , b);
    }

    static double mod(double a , double b)
    {
        return a % b ;
    }

    static void checker(double result)
    {
        if (result % 1 != 0)
        {
            System.out.println(result);
        }
        else
        {
            if (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE)
            {
                System.out.println((int) result);
            }
            else
            {
                System.out.println((long) result);
            }
        }


    }
}
