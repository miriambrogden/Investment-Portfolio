package assignment3;

public class Assignment3 {

    public static void main(String[] args) {

        String fileName = "";
        if (args.length != 0) {
            fileName = args[0];
            PortfolioGui theGui = new PortfolioGui(fileName);
            theGui.setVisible(true);
        } else {
            System.out.println("Error: no commandline argument");
            System.exit(0);
        }

    }

}
