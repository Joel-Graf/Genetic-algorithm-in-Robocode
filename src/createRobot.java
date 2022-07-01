import javax.tools.*;
import java.io.*;
/* 
 * Create Robot
 * Creates and compiles Robocode Java file to test 
 * 
 */

public class createRobot {

    public static void create(double[] chromo) {
        createRobotFile(chromo); // create file
        compile(); // now compile it
    }

    public static void compile() {
        String fileToCompile = "robots/custom/JoelGraf.java"; // which file to compile * rhyming :) *
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, fileToCompile); // run compile
    }

    public static void createRobotFile(double[] chromo) {
        try {
            FileWriter fstream = new FileWriter("robots/custom/JoelGraf.java"); // file name to create
            BufferedWriter out = new BufferedWriter(fstream);

            out.write("package custom; \n " +
                    "import robocode.*; \n" +
                    "import robocode.Robot;\n" +
                    "import java.awt.Color;\n" +
                    "public class JoelGraf extends AdvancedRobot {\n");
            out.append("int turnDirection = 1;\n");
            out.append("int hunting = 1;\n");
            out.append("\n");

            out.append("public void run() {\n");
            out.append("setBodyColor(new Color(118, 88, 152));\n");
            out.append("setGunColor(new Color(82, 208, 83));\n");
            out.append("setRadarColor(new Color(230, 119, 11));\n");
            out.append("setBulletColor(new Color(211, 41, 15));\n");
            out.append("setScanColor(new Color(0, 0, 0));\n");
            out.append("\n");
            out.append("while (true) {\n");
            out.append("if(getEnergy() > (int)"+chromo[0]+") {\n");
            out.append("hunting = 1;\n");
            out.append("turnRight((int)"+chromo[1]+" * turnDirection);\n");
            out.append("} else {\n");
            out.append("hunting = 0;\n");
            out.append("turnRight((int)"+chromo[1]+" * -turnDirection);\n");
            out.append("ahead((int)"+chromo[2]+");\n");
            out.append("}\n");
            out.append("}\n");
            out.append("}\n");

            out.append("public void onScannedRobot(ScannedRobotEvent e) {\n");
            out.append("if (e.getBearing() >= 0) {\n");
            out.append("turnDirection = 1;\n");
            out.append("} else {\n");
            out.append("turnDirection = -1;\n");
            out.append("}\n");
            out.append("turnRight(e.getBearing());\n");
            out.append("\n");
            out.append("if (hunting > 0) {\n");
            out.append("if (e.getDistance() < (int)"+chromo[3]+") {\n");
            out.append("fire((int)"+chromo[4]+");\n");
            out.append("} else if (e.getDistance() < (int)"+chromo[5]+") {\n");
            out.append("fire((int)"+chromo[6]+");\n");
            out.append("}\n");
            out.append("ahead(e.getDistance() + (int)"+chromo[8]+");\n");
            out.append("scan();\n");
            out.append("} else {\n");
            out.append("if (e.getDistance() < (int)"+chromo[3]+") {\n");
            out.append("fire((int)"+chromo[7]+");\n");
            out.append("} else if (e.getDistance() < (int)"+chromo[5]+") {\n");
            out.append("fire((int)"+chromo[6]+");\n");
            out.append("} else {\n");
            out.append("fire((int)"+chromo[4]+");\n");
            out.append("}\n");
            out.append("turnRight((int)"+chromo[1]+" * -turnDirection);\n");
            out.append("ahead((int)"+chromo[2]+");\n");
            out.append("}\n");
            out.append("}\n");

            out.append("public void onHitRobot(HitRobotEvent e) {\n");
            out.append("if (e.getBearing() >= 0) {\n");
            out.append("turnDirection = 1;\n");
            out.append("} else {\n");
            out.append("turnDirection = -1;\n");
            out.append("}\n");
            out.append("\n");
            out.append("turnRight(e.getBearing());\n");
            out.append("fire((int)"+chromo[7]+");\n");
            out.append("ahead((int)"+chromo[2]+");\n");
            out.append("}\n");

            out.append("public void onHitWall(HitWallEvent e) {\n");
            out.append("turnRight((int)"+chromo[1]+");\n");
            out.append("ahead((int)"+chromo[2]+");\n");
            out.append("}\n");
            
            out.append("public void onWin(WinEvent e) {\n");
            out.append("for (int i = 0; i < 50; i++) {\n");
            out.append("turnRight(50); \n");
            out.append("turnLeft(50);\n");
            out.append("}\n");
            out.append("}\n");

            out.append("\n}");
            out.close(); // close output stream

        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}