import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Album {
    public static Scanner myScanner = new Scanner(System.in);
    private String album;
    public Album(){}
    private ArrayList<Song> myAlbum = new ArrayList<Song>();

    public void controller(){
        boolean isTrue = true;
        boolean movingForward = true;
        Song presentSong = null;
        ListIterator<Song> myIterator = myAlbum.listIterator();

        while(isTrue){
            showMenu();

            boolean isInt = myScanner.hasNextInt();
            if(isInt) {
                int action = myScanner.nextInt();
                myScanner.nextLine();
                switch (action) {
                    case 0:
                        isTrue = false;
                        break;
                    case 1:
                        if (!movingForward && myIterator.hasNext()) {
                            myIterator.next();
                            movingForward = true;
                        }
                        if(!myAlbum.isEmpty()) {
                            if (myIterator.hasNext()) {
                                presentSong = myIterator.next();
                                System.out.println("**************************");
                                System.out.println("Now PLaying : " + presentSong.getTitle() + " -> " + presentSong.getDuration());
                                System.out.println("**************************");

                            } else {
                                System.out.println("**************************");
                                System.out.println("End of the List.");
                                System.out.println("**************************");
                                movingForward = false;
                            }
                        }else{
                            System.out.println("**************************");
                            System.out.println("No song has been added.");
                            System.out.println("**************************");
                        }
                        break;
                    case 2:
                        if (movingForward && myIterator.hasPrevious()) {
                            myIterator.previous();
                            movingForward = false;
                        }
                        if(!myAlbum.isEmpty()) {
                            if (myIterator.hasPrevious()) {
                                presentSong = myIterator.previous();
                                System.out.println("**************************");
                                System.out.println("Now PLaying : " + presentSong.getTitle() + " -> " + presentSong.getDuration());
                                System.out.println("**************************");
                            } else {
                                System.out.println("**************************");
                                System.out.println("Beginning of the List.");
                                System.out.println("**************************");
                                movingForward = true;
                            }
                        }else{
                            System.out.println("**************************");
                            System.out.println("No song has been added.");
                            System.out.println("**************************");
                        }
                        break;
                    case 3:
                        if (presentSong != null) {
                            System.out.println("**************************");
                            System.out.println("Replaying: " + presentSong.getTitle() + " -> " + presentSong.getDuration());
                            System.out.println("**************************");
                        } else {
                            System.out.println("**************************");
                            System.out.println("No song to replay.");
                            System.out.println("**************************");
                        }
                        break;
                    case 4:
                        listofSong();
                        break;
                    case 5:
                        if (presentSong != null) {
                            myAlbum.remove(presentSong);
                            System.out.println("**************************");
                            System.out.println(presentSong.getTitle() + " has been removed.");
                            System.out.println("**************************");
                            myIterator = myAlbum.listIterator();
                            presentSong = null;
                            if (myIterator.hasNext()) {
                                presentSong = myIterator.next();
                            }
                        } else {
                            System.out.println("**************************");
                            System.out.println("No Current song running.");
                            System.out.println("**************************");
                        }
                        break;
                    case 6:
                        myIterator = addSong();
                        break;
                    default:
                        System.out.println("Please choose a number between  0 to 6: ");
                        break;
                }
            }else{
                System.out.println("Please choose a number: ");
                myScanner.next();
            }
        }
    }

    public ListIterator<Song> addSong(){
        System.out.println("**************************");
        System.out.print("Enter song title: ");
        String name = myScanner.nextLine();
        System.out.print("Enter the duration of the song: ");
        double duration = myScanner.nextDouble();
        System.out.println("**************************");
        myScanner.nextLine();

        ListIterator<Song> mySongIterator = myAlbum.listIterator();

        while(mySongIterator.hasNext()){
            Song select = mySongIterator.next();
            int comp = select.getTitle().compareTo(name);
            if(comp == 0){
                System.out.println("**************************");
                System.out.println( name + " Already added");
                System.out.println("**************************");
                return null;
            }else if( comp > 0){
                mySongIterator.previous();
                mySongIterator.add(new Song(name, duration));
                ListIterator<Song> myIterator = myAlbum.listIterator();
                System.out.println("**************************");
                System.out.println(name + " -> "+ duration + " successfully added.");
                System.out.println("**************************");
                return myIterator;
            }
        }
        mySongIterator.add(new Song(name, duration));
        ListIterator<Song> myIterator = myAlbum.listIterator();
        System.out.println("**************************");
        System.out.println(name + " -> "+ duration + " successfully added.");
        System.out.println("**************************");
        return myIterator;
    }

    public void listofSong(){
        if(myAlbum.isEmpty()){
            System.out.println("**************************");
            System.out.println("No Song added");
            System.out.println("**************************");
        }else {
            System.out.println("===================");
            for (int i = 0; i < myAlbum.size(); i++) {
                Song presentSong = myAlbum.get(i);
                System.out.printf((i+1)+". %-15s%.2f%n", presentSong.getTitle(), presentSong.getDuration());
            }
            System.out.println("===================");
        }
    }

    public void showMenu(){
        System.out.println("\n0. to Exit\n" +
                "1. Next song\n" +
                "2. Previous song\n" +
                "3. Replay song\n" +
                "4. List song\n" +
                "5. Delete current song\n" +
                "6. Add new Song");
        System.out.print("Choose a number: ");
    }
}
