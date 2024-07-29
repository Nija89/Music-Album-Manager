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
            int action = myScanner.nextInt();
            myScanner.nextLine();
            switch (action){
                case 0:
                    isTrue = false;
                    break;
                case 1:
                    if(!movingForward && myIterator.hasNext()){
                        myIterator.next();
                        movingForward = true;
                    }
                    if(myIterator.hasNext()){
                        presentSong = myIterator.next();
                        System.out.println("**************************");
                        System.out.println("Now PLaying : " + presentSong.getTitle() + " -> " + presentSong.getDuration() );
                        System.out.println("**************************");

                    }else {
                        System.out.println("**************************");
                        System.out.println("End of the List.");
                        System.out.println("**************************");
                        movingForward = false;
                    }
                    break;
                case 2:
                    if(movingForward && myIterator.hasPrevious()){
                        myIterator.previous();
                        movingForward = false;
                    }
                    if(myIterator.hasPrevious()){
                        presentSong = myIterator.previous();
                        System.out.println("**************************");
                        System.out.println("Now PLaying : " + presentSong.getTitle() + " -> " + presentSong.getDuration() );
                        System.out.println("**************************");
                    }else {
                        System.out.println("**************************");
                        System.out.println("Beginning of the List.");
                        System.out.println("**************************");
                        movingForward = true;
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
                    if(presentSong != null){
                        myAlbum.remove(presentSong);
                        System.out.println(presentSong.getTitle() + " has been removed.");
                        myIterator = myAlbum.listIterator();
                        presentSong = null;
                        if (myIterator.hasNext()) {
                            presentSong = myIterator.next();
                        }
                    }else{
                        System.out.println("No Current song running.");
                    }
                    break;
            }
        }
    }

    public void addSong(Song newSong){
        String newString = newSong.getTitle();
        ListIterator<Song> mySongIterator = myAlbum.listIterator();

        while(mySongIterator.hasNext()){
            Song currentSong = mySongIterator.next();
            int comp = currentSong.getTitle().compareTo(newString);
            if(comp == 0){
                System.out.println( newString + " Already added");
                return;
            }else if( comp > 0){
                mySongIterator.previous();
                break;
            }
        }
        mySongIterator.add(newSong);
    }

    public void listofSong(){
        if(myAlbum.isEmpty()){
            System.out.println("No Song added");
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
                "5. Delete current song");
        System.out.print("Choose a number: ");
    }
}
