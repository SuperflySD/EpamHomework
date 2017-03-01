package unit2.task7submarine;

import java.lang.annotation.*;

@Documented
@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
@interface SubmarineName {
    String value() default "unknown";
}

@SubmarineName ("YellowSubmarine")
class AnnotatedNuclearSubmarine {
        private NuclearSubmarineEngine engine = new NuclearSubmarineEngine();
        private String status;

        public String go (int key){
            boolean ifSuccess = engine.start(key);
            if (ifSuccess) {
                status = "Your submarine is going somewhere!";
                System.out.println(status);
                return status;
            }
            else {
                status ="Your submarine can't move";
                System.out.println(status);
                return status;
            }

        }


        private class NuclearSubmarineEngine {

            private boolean start(int key){
                if (key==0) {
                    System.out.println("Engine has been successfully started)");
                    return true;
                }
                else {
                    System.out.println("Engine can't start(");
                    return false;
                }
            }

        }

}


