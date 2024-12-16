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

    double add(double a, double b)
    {
        return a + b;
    }

    double add(double a, double b, double c)
    {
        return a + b + c;
    }

    double sub(double a, double b)
    {
        return a - b;
    }

    double sub(double a, double b, double c)
    {
        return a - b - c;
    }

    double multi(double a, double b)
    {
        return a * b;
    }

    double multi(double a, double b, double c)
    {
        return (a * b * c);
    }


    double div(double a, double b)
    {
        return a / b;
    }

    double pow(double a, double b)
    {
        return Math.pow(getNum1(), getNum2());
    }

    double mod(double a , double b)
    {
        return a % b ;
    }

    void checker(double result)
    {
        if (result % 2 != 0)
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