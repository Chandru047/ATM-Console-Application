import java.util.Scanner;
class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Calculator calc = new Calculator();
        double result ;

        while (true)
        {
            System.out.println("Enter your choice (+ - * / pow mod): ");
            String choice = in.next();

            if(choice.equals("break"))
                break;

            System.out.println("No of operands (2 or 3): ");
            int count = in.nextInt();

            if (count == 2)
            {
                System.out.println("Enter your two numbers :");
                calc.setNum1(in.nextDouble());
                calc.setNum2(in.nextDouble());

                switch (choice)
                {
                    case "+" :
                        result =(calc.add(calc.getNum1(), calc.getNum2() ));
                        calc.checker(result);
                        break;
                    case "-" :
                        result =(calc.sub(calc.getNum1(), calc.getNum2() ));
                        calc.checker(result);
                        break;
                    case "*" :
                        result =(calc.multi(calc.getNum1(), calc.getNum2() ));
                        calc.checker(result);
                        break;
                    case "/" :
                        result =(calc.div(calc.getNum1(), calc.getNum2() ));
                        calc.checker(result);
                        break;
                    case "pow" :
                        result = calc.pow(calc.getNum1(), calc.getNum2());
                        calc.checker(result);
                        break;
                    case "mod" :
                        result = calc.mod(calc.getNum1() , calc.getNum2());
                        calc.checker(result);
                        break;
                    default:
                        System.out.println("Please enter a valid operation");

                }



            }

            else if (count == 3)
            {
                if (choice.equals("/") || choice.equals("mod") || choice.equals("pow"))
                {
                    System.out.println("The selected operation does not support 3 operands");
                    continue;
                }
                System.out.println("Enter your three numbers :");
                calc.setNum1(in.nextDouble());
                calc.setNum2(in.nextDouble());
                calc.setNum3(in.nextDouble());

                switch (choice)
                {
                    case "+" :
                        result =(calc.add(calc.getNum1(), calc.getNum2() , calc.getNum3()));
                        calc.checker(result);
                        break;
                    case "-" :
                        result =(calc.sub(calc.getNum1(), calc.getNum2() , calc.getNum3()));
                        calc.checker(result);
                        break;
                    case "*" :
                        result =(calc.multi(calc.getNum1(), calc.getNum2() , calc.getNum3()));
                        calc.checker(result);
                        break;
                    default:
                        System.out.println("Please enter a valid operation");


                }
            }


        }

    }
}