import FrontEndCorba.FrontEnd;
import FrontEndCorba.FrontEndHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class TestMultipleClient {

    public static FrontEnd connect(String name){
        FrontEnd frontEnd = null;
        try{
            // create and initialize the ORB
            ORB orb = ORB.init(new String[]{"-ORBInitialPort","1050","-ORBInitialHost","localhost"}, null);

            // get the root naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            frontEnd = FrontEndHelper.narrow(ncRef.resolve_str(name));

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return frontEnd;
    }

    public static void s1(){
        Runnable t1 = () -> {
            FrontEnd servent1 = connect("frontEnd");
            System.out.println(servent1.enrolCourse("comps1111","comp1","fall"));
        };

        Runnable t2 = () -> {
            FrontEnd servent2 = connect("frontEnd");
            System.out.println(servent2.enrolCourse("comps2222","comp1","fall"));
        };

        Runnable t3 = () -> {
            FrontEnd servent3 = connect("frontEnd");
            System.out.println(servent3.enrolCourse("comps3333","comp1","fall"));
        };

        Runnable t4 = () -> {
            FrontEnd servent4 = connect("frontEnd");
            System.out.println(servent4.enrolCourse("comps4444","comp1","fall"));
        };

        Runnable t5 = () -> {
            FrontEnd servent5 = connect("frontEnd");
            System.out.println(servent5.enrolCourse("comps5555","comp1","fall"));
        };

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);
        Thread thread4 = new Thread(t4);
        Thread thread5 = new Thread(t5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    public static void s2(){
        FrontEnd servent1 = connect("frontEnd");

        Runnable t1 = () -> {
            System.out.println(servent1.dropCourse("comps1111","comp1"));
        };

        Runnable t2 = () -> {
            System.out.println(servent1.enrolCourse("comps3333","comp1","fall"));
        };

        Runnable t3 = () -> {
            System.out.println(servent1.enrolCourse("comps4444","comp1","fall"));
        };

        Runnable t4 = () -> {
            System.out.println(servent1.enrolCourse("comps5555","comp1","fall"));
        };

        Runnable t5 = () -> {
            System.out.println(servent1.enrolCourse("comps6666","comp1","fall"));
        };

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);
        Thread thread4 = new Thread(t4);
        Thread thread5 = new Thread(t5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    public static void s3(){
        FrontEnd servent1 = connect("frontEnd");

        Runnable t1 = () -> {
            System.out.println(servent1.swapCourse("comps3333","inse1","comp1"));
        };

        Runnable t2 = () -> {
            System.out.println(servent1.enrolCourse("comps3333","comp1","fall"));
        };

        Runnable t3 = () -> {
            System.out.println(servent1.enrolCourse("comps4444","comp1","fall"));
        };

        Runnable t4 = () -> {
            System.out.println(servent1.enrolCourse("comps5555","comp1","fall"));
        };

        Runnable t5 = () -> {
            System.out.println(servent1.enrolCourse("comps6666","comp1","fall"));
        };

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);
        Thread thread4 = new Thread(t4);
        Thread thread5 = new Thread(t5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    public static void checkdeadlock(){
        FrontEnd servent1 = connect("frontEnd");

        Runnable t1 = () -> {
            System.out.println(servent1.addCourse("comp4","fall"));
        };

        Runnable t2 = () -> {
            System.out.println(servent1.addCourse("comp5","fall"));
        };

        Runnable t3 = () -> {
            System.out.println(servent1.enrolCourse("comps4444","comp4","fall"));
        };

        Runnable t4 = () -> {
            System.out.println(servent1.enrolCourse("comps5555","comp5","fall"));
        };

        Runnable t5 = () -> {
            System.out.println(servent1.swapCourse("comps4444","comp5","comp4"));
        };

        Runnable t6 = () -> {
            System.out.println(servent1.swapCourse("comps5555","comp4","comp5"));
        };

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);
        Thread thread4 = new Thread(t4);
        Thread thread5 = new Thread(t5);
        Thread thread6 = new Thread(t6);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }

    public static void main(String[] args) {
//        s1();
//        s2();
//        s3();
        checkdeadlock();
    }
}
