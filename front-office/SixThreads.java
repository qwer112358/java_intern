import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Object lock = new Object();
    public static void main(String[] args) {
        List<String> chandlerReplicas = new ArrayList<>(List.of("Hey.", "And this from the cry-for-help department. Are you wearing makeup?",
                "That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman.",
                "Do you know which one you're gonna be?", "Good luck, man. I hope you get it"));
        List<String> joeyReplicas = new ArrayList<>(List.of("Hey, hey.", "Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.",
                "You know those posters for the City Free Clinic?", "No, but I hear lyme disease is open, so... (crosses fingers)",
                "Thanks."));
        List<String> phoebeReplicas = new ArrayList<>(List.of("Hey.", "What were you modeling for?", "You know, the asthma guy was really cute."));
        List<String> monicaReplicas = new ArrayList<>(List.of("Well, what's the job?","Oh, wow, so you're gonna be one of those \"healthy, healthy, healthy guys\""));
        String rachelReplica = "Assistant buyer. Oh! I would be shopping... for a living!";
        Thread joeyThread = new Thread(getRunnable(joeyReplicas, 1000L), "Joey: ");
        Thread chandlerThread = new Thread(getRunnableChandler(chandlerReplicas, 0), "Chandler: ");
        Thread phoebeThread = new Thread(getRunnablePhoebe(phoebeReplicas, 0L), "Phoebe: ");
        Thread monicaThread = new Thread(getRunnableMonica(monicaReplicas,0L),"Monica: ");
        Thread rachelThread = new Thread(() -> {
            try {
                Thread.sleep(100L);
                System.out.println(Thread.currentThread().getName() + " " + rachelReplica);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Rachel: ");

        monicaThread.start();
        rachelThread.start();

        try {
            Thread.sleep(1000L);
            joeyThread.start();
            chandlerThread.start();
            phoebeThread.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static Runnable getRunnable(List<String> replicas, long k) {
        return () -> {
            for (int i = 0; i < replicas.size(); i++) {
                try {
                    System.out.println(Thread.currentThread().getName() +  replicas.get(i));
                    Thread.sleep((k + 1000L) * (i + 1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private static Runnable getRunnableChandler(List<String> replicas, long k) {
        return () -> {
            for (int i = 0; i < replicas.size(); i++) {
                try {
                    if (i == 1){
                        Thread.sleep(300L);
                    }
                    if (i == 3) {
                        Thread.sleep(7000L);
                    }
                    if (i == 4) {
                        Thread.sleep(1000L);
                    }
                    Thread.sleep((k + 300L) * (i + 1));
                    System.out.println(Thread.currentThread().getName() +  replicas.get(i));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private static Runnable getRunnablePhoebe(List<String> replicas, long k) {
        return () -> {
            for (int i = 0; i < replicas.size(); i++) {
                try {
                    if (i == 0) {
                        Thread.sleep(100L);
                    }
                    if (i == 1) {
                        Thread.sleep(4500);
                    }
                    Thread.sleep((k + 300L) * (i + 1));
                    System.out.println(Thread.currentThread().getName() +  replicas.get(i));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private static Runnable getRunnableMonica(List<String> replicas, long k) {
        return () -> {
            for (int i = 0; i < replicas.size(); i++) {
                try {
                    if (i == 1) {
                        Thread.sleep(6300L);
                    }
                    System.out.println(Thread.currentThread().getName() +  replicas.get(i));
                    Thread.sleep((k + 1000L) * (i + 1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}